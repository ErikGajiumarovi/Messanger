package com.erikproject.messanger.domian.repository

import comerikprojectdatabase.Local_chats

interface ChatsRepository {
    suspend fun getChats(): List<Local_chats>
    suspend fun getChatById(chatId: Long): Local_chats?
    suspend fun updateLastMessage(
        chatId: Long,
        lastMessageId: Long,
        lastMessageText: String,
        lastMessageTime: Long
    )
}