package com.erikproject.messanger.presentation.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.erikproject.messanger.presentation.AppScreen
import com.erikproject.messanger.presentation.Navigator
import com.erikproject.messanger.presentation.view.home_components.ChatsView
import com.erikproject.messanger.presentation.view.home_components.ContactsView
import com.erikproject.messanger.presentation.view.home_components.ProfileView
import com.erikproject.messanger.presentation.view.home_components.SettingsView
import org.koin.mp.KoinPlatform.getKoin

@Composable
fun HomeView(
    navigator: Navigator<AppScreen>,
) {
    val navigatorMenu = remember { Navigator(AppContent.Chats) }
    val currentScreen by navigatorMenu.currentScreen
    
    val navItems = listOf(
        NavigationItem(
            title = "Чаты",
            icon = Icons.Default.Home,
            view = AppContent.Chats
        ),
        NavigationItem(
            title = "Контакты",
            icon = Icons.Default.Person,
            view = AppContent.Contacts
        ),
        NavigationItem(
            title = "Настройки",
            icon = Icons.Default.Settings,
            view = AppContent.Settings
        ),
        NavigationItem(
            title = "Профиль",
            icon = Icons.Default.AccountCircle,
            view = AppContent.Profile
        )
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(8.dp)
            ) {
                navItems.forEach { item ->
                    val selected = currentScreen == item.view
                    NavigationBarItem(
                        selected = selected,
                        onClick = { navigatorMenu.navigateTo(item.view) },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title
                            )
                        },
                        label = {
                            Text(text = item.title)
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        // Display screen based on current selection
        val contentModifier = Modifier.padding(innerPadding)
        
        when (currentScreen) {
            AppContent.Chats -> ChatsView(getKoin().get(), navigator)
            AppContent.Contacts -> ContactsView(getKoin().get())
            AppContent.Settings -> SettingsView(getKoin().get())
            AppContent.Profile -> ProfileView(getKoin().get())
        }
    }
}


// Вспомогательные классы и объекты
data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val view: AppContent
)

enum class AppContent {
    Chats,
    Contacts,
    Settings,
    Profile
}



