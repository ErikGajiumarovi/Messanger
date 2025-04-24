package com.erikproject.messanger.expect_actual

import android.content.Context
import com.erikproject.messanger.platform_specific.KmpInitializer.applicationContext
import okio.Path
import okio.Path.Companion.toPath

actual fun getPath(): Path {
    val ctx = applicationContext as? Context
        ?: throw IllegalArgumentException("Context is required on Android")
    return ctx.dataDir.absolutePath.toPath()
}

actual fun getPathToDBs(): Path {
    return getPath().toString().toPath().resolve("databases")
}