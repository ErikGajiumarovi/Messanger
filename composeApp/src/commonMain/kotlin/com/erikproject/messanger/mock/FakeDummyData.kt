package com.erikproject.messanger.mock

import com.erikproject.messanger.utils.Time
import comerikprojectdatabase.Local_chat_members
import comerikprojectdatabase.Local_chats
import comerikprojectdatabase.Local_messages
import kotlinx.datetime.Clock
import kotlin.time.Duration.Companion.days

object FakeDummyData {

    fun generateSampleChatsMembers(): List<Local_chat_members> {
        val currentTimeMillis = Time.getTimeMills()

        // Generate 4 sample chat members with realistic data
        return listOf(
            // Chat 1 members
            Local_chat_members(
                chat_id = 1L,
                user_id = 1001L,
                role = "admin",
                joined_at = currentTimeMillis - 7_890_000_000, // About 3 months ago
                last_sync_time = currentTimeMillis - 3600_000 // 1 hour ago
            ),
            Local_chat_members(
                chat_id = 1L,
                user_id = 1002L,
                role = "member",
                joined_at = currentTimeMillis - 5_184_000_000, // About 2 months ago
                last_sync_time = currentTimeMillis - 7200_000 // 2 hours ago
            ),

            // Chat 2 members
            Local_chat_members(
                chat_id = 2L,
                user_id = 1001L,
                role = "member",
                joined_at = currentTimeMillis - 5_184_000_000, // About 2 months ago
                last_sync_time = currentTimeMillis - 5400_000 // 1.5 hours ago
            ),
            Local_chat_members(
                chat_id = 2L,
                user_id = 1003L,
                role = "admin",
                joined_at = currentTimeMillis - 10_368_000_000, // About 4 months ago
                last_sync_time = currentTimeMillis - 1800_000 // 30 minutes ago
            ),

            // Chat 3 members
            Local_chat_members(
                chat_id = 3L,
                user_id = 1004L,
                role = "admin",
                joined_at = currentTimeMillis - 12_960_000_000, // About 5 months ago
                last_sync_time = currentTimeMillis - 1200_000 // 20 minutes ago
            ),
            Local_chat_members(
                chat_id = 3L,
                user_id = 1001L,
                role = "member",
                joined_at = currentTimeMillis - 10_368_000_000, // About 4 months ago
                last_sync_time = currentTimeMillis - 2700_000 // 45 minutes ago
            ),

            // Chat 4 members - a channel with just the creator
            Local_chat_members(
                chat_id = 4L,
                user_id = 1006L,
                role = "admin",
                joined_at = currentTimeMillis - 1_296_000_000, // About 15 days ago
                last_sync_time = currentTimeMillis - 600_000 // 10 minutes ago
            ),

            // Chat 5 - self chat
            Local_chat_members(
                chat_id = 5L,
                user_id = 1001L,
                role = "admin",
                joined_at = null, // No joined date for this user
                last_sync_time = currentTimeMillis - 3600_000 // 1 hour ago
            )
        )
    }

    fun generateSampleChats(): List<Local_chats> {
        val now = Clock.System.now()
        val nowMillis = now.toEpochMilliseconds()

        // Вчерашняя дата
        val yesterday = (now - 1.days).toEpochMilliseconds()

        // Дата от недели назад
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
                last_message_id = 103L, // ID последнего сообщения
                last_message_text = "Привет! Как дела с проектом?",
                last_message_time = nowMillis - 15 * 60 * 1000, // 15 минут назад
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
                last_message_id = 203L, // ID последнего сообщения
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
                last_message_id = 302L, // ID последнего сообщения
                last_message_text = "Пришлю документы вечером",
                last_message_time = nowMillis - 3 * 60 * 60 * 1000, // 3 часа назад
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
                last_message_id = 401L, // ID последнего сообщения
                last_message_text = "Мы открываем новый офис в Москве! Подробности на корпоративном портале.",
                last_message_time = nowMillis - 24 * 60 * 60 * 1000, // день назад
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
                last_message_id = 501L, // ID последнего сообщения
                last_message_text = "Важные ссылки для проекта: https://example.com/project",
                last_message_time = nowMillis - 2 * 24 * 60 * 60 * 1000, // 2 дня назад
                unread_count = 0L,
                last_sync_time = nowMillis,
                is_draft = 0L,
                last_draft_text = null,
            )
        )
    }

    /**
     * Генерирует примеры сообщений для тестирования
     */
    fun generateSampleMessages(): List<Local_messages> {
        val now = Clock.System.now()
        val nowMillis = now.toEpochMilliseconds()

        // Расчет времени для разных сообщений
        val fifteenMinutesAgo = nowMillis - 15 * 60 * 1000
        val twentyMinutesAgo = nowMillis - 20 * 60 * 1000
        val thirtyMinutesAgo = nowMillis - 30 * 60 * 1000
        val oneHourAgo = nowMillis - 60 * 60 * 1000
        val twoHoursAgo = nowMillis - 2 * 60 * 60 * 1000
        val threeHoursAgo = nowMillis - 3 * 60 * 60 * 1000
        val yesterday = nowMillis - 24 * 60 * 60 * 1000
        val twoDaysAgo = nowMillis - 2 * 24 * 60 * 60 * 1000

        return listOf(
            // Чат 1 - личный чат с Алексеем Ивановым
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
                is_mine = 0L, // сообщение от собеседника
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
                is_mine = 1L, // мое сообщение
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
                is_mine = 0L, // сообщение от собеседника
                delivery_status = "read",
                error_message = null,
                sync_status = "synced",
                local_media_path = null
            ),

            // Чат 2 - групповой чат
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
                is_mine = 1L, // мое сообщение
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
                is_mine = 0L, // сообщение от другого участника
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
                is_mine = 0L, // сообщение от другого участника
                delivery_status = "delivered",
                error_message = null,
                sync_status = "synced",
                local_media_path = null
            ),

            // Чат 3 - личный чат с Марией Петровой
            Local_messages(
                id = 301L,
                server_id = 80001L,
                chat_id = 3L,
                sender_id = 1001L,
                text = "Мария, нужны документы по последнему проекту",
                is_deleted = 0L,
                created_at = threeHoursAgo + 600_000, // 3 часа 10 минут назад
                updated_at = null,
                send_status = "sent",
                is_mine = 1L, // мое сообщение
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
                is_mine = 0L, // сообщение от собеседника
                delivery_status = "read",
                error_message = null,
                sync_status = "synced",
                local_media_path = null
            ),

            // Чат 4 - канал новостей компании
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
                is_mine = 0L, // сообщение от админа канала
                delivery_status = "read",
                error_message = null,
                sync_status = "synced",
                local_media_path = null
            ),

            // Чат 5 - "Избранное"
            Local_messages(
                id = 501L,
                server_id = null, // может быть null для локальных сообщений
                chat_id = 5L,
                sender_id = 1001L,
                text = "Важные ссылки для проекта: https://example.com/project",
                is_deleted = 0L,
                created_at = twoDaysAgo,
                updated_at = null,
                send_status = "sent",
                is_mine = 1L, // мое сообщение
                delivery_status = "read",
                error_message = null,
                sync_status = "local_only", // сохранено только локально
                local_media_path = null
            )
        )
    }
}