package com.erikproject.messanger.expect_actual

import okio.Path
import okio.Path.Companion.toPath
import java.io.File
import java.util.Locale

actual fun getPath(): Path {
    return getAppDataPath().toPath()
}

actual fun getPathToDBs(): Path {
    return getPath().toString().toPath().resolve("databases")
}

fun getAppDataPath(): String {
    val osName = getOsName()
    val userHome = System.getProperty("user.home")
    val appName = "MessengerApp"

    val appDataPath = if (osName.contains("win")) {
        System.getenv("LOCALAPPDATA") + "\\" + appName + "\\"
    } else if (osName.contains("mac")) {
        "$userHome/Library/Application Support/$appName/"
    } else if (osName.contains("nux") || osName.contains("nix")) {
        "$userHome/.local/share/ARGONAUT_MP/"
    } else {
        throw UnsupportedOperationException("Unsupported operating ${osName}")
    }
    // Проверяем, существует ли директория, если нет — создаём её
    val dir = File(appDataPath)
    if (!dir.exists()) {
        dir.mkdirs() // Создать все необходимые директории
    }
    return appDataPath
}

private fun getOsName(): String {
    val os = System.getProperty("os.name").lowercase(Locale.getDefault())

    return when {
        os.contains("win") -> "win"
        os.contains("mac") -> "macos"
        os.contains("nux") || os.contains("nix") -> "linux"
        else -> throw UnsupportedOperationException("Unsupported operating system: $os")
    }
}

