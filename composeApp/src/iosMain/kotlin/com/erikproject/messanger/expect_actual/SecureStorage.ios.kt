package com.erikproject.messanger.expect_actual

import platform.Foundation.NSUserDefaults

actual class TokenStorage {
    private val defaults = NSUserDefaults.standardUserDefaults

    actual fun saveAccessToken(token: String) {
        defaults.setObject(token, forKey = "AccessToken")
    }

    actual fun saveRefreshToken(token: String) {
        defaults.setObject(token, forKey = "RefreshToken")
    }

    actual fun getAccessToken(): String? {
        return defaults.stringForKey("AccessToken")
    }

    actual fun getRefreshToken(): String? {
        return defaults.stringForKey("RefreshToken")
    }

    actual fun clearAccessToken() {
        defaults.removeObjectForKey("AccessToken")
    }

    actual fun clearRefreshToken() {
        defaults.removeObjectForKey("RefreshToken")
    }
}