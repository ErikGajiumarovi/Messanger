package com.erikproject.messanger.data.remote.api.request

data class RegisterRequest(
    val username: String,
    val email: String,
    val phoneNumber: String,
    val password: String
)