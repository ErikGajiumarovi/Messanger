package com.erikproject.messanger.presentation.viewmodel

import ChatItem
import ChatMember
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.datetime.Clock
import kotlin.time.Duration.Companion.days


/**
 * ViewModel для экрана списка чатов
 */
class ChatsViewModel {
    // Имитация данных из базы данных
    private val _chats = MutableStateFlow<List<ChatItem>>(emptyList())
    val chats: StateFlow<List<ChatItem>> = _chats.asStateFlow()

    private val _currentUserId = MutableStateFlow<Long>(1) // Предполагаемый ID текущего пользователя
    val currentUserId: StateFlow<Long> = _currentUserId.asStateFlow()

    init {
        loadChats()
    }

    private fun loadChats() {
        // Здесь в реальном приложении будет запрос к базе данных
        // Имитация данных для примера
        _chats.value = generateSampleChats()
    }

    private fun generateSampleChats(): List<ChatItem> {
        val now = Clock.System.now()
        val nowMillis = now.toEpochMilliseconds()

        // Вчерашняя дата
        val yesterday = (now - 1.days).toEpochMilliseconds()

        // Дата от недели назад
        val weekAgo = (now - 7.days).toEpochMilliseconds()

        return listOf(
            ChatItem(
                id = 1,
                type = ChatType.PERSONAL,
                name = "Алексей Иванов",
                description = null,
                creatorId = null,
                createdAt = weekAgo,
                avatarUrl = null,
                avatarLocalPath = null,
                lastMessageId = 100,
                lastMessageText = "Привет! Как дела с проектом?",
                lastMessageTime = nowMillis - 15 * 60 * 1000, // 15 минут назад
                unreadCount = 2,
                lastSyncTime = nowMillis,
                isDraft = false,
                lastDraftText = null,
                members = listOf(
                    ChatMember(1, 1, "member", weekAgo, nowMillis),
                    ChatMember(1, 2, "member", weekAgo, nowMillis)
                )
            ),
            ChatItem(
                id = 2,
                type = ChatType.GROUP,
                name = "Рабочая группа",
                description = "Обсуждение текущих рабочих задач",
                creatorId = 1,
                createdAt = weekAgo,
                avatarUrl = null,
                avatarLocalPath = null,
                lastMessageId = 200,
                lastMessageText = "Напоминаю про встречу завтра в 10:00!",
                lastMessageTime = yesterday,
                unreadCount = 5,
                lastSyncTime = nowMillis,
                isDraft = false,
                lastDraftText = null,
                members = listOf(
                    ChatMember(2, 1, "admin", weekAgo, nowMillis),
                    ChatMember(2, 2, "member", weekAgo, nowMillis),
                    ChatMember(2, 3, "member", weekAgo, nowMillis),
                    ChatMember(2, 4, "member", weekAgo, nowMillis)
                )
            ),
            ChatItem(
                id = 3,
                type = ChatType.PERSONAL,
                name = "Мария Петрова",
                description = null,
                creatorId = null,
                createdAt = weekAgo,
                avatarUrl = null,
                avatarLocalPath = null,
                lastMessageId = 300,
                lastMessageText = "Пришлю документы вечером",
                lastMessageTime = nowMillis - 3 * 60 * 60 * 1000, // 3 часа назад
                unreadCount = 0,
                lastSyncTime = nowMillis,
                isDraft = true,
                lastDraftText = "Спасибо за информацию, буду ждать",
                members = listOf(
                    ChatMember(3, 1, "member", weekAgo, nowMillis),
                    ChatMember(3, 5, "member", weekAgo, nowMillis)
                )
            ),
            ChatItem(
                id = 4,
                type = ChatType.CHANNEL,
                name = "Новости компании",
                description = "Официальный канал корпоративных новостей",
                creatorId = 6,
                createdAt = weekAgo,
                avatarUrl = null,
                avatarLocalPath = null,
                lastMessageId = 400,
                lastMessageText = "Мы открываем новый офис в Москве! Подробности на корпоративном портале.",
                lastMessageTime = nowMillis - 24 * 60 * 60 * 1000, // день назад
                unreadCount = 1,
                lastSyncTime = nowMillis,
                isDraft = false,
                lastDraftText = null,
                members = listOf(
                    ChatMember(4, 1, "member", weekAgo, nowMillis),
                    ChatMember(4, 6, "admin", weekAgo, nowMillis),
                    ChatMember(4, 7, "admin", weekAgo, nowMillis),
                    ChatMember(4, 8, "member", weekAgo, nowMillis),
                    ChatMember(4, 9, "member", weekAgo, nowMillis)
                )
            ),
            ChatItem(
                id = 5,
                type = ChatType.SELF,
                name = "Избранное",
                description = "Сохраненные сообщения",
                creatorId = 1,
                createdAt = weekAgo,
                avatarUrl = null,
                avatarLocalPath = null,
                lastMessageId = 500,
                lastMessageText = "Важные ссылки для проекта: https://example.com/project",
                lastMessageTime = nowMillis - 2 * 24 * 60 * 60 * 1000, // 2 дня назад
                unreadCount = 0,
                lastSyncTime = nowMillis,
                isDraft = false,
                lastDraftText = null,
                members = listOf(
                    ChatMember(5, 1, "admin", weekAgo, nowMillis)
                )
            )
        )
    }

    fun onChatClick(chatId: Long) {
        // Обработка нажатия на чат
        // В реальном приложении здесь может быть навигация к экрану чата
        println("Открытие чата с ID: $chatId")
    }
}