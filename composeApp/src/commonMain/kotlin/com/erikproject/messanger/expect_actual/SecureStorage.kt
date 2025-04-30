package com.erikproject.messanger.expect_actual

expect class TokenStorage() {
    fun saveAccessToken(token: String)
    fun saveRefreshToken(token: String)

    fun getAccessToken(): String?
    fun getRefreshToken(): String?

    fun clearAccessToken()
    fun clearRefreshToken()
}
