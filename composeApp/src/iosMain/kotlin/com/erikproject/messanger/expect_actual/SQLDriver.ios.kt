package com.erikproject.messanger.expect_actual

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.erikproject.database.MessengerDatabase
import okio.Path

actual fun getDriver(pathToDB: Path): SqlDriver {
    return NativeSqliteDriver(
        schema = MessengerDatabase.Schema,
        name = pathToDB.name
    )
}