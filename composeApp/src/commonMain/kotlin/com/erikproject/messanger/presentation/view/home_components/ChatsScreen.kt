package com.erikproject.messanger.presentation.view.home_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.erikproject.messanger.Navigator
import com.erikproject.messanger.Screen
import com.erikproject.messanger.presentation.viewmodel.ChatUiState
import com.erikproject.messanger.presentation.viewmodel.ChatViewModel
import com.erikproject.messanger.presentation.viewmodel.home_components.ChatsViewModel
import comerikprojectdatabase.Local_messages
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatsScreen(
    viewModel: ChatsViewModel
) {
    val chats by viewModel.chats.collectAsState()
    val chatMembers by viewModel.chatMembers.collectAsState()
    val messages by viewModel.messages.collectAsState()


}