package com.erikproject.messanger.domian.usecase

import com.erikproject.messanger.domian.repository.ChatMembersRepository
import comerikprojectdatabase.Local_chat_members

class GetChatMembers(
    private val repository: ChatMembersRepository
) {
    suspend operator fun invoke(): List<Local_chat_members> {
        return repository.getChatMembers()
    }
}