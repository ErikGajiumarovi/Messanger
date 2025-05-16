package com.erikproject.messanger.presentation.viewmodel

import com.erikproject.messanger.domian.repository.ChatMembersRepository
import com.erikproject.messanger.domian.repository.ChatsRepository
import com.erikproject.messanger.domian.repository.MessagesRepository
import com.erikproject.messanger.domian.usecase.GetChatById
import com.erikproject.messanger.domian.usecase.GetChatMembersByChatId
import com.erikproject.messanger.domian.usecase.GetMessagesByChatId
import com.erikproject.messanger.domian.usecase.SendMessage
import com.erikproject.messanger.presentation.AppScreen
import com.erikproject.messanger.presentation.CustomViewModel
import com.erikproject.messanger.presentation.Navigator
import com.erikproject.messanger.utils.Time
import comerikprojectdatabase.Local_chat_members
import comerikprojectdatabase.Local_chats
import comerikprojectdatabase.Local_messages
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

class ChatViewModel (
    private val chatId: Long,
    private val getChatById: GetChatById,
    private val getMessagesByChatId: GetMessagesByChatId,
    private val getChatMembersByChatId: GetChatMembersByChatId,
    private val sendMessage: SendMessage
) : CustomViewModel() {

    private val _chatState = MutableStateFlow<ChatUiState>(ChatUiState.Loading)
    val chatState = _chatState.asStateFlow()

    private val _messageText = MutableStateFlow("")
    val messageText = _messageText.asStateFlow()

    private val currentUserId = 1001L

    init {
        loadInitialData()
    }

    fun loadInitialData() {
        viewModelScope.launch {
            try {
                val chat = getChatById(chatId)
                val messages = getMessagesByChatId(chatId)
                val members = getChatMembersByChatId(chatId)

                _chatState.value = if (chat != null) {
                    ChatUiState.Success(chat, messages, members)
                } else {
                    ChatUiState.Error("Chat not found")
                }
            } catch (e: Exception) {
                _chatState.value = ChatUiState.Error("Loading failed: ${e.message}")
            }
        }
    }

    fun updateMessageText(text: String) {
        _messageText.value = text
    }

    fun sendMessage() {
        val text = _messageText.value.trim()
        if (text.isEmpty()) return

        viewModelScope.launch {
            sendMessage(
                chatId = chatId,
                text = text,
                currentUserId = currentUserId
            )
            _messageText.value = ""
            loadInitialData() // Обновляем данные после отправки
        }
    }
}

sealed class ChatUiState {
    object Loading : ChatUiState()
    data class Success(
        val chat: Local_chats,
        val messages: List<Local_messages>,
        val members: List<Local_chat_members>
    ) : ChatUiState()
    data class Error(val message: String) : ChatUiState()
}
