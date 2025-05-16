package com.erikproject.messanger.data.repository

import com.erikproject.messanger.domian.repository.ChatMembersRepository
import comerikprojectdatabase.Local_chat_members
import comerikprojectdatabase.Local_chat_membersQueries

import kotlinx.datetime.Clock

class ChatMembersRepositoryImpl(
    db: Local_chat_membersQueries,
) : ChatMembersRepository {
    private val chatMembers = mutableListOf<Local_chat_members>()

    init {
        chatMembers.addAll(db.selectChats().executeAsList())
    }

    override suspend fun getChatMembers(): List<Local_chat_members> {
        return chatMembers
    }

    override suspend fun getChatMembersByChatId(chatId: Long): List<Local_chat_members> {
        return chatMembers.filter { it.chat_id == chatId }
    }

    private fun generateSampleChatsMembers(): List<Local_chat_members> {
        val currentTimeMillis = Clock.System.now().toEpochMilliseconds()

        // Generate sample chat members with realistic data
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


}