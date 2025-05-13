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
import comerikprojectdatabase.Local_chat_members
import comerikprojectdatabase.Local_chats
import comerikprojectdatabase.Local_messages
import org.koin.mp.KoinPlatform.getKoin

sealed class Screen {
    object Home : Screen()
    object Login : Screen()
    data class Chat(val chatId: Long) : Screen()
}


@Composable
fun App() {

    val navigator: Navigator = getKoin().get()

    FileUtils.mkdir(getPathToDBs())

    MaterialTheme(
        typography = Typography(
            bodyLarge = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Normal
            )
        )
    ) {
        when (val screen = navigator.currentScreen.value) {
            is Screen.Home -> HomeScreen(viewModel = getKoin().get())
            is Screen.Chat -> {
                val chatViewModel = ChatViewModel(chatId = screen.chatId, navigator = navigator)
                ChatScreen(viewModel = chatViewModel)
            }
            is Screen.Login -> {
                LoginScreen(viewModel = getKoin().get())
            }
        }
    }
}


class Navigator {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.Home)

    fun navigateTo(screen: Screen) {
        currentScreen.value = screen
    }
}









