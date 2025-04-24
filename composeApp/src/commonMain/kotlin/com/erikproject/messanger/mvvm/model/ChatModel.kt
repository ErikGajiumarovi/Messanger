package com.erikproject.messanger.mvvm.model

import androidx.compose.runtime.mutableStateListOf
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.url
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.runBlocking

class ChatModel(private val client: HttpClient) {
    private var session: DefaultClientWebSocketSession? = null
    private var username: String = ""
    val messages = mutableStateListOf<String>()

    suspend fun connect(user: String) {
        username = user
        session = client.webSocketSession {
            url("ws://localhost:8080/chat/$username")
        }

        session?.incoming?.consumeEach { frame ->
            if (frame is Frame.Text) {
                messages.add(frame.readText())
            }
        }
    }

    suspend fun sendMessage(text: String) {
        session?.send(Frame.Text(text))
    }

    fun disconnect() {
        runBlocking {
            session?.close()
        }
    }
}