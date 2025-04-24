package com.erikproject.messanger.expect_actual

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import okio.Path

actual fun getDriver(pathToDB: Path): SqlDriver {
    return JdbcSqliteDriver("jdbc:sqlite:$pathToDB")
}