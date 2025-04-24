package com.erikproject.messanger.expect_actual

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.erikproject.database.MessengerDatabase
import com.erikproject.messanger.platform_specific.KmpInitializer.applicationContext
import okio.Path

actual fun getDriver(pathToDB: Path): SqlDriver {
    return AndroidSqliteDriver(MessengerDatabase.Schema, applicationContext, pathToDB.toString())
}