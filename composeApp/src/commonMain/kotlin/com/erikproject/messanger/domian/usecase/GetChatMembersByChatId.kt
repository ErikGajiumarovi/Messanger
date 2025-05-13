package com.erikproject.messanger.domian.usecase

import com.erikproject.messanger.domian.repository.ChatMembersRepository
import comerikprojectdatabase.Local_chat_members

class GetChatMembersByChatId(
    private val repository: ChatMembersRepository
) {
    suspend operator fun invoke(chatId: Long): List<Local_chat_members> {
        return repository.getChatMembersByChatId(chatId)
    }
}