package com.erikproject.messanger.expect_actual

import android.content.Context
import com.erikproject.messanger.platform_specific.KmpInitializer.applicationContext

actual object TokenStorage {
    private val prefs = applicationContext.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    actual fun saveAccessToken(token: String) {
        prefs.edit().putString("AccessToken", token).apply()
    }

    actual fun saveRefreshToken(token: String) {
        prefs.edit().putString("RefreshToken", token).apply()
    }

    actual fun getAccessToken(): String? {
        return prefs.getString("AccessToken", null)
    }

    actual fun getRefreshToken(): String? {
        return prefs.getString("RefreshToken", null)
    }

    actual fun clearAccessToken() {
        prefs.edit().remove("AccessToken").apply()
    }

    actual fun clearRefreshToken() {
        prefs.edit().remove("RefreshToken").apply()
    }

}