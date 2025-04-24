package com.erikproject.messanger.expect_actual

import app.cash.sqldelight.db.SqlDriver
import okio.Path

expect fun getDriver(pathToDB: Path): SqlDriver