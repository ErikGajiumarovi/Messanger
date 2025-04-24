package com.erikproject.messanger.data.remote

import com.erikproject.messanger.utils.Time
import com.erikproject.messanger.expect_actual.TokenStorage
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.longOrNull
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class NetworkClient(val tokenStorage: TokenStorage) {
    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        install(Logging) {
            level = LogLevel.HEADERS
        }
        install(WebSockets)
        install(DefaultRequest) {
            contentType(ContentType.Application.Json)
        }
    }

    val baseUrl = "http://localhost:8080/"

    suspend inline fun <reified T> get(
        endpoint: String,
        requireAuth: Boolean = true,
        params: Map<String, String> = emptyMap()
    ): T {
        return request<T>(HttpMethod.Get, endpoint, null, requireAuth, params)
    }

    suspend inline fun <reified T> post(
        endpoint: String,
        body: Any? = null,
        requireAuth: Boolean = true
    ): T {
        return request<T>(HttpMethod.Post, endpoint, body, requireAuth)
    }

    suspend inline fun <reified T> request(
        method: HttpMethod,
        endpoint: String,
        body: Any? = null,
        requireAuth: Boolean = true,
        params: Map<String, String> = emptyMap()
    ): T {
        // Если нужен токен и он истек, пробуем обновить
        val token = tokenStorage.getAccessToken()

        if (token == null || requireAuth && isJwtExpired(token)) {
            refreshToken()
        }

        val url = "$baseUrl/$endpoint".let {
            if (params.isNotEmpty()) {
                val query = params.entries.joinToString("&") { "${it.key}=${it.value}" }
                "$it?$query"
            } else {
                it
            }
        }

        val response = client.request(url) {
            this.method = method

            if (requireAuth) {
                val token = tokenStorage.getAccessToken()
                if (token != null) {
                    header("Authorization", "Bearer $token")
                }
            }

            if (body != null && method != HttpMethod.Get) {
                contentType(ContentType.Application.Json)
                setBody(body)
            }
        }

        return response.body()
    }

    suspend fun refreshToken() {
        val refreshToken =
            tokenStorage.getRefreshToken() ?: throw Exception("No refresh token available")

        try {
            val response: TokenResponse = client.post("$baseUrl/auth/refresh") {
                contentType(ContentType.Application.Json)
                setBody(mapOf("refreshToken" to refreshToken))
            }.body()

            tokenStorage.saveAccessToken(response.accessToken)
        } catch (e: Exception) {
            // В случае ошибки очищаем токены
            tokenStorage.clearAccessToken()
            tokenStorage.clearRefreshToken()
            throw Exception("Session expired, please login again")
        }
    }

    data class TokenResponse(val accessToken: String)
}

@OptIn(ExperimentalEncodingApi::class)
fun isJwtExpired(token: String): Boolean {
    return try {
        val parts = token.split(".")
        if (parts.size != 3) return true // невалидный токен

        val payloadJson = Base64.decode(parts[1]).decodeToString()
        val jsonElement = Json.parseToJsonElement(payloadJson)
        val exp = jsonElement.jsonObject["exp"]?.jsonPrimitive?.longOrNull

        if (exp == null) true else Time.getTimeMills() >= exp
    } catch (e: Exception) {
        true // если не удалось декодировать — считаем токен просроченным
    }
}