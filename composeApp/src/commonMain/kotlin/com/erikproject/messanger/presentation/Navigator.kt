package com.erikproject.messanger.presentation

import androidx.compose.runtime.mutableStateOf

class Navigator<T>(
    initialScreen: T
) {
    var currentScreen = mutableStateOf(initialScreen)

    fun navigateTo(screen: T) {
        currentScreen.value = screen
    }
}