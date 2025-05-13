package com.erikproject.messanger.domian.usecase

import com.erikproject.messanger.domian.repository.ChatsRepository
import com.erikproject.messanger.domian.repository.MessagesRepository
import com.erikproject.messanger.utils.Time
import comerikprojectdatabase.Local_messages
import kotlinx.datetime.Clock

class SendMessage (
    private val messagesRepository: MessagesRepository,
    private val chatsRepository: ChatsRepository
) {
    suspend operator fun invoke(
        chatId: Long,
        text: String,
        currentUserId: Long
    ) {
        val now = Clock.System.now().toEpochMilliseconds()
        val newMessageId = Time.getTimeMills()

        val message = Local_messages(
            id = newMessageId,
            server_id = null,
            chat_id = chatId,
            sender_id = currentUserId,
            text = text,
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

        messagesRepository.addMessage(message)
        chatsRepository.updateLastMessage(
            chatId = chatId,
            lastMessageId = newMessageId,
            lastMessageText = text,
            lastMessageTime = now
        )

        messagesRepository.updateMessageStatus(
            messageId = newMessageId,
            sendStatus = "sent",
            deliveryStatus = "delivered",
            syncStatus = "synced"
        )
    }
}