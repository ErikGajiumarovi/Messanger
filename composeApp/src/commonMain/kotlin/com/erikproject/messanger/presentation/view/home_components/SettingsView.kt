package com.erikproject.messanger.presentation.view.home_components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.erikproject.messanger.presentation.viewmodel.home_components.SettingsViewModel

@Composable
fun SettingsView(
    viewModel: SettingsViewModel
) {
    // Содержимое экрана настроек
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Экран настроек")
    }
}