package com.erikproject.messanger.presentation.view.home_components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.erikproject.messanger.presentation.viewmodel.home_components.ContactsViewModel

@Composable
fun ContactsView(
    viewModel: ContactsViewModel
) {
    // Содержимое экрана контактов
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Экран контактов")
    }
}