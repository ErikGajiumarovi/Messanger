package com.erikproject.messanger.data.repository

import comerikprojectdatabase.Local_chats
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import kotlin.time.Duration.Companion.days

class ChatsRepository {
    private val chats = mutableListOf<Local_chats>()

    init {
        chats.addAll(generateSampleChats())
    }

    suspend fun getChatById(chatId: Long): Local_chats? {
        // Simulate network delay
        delay(200)
        return chats.find { it.id == chatId }
    }

    suspend fun updateLastMessage(
        chatId: Long,
        lastMessageId: Long,
        lastMessageText: String,
        lastMessageTime: Long
    ) {
        val index = chats.indexOfFirst { it.id == chatId }
        if (index != -1) {
            val chat = chats[index]
            chats[index] = chat.copy(
                last_message_id = lastMessageId,
                last_message_text = lastMessageText,
                last_message_time = lastMessageTime,
                unread_count = 0L  // Reset unread count for the current user
            )
        }
    }

    private fun generateSampleChats(): List<Local_chats> {
        val now = Clock.System.now()
        val nowMillis = now.toEpochMilliseconds()

        // Yesterday date
        val yesterday = (now - 1.days).toEpochMilliseconds()

        // Date from a week ago
        val weekAgo = (now - 7.days).toEpochMilliseconds()

        return listOf(
            Local_chats(
                id = 1L,
                type = "personal",
                name = "Алексей Иванов",
                description = null,
                creator_id = null,
                created_at = weekAgo,
                avatar_url = null,
                avatar_local_path = null,
                last_message_id = 103L, // ID of the last message
                last_message_text = "Привет! Как дела с проектом?",
                last_message_time = nowMillis - 15 * 60 * 1000, // 15 minutes ago
                unread_count = 2L,
                last_sync_time = nowMillis,
                is_draft = 0L,
                last_draft_text = null,
            ),
            Local_chats(
                id = 2L,
                type = "group",
                name = "Рабочая группа",
                description = "Обсуждение текущих рабочих задач",
                creator_id = 1003L,
                created_at = weekAgo,
                avatar_url = null,
                avatar_local_path = null,
                last_message_id = 203L, // ID of the last message
                last_message_text = "Напоминаю про встречу завтра в 10:00!",
                last_message_time = yesterday,
                unread_count = 5L,
                last_sync_time = nowMillis,
                is_draft = 0L,
                last_draft_text = null,
            ),
            Local_chats(
                id = 3L,
                type = "personal",
                name = "Мария Петрова",
                description = null,
                creator_id = null,
                created_at = weekAgo,
                avatar_url = null,
                avatar_local_path = null,
                last_message_id = 302L, // ID of the last message
                last_message_text = "Пришлю документы вечером",
                last_message_time = nowMillis - 3 * 60 * 60 * 1000, // 3 hours ago
                unread_count = 0L,
                last_sync_time = nowMillis,
                is_draft = 1L,
                last_draft_text = "Спасибо за информацию, буду ждать",
            ),
            Local_chats(
                id = 4L,
                type = "channel",
                name = "Новости компании",
                description = "Официальный канал корпоративных новостей",
                creator_id = 1006L,
                created_at = weekAgo,
                avatar_url = null,
                avatar_local_path = null,
                last_message_id = 401L, // ID of the last message
                last_message_text = "Мы открываем новый офис в Москве! Подробности на корпоративном портале.",
                last_message_time = nowMillis - 24 * 60 * 60 * 1000, // a day ago
                unread_count = 1L,
                last_sync_time = nowMillis,
                is_draft = 0L,
                last_draft_text = null,
            ),
            Local_chats(
                id = 5L,
                type = "self",
                name = "Избранное",
                description = "Сохраненные сообщения",
                creator_id = 1001L,
                created_at = weekAgo,
                avatar_url = null,
                avatar_local_path = null,
                last_message_id = 501L, // ID of the last message
                last_message_text = "Важные ссылки для проекта: https://example.com/project",
                last_message_time = nowMillis - 2 * 24 * 60 * 60 * 1000, // 2 days ago
                unread_count = 0L,
                last_sync_time = nowMillis,
                is_draft = 0L,
                last_draft_text = null,
            )
        )
    }
}