package com.erikproject.messanger.data.repository

import com.erikproject.messanger.data.local.database.AppDatabase
import com.erikproject.messanger.data.remote.NetworkClient
import com.erikproject.messanger.data.remote.api.request.*
import com.erikproject.messanger.data.remote.api.response.TokenPair
import com.erikproject.messanger.expect_actual.TokenStorage


class UserRepository(
    private val networkClient: NetworkClient,
    private val database: AppDatabase,
    private val storage: TokenStorage
) {
    
    init {
        println("init UserRepository")
    }

    suspend fun loginUser(
        username: String,
        password: String
    ) {
        val response: String = networkClient.post(
            endpoint = "auth/login",
            body = LoginRequest(username, password),
            requireAuth = false
        )

        println(response)
    }

    suspend fun registerUser(
        username: String,
        email: String,
        phoneNumber: String,
        password: String
    ) {
        val response: String = networkClient.post(
            endpoint = "auth/register",
            body = RegisterRequest(username, email, phoneNumber, password),
            requireAuth = false
        )
        println(response)

    }

    suspend fun verifyUser(
        username: String,
        otp: String
    ) {
        val response: TokenPair = networkClient.post(
            endpoint = "auth/register",
            body = VerifyRequest(username, otp),
            requireAuth = false
        )
        println(response)

        storage.saveAccessToken(response.accessToken)
        storage.saveRefreshToken(response.refreshToken)
    }
}

