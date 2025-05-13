package com.erikproject.messanger.domian.usecase

import com.erikproject.messanger.domian.repository.MessagesRepository

class GetMessages(
    private val repository: MessagesRepository
) {
    suspend operator fun invoke(): List<comerikprojectdatabase.Local_messages> {
        return repository.getMessages()
    }
}