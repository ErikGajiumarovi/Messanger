package com.erikproject.messanger.domian.repository

import comerikprojectdatabase.Local_messages

interface MessagesRepository {
    suspend fun getMessages(): List<Local_messages>
    suspend fun getMessagesByChatId(chatId: Long): List<Local_messages>
    suspend fun addMessage(message: Local_messages)
    suspend fun updateMessageStatus(
        messageId: Long,
        sendStatus: String,
        deliveryStatus: String,
        syncStatus: String
    )
}