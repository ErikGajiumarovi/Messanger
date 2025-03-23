package com.erikproject.messanger.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import com.erikproject.messanger.viewmodel.LoginViewModel

enum class Screen { Main, Chat, Login }

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val loginVM = LoginViewModel()

    var currentScreen by remember { mutableStateOf(Screen.Main) }
    var messages by remember { mutableStateOf(listOf("Hello!", "How are you?")) }
    var newMessage by remember { mutableStateOf("") }

    MaterialTheme(
        typography = Typography(
            bodyLarge = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Normal
            )
        )
    ) {
        when (currentScreen) {
            Screen.Main -> MainScreen(
                onNavigateToChat = { currentScreen = Screen.Chat },
                onNavigateToLogin = { currentScreen = Screen.Login }
            )
            Screen.Chat -> ChatScreen(
                messages = messages,
                newMessage = newMessage,
                onMessageChange = { newMessage = it },
                onSendMessage = {
                    if (newMessage.isNotBlank()) {
                        messages += newMessage
                        newMessage = ""
                    }
                },
                onBack = { currentScreen = Screen.Main }
            )
            Screen.Login -> LoginScreen(
                onBack = { currentScreen = Screen.Main },
                vm = loginVM
            )
        }
    }
}







