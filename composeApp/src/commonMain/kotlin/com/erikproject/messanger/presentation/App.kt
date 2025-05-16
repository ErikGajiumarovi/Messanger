package com.erikproject.messanger.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.erikproject.messanger.data.storage.FileUtils
import com.erikproject.messanger.expect_actual.getPathToDBs
import com.erikproject.messanger.presentation.view.ChatView
import com.erikproject.messanger.presentation.view.HomeView
import com.erikproject.messanger.presentation.view.LoginView
import org.koin.core.parameter.parametersOf
import org.koin.mp.KoinPlatform.getKoin

@Composable
fun App() {
    val nav = Navigator<AppScreen>(AppScreen.Home)
    FileUtils.mkdir(getPathToDBs())

    MaterialTheme(
        typography = Typography(
            bodyLarge = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Normal
            )
        )
    ) {
        when (val screen = nav.currentScreen.value) {
            is AppScreen.Home -> HomeView(nav)

            is AppScreen.Chat -> ChatView(
                getKoin().get(parameters = { parametersOf(screen.chatId) }),
                nav
            )

            is AppScreen.Login -> LoginView(getKoin().get())
        }
    }

}

sealed class AppScreen {
    object Home : AppScreen()
    object Login : AppScreen()
    data class Chat(val chatId: Long) : AppScreen()
}
