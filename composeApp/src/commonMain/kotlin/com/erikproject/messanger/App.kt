package com.erikproject.messanger

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.*
import androidx.compose.runtime.*

enum class Screen { Main, Chat, Login }

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun App() {
    var currentScreen by remember { mutableStateOf(Screen.Main) }


//    MaterialTheme(
//        typography = Typography(
//            bodyLarge = MaterialTheme.typography.bodyLarge.copy(
//                fontWeight = FontWeight.Normal
//            )
//        )
//    ) {
//        when (currentScreen) {
//            Screen.Main -> HomeScreen(HomeViewModel())
//            Screen.Chat -> ChatScreen(ChatViewModel())
//            Screen.Login -> LoginScreen(LoginViewModel())
//        }
//    }
}







