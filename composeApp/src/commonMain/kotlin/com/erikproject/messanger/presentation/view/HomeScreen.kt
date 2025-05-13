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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.erikproject.messanger.Navigator
import com.erikproject.messanger.presentation.view.home_components.ChatsScreen
import com.erikproject.messanger.presentation.view.home_components.ContactsScreen
import com.erikproject.messanger.presentation.view.home_components.ProfileScreen
import com.erikproject.messanger.presentation.view.home_components.SettingsScreen
import com.erikproject.messanger.presentation.viewmodel.ChatViewModel
import com.erikproject.messanger.presentation.viewmodel.home_components.ChatsViewModel
import com.erikproject.messanger.presentation.viewmodel.HomeViewModel
import org.koin.mp.KoinPlatform.getKoin

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    val navItems = listOf(
        NavigationItem(
            title = "Чаты",
            icon = Icons.Default.Home,
            screen = Screen.Chats
        ),
        NavigationItem(
            title = "Контакты",
            icon = Icons.Default.Person,
            screen = Screen.Contacts
        ),
        NavigationItem(
            title = "Настройки",
            icon = Icons.Default.Settings,
            screen = Screen.Settings
        ),
        NavigationItem(
            title = "Профиль",
            icon = Icons.Default.AccountCircle,
            screen = Screen.Profile
        )
    )

    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen =
        navItems.find { it.screen.route == currentDestination?.route } ?: navItems[0]

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(8.dp)
            ) {
                navItems.forEach { item ->
                    val selected = currentScreen == item
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(item.screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
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
        NavHost(
            navController = navController,
            startDestination = Screen.Chats.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Chats.route) {
                ChatsScreen(getKoin().get())
            }
            composable(Screen.Contacts.route) {
                ContactsScreen(getKoin().get())
            }
            composable(Screen.Settings.route) {
                SettingsScreen(getKoin().get())
            }
            composable(Screen.Profile.route) {
                ProfileScreen(getKoin().get())
            }
        }
    }
}


// Вспомогательные классы и объекты
data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)

sealed class Screen(val route: String) {
    object Chats : Screen("chats")
    object Contacts : Screen("contacts")
    object Settings : Screen("settings")
    object Profile : Screen("profile")
}



