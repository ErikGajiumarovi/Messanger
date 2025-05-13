package com.erikproject.messanger.presentation.viewmodel.home_components

import com.erikproject.messanger.Navigator
import com.erikproject.messanger.Screen
import com.erikproject.messanger.domian.usecase.GetChatMembers
import com.erikproject.messanger.domian.usecase.GetChats
import com.erikproject.messanger.domian.usecase.GetMessages
import com.erikproject.messanger.presentation.CustomViewModel
import comerikprojectdatabase.Local_chat_members
import comerikprojectdatabase.Local_chats
import comerikprojectdatabase.Local_messages
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel для экрана списка чатов
 */
class ChatsViewModel(
    private val getChats: GetChats,
    private val getChatMembers: GetChatMembers,
    private val getMessages: GetMessages,
    private val navigator: Navigator
) : CustomViewModel(){
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
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _chats.value = getChats()
            _chatMembers.value = getChatMembers()
            _messages.value = getMessages()
        }
    }

    fun onChatClick(chatId: Long) {
        navigator.navigateTo(Screen.Chat(chatId))
    }
}