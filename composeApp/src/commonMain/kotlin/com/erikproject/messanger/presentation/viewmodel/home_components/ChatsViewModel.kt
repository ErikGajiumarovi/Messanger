package com.erikproject.messanger.presentation.viewmodel.home_components

import com.erikproject.messanger.Navigator
import com.erikproject.messanger.Screen
import com.erikproject.messanger.mock.FakeDummyData.generateSampleChats
import com.erikproject.messanger.mock.FakeDummyData.generateSampleChatsMembers
import com.erikproject.messanger.mock.FakeDummyData.generateSampleMessages
import comerikprojectdatabase.Local_chat_members
import comerikprojectdatabase.Local_chats
import comerikprojectdatabase.Local_messages
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


/**
 * ViewModel для экрана списка чатов
 */
class ChatsViewModel(
    private val navigator: Navigator
) {
    // Имитация данных из базы данных
    private val _chats = MutableStateFlow<List<Local_chats>>(emptyList())
    val chats: StateFlow<List<Local_chats>> = _chats.asStateFlow()

    // Имитация данных из базы данных
    private val _chatMembers = MutableStateFlow<List<Local_chat_members>>(emptyList())
    val chatMembers: StateFlow<List<Local_chat_members>> = _chatMembers.asStateFlow()

    // Имитация данных из базы данных
    private val _messages = MutableStateFlow<List<Local_messages>>(emptyList())
    val messages: StateFlow<List<Local_messages>> = _messages.asStateFlow()

    init {
        loadChats()
        loadChatMembers()
        loadMessages()
    }

    private fun loadMessages() {
        // Здесь в реальном приложении будет запрос к базе данных
        // Имитация данных для примера
        _messages.value = generateSampleMessages()
    }

    private fun loadChatMembers() {
        // Здесь в реальном приложении будет запрос к базе данных
        // Имитация данных для примера
        _chatMembers.value = generateSampleChatsMembers()
    }

    private fun loadChats() {
        // Здесь в реальном приложении будет запрос к базе данных
        // Имитация данных для примера
        _chats.value = generateSampleChats()
    }

    fun onChatClick(chatId: Long) {
        navigator.navigateTo(Screen.Chat(chatId))
    }
}