package com.erikproject.messanger

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class Navigator {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.Home)

    fun navigateTo(screen: Screen) {
        currentScreen.value = screen
    }
}