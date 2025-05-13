package com.erikproject.messanger.domian.usecase

import com.erikproject.messanger.domian.repository.MessagesRepository
import comerikprojectdatabase.Local_messages

class GetMessagesByChatId (
    private val repository: MessagesRepository
) {
    suspend operator fun invoke(chatId: Long): List<Local_messages> {
        return repository.getMessagesByChatId(chatId)
    }
}