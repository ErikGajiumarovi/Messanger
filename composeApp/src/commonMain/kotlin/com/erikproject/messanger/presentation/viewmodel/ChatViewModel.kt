package com.erikproject.messanger.presentation.viewmodel

import com.erikproject.messanger.Navigator
import com.erikproject.messanger.data.repository.ChatMembersRepository
import com.erikproject.messanger.data.repository.ChatsRepository
import com.erikproject.messanger.data.repository.MessagesRepository
import com.erikproject.messanger.presentation.CustomViewModel
import com.erikproject.messanger.utils.Time
import comerikprojectdatabase.Local_chat_members
import comerikprojectdatabase.Local_chats
import comerikprojectdatabase.Local_messages
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock


class ChatViewModel(
    private val chatId: Long,
    private val navigator: Navigator,
    private val messagesRepository: MessagesRepository = MessagesRepository(),
    private val chatsRepository: ChatsRepository = ChatsRepository(),
    private val chatMembersRepository: ChatMembersRepository = ChatMembersRepository()
) : CustomViewModel() {
    // UI states
    private val _chatState = MutableStateFlow<ChatUiState>(ChatUiState.Loading)
    val chatState = _chatState.asStateFlow()

    private val _messageText = MutableStateFlow("")
    val messageText = _messageText.asStateFlow()

    // User ID of the current user - in a real app, get from user session
    private val currentUserId = 1001L

    init {
        loadChat()
    }

    fun loadChat() {
        viewModelScope.launch {
            try {
                val chat = chatsRepository.getChatById(chatId)
                val messages = messagesRepository.getMessagesByChatId(chatId)
                val members = chatMembersRepository.getChatMembersByChatId(chatId)

                if (chat != null) {
                    _chatState.value = ChatUiState.Success(
                        chat = chat,
                        messages = messages,
                        members = members
                    )
                } else {
                    _chatState.value = ChatUiState.Error("Chat not found")
                }
            } catch (e: Exception) {
                _chatState.value = ChatUiState.Error("Failed to load chat: ${e.message}")
            }
        }
    }

    fun updateMessageText(text: String) {
        _messageText.value = text
    }

    fun sendMessage() {
        val messageText = _messageText.value.trim()
        if (messageText.isEmpty()) return

        viewModelScope.launch {
            val now = Clock.System.now().toEpochMilliseconds()

            // Generate a new message ID (in a real app, this would be done differently)
            val newMessageId = Time.getTimeMills()

            val newMessage = Local_messages(
                id = newMessageId,
                server_id = null, // Will be set when synced with server
                chat_id = chatId,
                sender_id = currentUserId,
                text = messageText,
                is_deleted = 0L,
                created_at = now,
                updated_at = null,
                send_status = "sending",
                is_mine = 1L,
                delivery_status = "pending",
                error_message = null,
                sync_status = "not_synced",
                local_media_path = null
            )

            // Add message to database
            messagesRepository.addMessage(newMessage)

            // Update chat with last message
            chatsRepository.updateLastMessage(
                chatId = chatId,
                lastMessageId = newMessageId,
                lastMessageText = messageText,
                lastMessageTime = now
            )

            // Clear input field
            _messageText.value = ""

            // Reload chat data to update UI
            loadChat()

            // Simulate sending message to server (in a real app this would call an API)
            delay(1000)
            messagesRepository.updateMessageStatus(
                messageId = newMessageId,
                sendStatus = "sent",
                deliveryStatus = "delivered",
                syncStatus = "synced"
            )
            loadChat()
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