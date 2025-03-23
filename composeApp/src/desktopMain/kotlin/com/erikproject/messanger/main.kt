package com.erikproject.messanger

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.erikproject.messanger.view.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Messanger",
    ) {
        App()
    }
}