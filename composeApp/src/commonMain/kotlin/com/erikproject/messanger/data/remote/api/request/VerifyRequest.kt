package com.erikproject.messanger.data.remote.api.request

data class VerifyRequest(
    val username: String,
    val otp: String
)