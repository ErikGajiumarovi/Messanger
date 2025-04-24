package com.erikproject.messanger.data.local.database

import app.cash.sqldelight.db.SqlDriver
import com.erikproject.database.MessengerDatabase
import com.erikproject.messanger.expect_actual.getDriver
import com.erikproject.messanger.expect_actual.getPathToDBs

class AppDatabase private constructor(
    private val driver: SqlDriver
) {
    companion object {
        fun create(): AppDatabase {
            return AppDatabase(getDriver(getPathToDBs().resolve("messenger.db")))
        }
    }

    private val internalDb by lazy { MessengerDatabase(driver) }

    init {
        MessengerDatabase.Schema.create(driver)
    }

    fun close() {
        driver.close()
    }

    val chatMembersQueries get() = internalDb.local_chat_membersQueries
    val chatsQueries get() = internalDb.local_chatsQueries
    val contactsQueries get() = internalDb.local_contactsQueries
    val messageStatusesQueries get() = internalDb.local_message_statusesQueries
    val messagesQueries get() = internalDb.local_messagesQueries
    val userQueries get() = internalDb.local_userQueries
    val mediaCacheQueries get() = internalDb.media_cacheQueries
    val syncInfoQueries get() = internalDb.sync_infoQueries
}