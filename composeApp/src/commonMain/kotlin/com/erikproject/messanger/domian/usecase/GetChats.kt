package com.erikproject.messanger.domian.usecase

import com.erikproject.messanger.domian.repository.ChatsRepository
import comerikprojectdatabase.Local_chats

class GetChats(
    private val repository: ChatsRepository
) {
    suspend operator fun invoke(): List<Local_chats> {
        return repository.getChats()
    }
}