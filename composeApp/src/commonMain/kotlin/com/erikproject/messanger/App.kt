package com.erikproject.messanger

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.erikproject.messanger.data.storage.FileUtils
import com.erikproject.messanger.expect_actual.getPathToDBs
import com.erikproject.messanger.presentation.view.ChatScreen
import com.erikproject.messanger.presentation.view.HomeScreen
import com.erikproject.messanger.presentation.view.LoginScreen
import org.koin.core.parameter.parametersOf
import org.koin.mp.KoinPlatform.getKoin

sealed class Screen {
    object Home : Screen()
    object Login : Screen()
    data class Chat(val chatId: Long) : Screen()
}

@Composable
fun App() {
    FileUtils.mkdir(getPathToDBs())

    MaterialTheme(
        typography = Typography(
            bodyLarge = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Normal
            )
        )
    ) {
        when (val screen = getKoin().get<Navigator>().currentScreen.value) {
            is Screen.Home -> HomeScreen(viewModel = getKoin().get())

            is Screen.Chat -> ChatScreen(viewModel = getKoin().get(parameters = {
                parametersOf(
                    screen.chatId
                )
            }))

            is Screen.Login -> LoginScreen(viewModel = getKoin().get())
        }
    }
}

