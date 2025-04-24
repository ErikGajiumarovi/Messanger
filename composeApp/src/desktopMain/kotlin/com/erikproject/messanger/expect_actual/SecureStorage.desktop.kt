package com.erikproject.messanger.expect_actual

import java.util.prefs.Preferences

actual object TokenStorage {
    private val prefs = Preferences.userRoot()

    actual fun saveAccessToken(token: String) {
        prefs.put("AccessToken", token)
        prefs.flush()
    }

    actual fun saveRefreshToken(token: String) {
        prefs.put("RefreshToken", token)
        prefs.flush()
    }

    actual fun getAccessToken(): String? {
        return prefs.get("AccessToken", null)
    }

    actual fun getRefreshToken(): String? {
        return prefs.get("RefreshToken", null)
    }

    actual fun clearAccessToken() {
        prefs.remove("AccessToken")
        prefs.flush()
    }

    actual fun clearRefreshToken() {
        prefs.remove("RefreshToken")
        prefs.flush()
    }
}