package com.erikproject.messanger.model

import kotlinx.coroutines.delay

class LoginModel {
    suspend fun login(username: String, password: String): String {
        delay(1000)
        return "Hello World"
    }
}