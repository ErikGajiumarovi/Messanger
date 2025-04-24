package com.erikproject.messanger

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.erikproject.database.MessengerDatabase
import com.erikproject.messanger.mvvm.model.LoginModel
import com.erikproject.messanger.mvvm.view.ChatScreen
import com.erikproject.messanger.mvvm.view.HomeScreen
import com.erikproject.messanger.mvvm.view.LoginScreen
import com.erikproject.messanger.mvvm.viewmodel.ChatViewModel
import com.erikproject.messanger.mvvm.viewmodel.HomeViewModel
import com.erikproject.messanger.mvvm.viewmodel.LoginViewModel

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







