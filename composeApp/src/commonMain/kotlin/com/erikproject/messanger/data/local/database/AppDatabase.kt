package com.erikproject.messanger.data.local.database

import app.cash.sqldelight.db.SqlDriver
import com.erikproject.database.MessengerDatabase

class AppDatabase(
    private val driver: SqlDriver
) {
    private lateinit var directDelight: MessengerDatabase
    init {
        MessengerDatabase.Schema.create(driver)
        directDelight = MessengerDatabase(driver)
    }
    fun close() {
        driver.close()
    }



}