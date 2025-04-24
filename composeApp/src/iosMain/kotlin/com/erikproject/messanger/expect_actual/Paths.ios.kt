package com.erikproject.messanger.expect_actual

import okio.Path
import okio.Path.Companion.toPath
import platform.Foundation.NSApplicationSupportDirectory
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSUserDomainMask

actual fun getPath(): Path {
    val paths = NSSearchPathForDirectoriesInDomains(
        NSApplicationSupportDirectory,
        NSUserDomainMask,
        true
    )
    return paths.first().toString().toPath()
}

actual fun getPathToDBs(): Path {
    return getPath().toString().toPath().resolve("databases")
}