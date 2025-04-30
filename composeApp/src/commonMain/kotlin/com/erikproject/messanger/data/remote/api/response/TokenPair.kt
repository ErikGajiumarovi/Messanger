package com.erikproject.messanger.data.remote.api.response

data class TokenPair(
    val accessToken: String,
    val refreshToken: String
)