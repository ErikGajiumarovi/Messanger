package com.erikproject.messanger.data.repository

import comerikprojectdatabase.Local_messages
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock

class MessagesRepository {
    private val messages = mutableListOf<Local_messages>()

    init {
        messages.addAll(generateSampleMessages())
    }

    suspend fun getMessagesByChatId(chatId: Long): List<Local_messages> {
        // Simulate network delay
        delay(300)
        return messages.filter { it.chat_id == chatId }.sortedBy { it.created_at }
    }

    suspend fun addMessage(message: Local_messages) {
        messages.add(message)
    }

    suspend fun updateMessageStatus(
        messageId: Long,
        sendStatus: String,
        deliveryStatus: String,
        syncStatus: String
    ) {
        val index = messages.indexOfFirst { it.id == messageId }
        if (index != -1) {
            val message = messages[index]
            messages[index] = message.copy(
                send_status = sendStatus,
                delivery_status = deliveryStatus,
                sync_status = syncStatus
            )
        }
    }

    private fun generateSampleMessages(): List<Local_messages> {
        val now = Clock.System.now()
        val nowMillis = now.toEpochMilliseconds()

        // Time calculations for different messages
        val fifteenMinutesAgo = nowMillis - 15 * 60 * 1000
        val twentyMinutesAgo = nowMillis - 20 * 60 * 1000
        val thirtyMinutesAgo = nowMillis - 30 * 60 * 1000
        val oneHourAgo = nowMillis - 60 * 60 * 1000
        val twoHoursAgo = nowMillis - 2 * 60 * 60 * 1000
        val threeHoursAgo = nowMillis - 3 * 60 * 60 * 1000
        val yesterday = nowMillis - 24 * 60 * 60 * 1000
        val twoDaysAgo = nowMillis - 2 * 24 * 60 * 60 * 1000

        return listOf(
            // Chat 1 - personal chat with Alexey Ivanov
            Local_messages(
                id = 101L,
                server_id = 60001L,
                chat_id = 1L,
                sender_id = 1002L,
                text = "Привет! Есть минутка обсудить новый проект?",
                is_deleted = 0L,
                created_at = thirtyMinutesAgo,
                updated_at = null,
                send_status = "sent",
                is_mine = 0L, // message from the other person
                delivery_status = "read",
                error_message = null,
                sync_status = "synced",
                local_media_path = null
            ),
            Local_messages(
                id = 102L,
                server_id = 60002L,
                chat_id = 1L,
                sender_id = 1001L,
                text = "Да, конечно. Что именно нужно обсудить?",
                is_deleted = 0L,
                created_at = twentyMinutesAgo,
                updated_at = null,
                send_status = "sent",
                is_mine = 1L, // my message
                delivery_status = "read",
                error_message = null,
                sync_status = "synced",
                local_media_path = null
            ),
            Local_messages(
                id = 103L,
                server_id = 60003L,
                chat_id = 1L,
                sender_id = 1002L,
                text = "Привет! Как дела с проектом?",
                is_deleted = 0L,
                created_at = fifteenMinutesAgo,
                updated_at = null,
                send_status = "sent",
                is_mine = 0L, // message from the other person
                delivery_status = "read",
                error_message = null,
                sync_status = "synced",
                local_media_path = null
            ),

            // Chat 2 - group chat
            Local_messages(
                id = 201L,
                server_id = 70001L,
                chat_id = 2L,
                sender_id = 1001L,
                text = "Всем привет! Когда у нас следующая встреча?",
                is_deleted = 0L,
                created_at = twoHoursAgo,
                updated_at = null,
                send_status = "sent",
                is_mine = 1L, // my message
                delivery_status = "read",
                error_message = null,
                sync_status = "synced",
                local_media_path = null
            ),
            Local_messages(
                id = 202L,
                server_id = 70002L,
                chat_id = 2L,
                sender_id = 1003L,
                text = "Давайте встретимся завтра. Всем удобно?",
                is_deleted = 0L,
                created_at = oneHourAgo,
                updated_at = null,
                send_status = "sent",
                is_mine = 0L, // message from another participant
                delivery_status = "read",
                error_message = null,
                sync_status = "synced",
                local_media_path = null
            ),
            Local_messages(
                id = 203L,
                server_id = 70003L,
                chat_id = 2L,
                sender_id = 1003L,
                text = "Напоминаю про встречу завтра в 10:00!",
                is_deleted = 0L,
                created_at = yesterday,
                updated_at = null,
                send_status = "sent",
                is_mine = 0L, // message from another participant
                delivery_status = "delivered",
                error_message = null,
                sync_status = "synced",
                local_media_path = null
            ),

            // Chat 3 - personal chat with Maria Petrova
            Local_messages(
                id = 301L,
                server_id = 80001L,
                chat_id = 3L,
                sender_id = 1001L,
                text = "Мария, нужны документы по последнему проекту",
                is_deleted = 0L,
                created_at = threeHoursAgo + 600_000, // 3 hours 10 minutes ago
                updated_at = null,
                send_status = "sent",
                is_mine = 1L, // my message
                delivery_status = "read",
                error_message = null,
                sync_status = "synced",
                local_media_path = null
            ),
            Local_messages(
                id = 302L,
                server_id = 80002L,
                chat_id = 3L,
                sender_id = 1004L,
                text = "Пришлю документы вечером",
                is_deleted = 0L,
                created_at = threeHoursAgo,
                updated_at = null,
                send_status = "sent",
                is_mine = 0L, // message from the other person
                delivery_status = "read",
                error_message = null,
                sync_status = "synced",
                local_media_path = null
            ),

            // Chat 4 - company news channel
            Local_messages(
                id = 401L,
                server_id = 90001L,
                chat_id = 4L,
                sender_id = 1006L,
                text = "Мы открываем новый офис в Москве! Подробности на корпоративном портале.",
                is_deleted = 0L,
                created_at = yesterday,
                updated_at = null,
                send_status = "sent",
                is_mine = 0L, // message from channel admin
                delivery_status = "read",
                error_message = null,
                sync_status = "synced",
                local_media_path = null
            ),

            // Chat 5 - "Saved messages"
            Local_messages(
                id = 501L,
                server_id = null, // can be null for local messages
                chat_id = 5L,
                sender_id = 1001L,
                text = "Важные ссылки для проекта: https://example.com/project",
                is_deleted = 0L,
                created_at = twoDaysAgo,
                updated_at = null,
                send_status = "sent",
                is_mine = 1L, // my message
                delivery_status = "read",
                error_message = null,
                sync_status = "local_only", // stored locally only
                local_media_path = null
            )
        )
    }
}