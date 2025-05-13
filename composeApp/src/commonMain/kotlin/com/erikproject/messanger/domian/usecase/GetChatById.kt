package com.erikproject.messanger.domian.usecase

import com.erikproject.messanger.domian.repository.ChatsRepository
import comerikprojectdatabase.Local_chats

class GetChatById (
    private val repository: ChatsRepository
) {
    suspend operator fun invoke(chatId: Long): Local_chats? {
        return repository.getChatById(chatId)
    }
}