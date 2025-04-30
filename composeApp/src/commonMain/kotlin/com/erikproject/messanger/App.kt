package com.erikproject.messanger

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import com.erikproject.messanger.data.local.database.AppDatabase
import com.erikproject.messanger.data.remote.NetworkClient
import com.erikproject.messanger.data.repository.UserRepository
import com.erikproject.messanger.data.storage.FileUtils
import com.erikproject.messanger.expect_actual.TokenStorage
import com.erikproject.messanger.expect_actual.getDriver
import com.erikproject.messanger.expect_actual.getPathToDBs
import com.erikproject.messanger.presentation.view.ChatScreen
import com.erikproject.messanger.presentation.view.HomeScreen
import com.erikproject.messanger.presentation.view.LoginScreen
import com.erikproject.messanger.presentation.viewmodel.ChatViewModel
import com.erikproject.messanger.presentation.viewmodel.HomeViewModel
import com.erikproject.messanger.presentation.viewmodel.LoginViewModel
import org.koin.mp.KoinPlatform.getKoin

enum class Screen { Main, Chat, Login }

@Composable
fun App() {
    val currentScreen by remember { mutableStateOf(Screen.Login) }

    FileUtils.mkdir(getPathToDBs())

    MaterialTheme(
        typography = Typography(
            bodyLarge = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Normal
            )
        )
    ) {
        when (currentScreen) {
            Screen.Main -> HomeScreen(HomeViewModel())
            Screen.Chat -> ChatScreen(ChatViewModel())
            Screen.Login -> {
                val loginViewModel: LoginViewModel = getKoin().get()
                LoginScreen(loginViewModel)
            }
        }
    }
}







