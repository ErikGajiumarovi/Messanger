package com.erikproject.messanger.domian.repository

import comerikprojectdatabase.Local_chat_members

interface ChatMembersRepository {
    suspend fun getChatMembers(): List<Local_chat_members>
    suspend fun getChatMembersByChatId(chatId: Long): List<Local_chat_members>
}