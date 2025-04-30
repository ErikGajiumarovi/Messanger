package com.erikproject.messanger.data.storage

import com.erikproject.messanger.expect_actual.getPath
import com.erikproject.messanger.expect_actual.getPathToDBs
import okio.FileSystem
import okio.Path
import okio.Path.Companion.toPath
import okio.SYSTEM

object FileUtils {
    fun writeFile(path: String, content: String) {
        FileSystem.SYSTEM.write(path.toPath()) {
            writeUtf8(content)
        }
    }

    // Чтение текста из файла
    fun readFile(path: Path): String {
        return FileSystem.SYSTEM.read(path) {
            readUtf8()
        }
    }

    fun deleteFile(path: Path) {
        return FileSystem.SYSTEM.delete(path)
    }

    // Рекурсивное удаление папки или файла
    fun deleteDirectory(path: Path, rootDirectory: Boolean) {
        if (!exists(path)) return

        if (FileSystem.SYSTEM.metadataOrNull(path)?.isDirectory == true) {
            FileSystem.SYSTEM.list(path).forEach {
                deleteDirectory(it, true) // Для вложенных элементов всегда удаляем полностью
            }
        }
        if (rootDirectory) {
            FileSystem.SYSTEM.delete(path, mustExist = false)
        }
    }

    fun mkdir(folder: Path) {
        FileSystem.SYSTEM.createDirectory(folder)
    }

    fun exists(path: Path): Boolean {
        return FileSystem.SYSTEM.exists(path)
    }

    private fun ls(path: Path): List<String> {
        val fileSystem = FileSystem.SYSTEM

        return if (fileSystem.exists(path) && fileSystem.metadata(path).isDirectory) {
            fileSystem.list(path)
                .map { it.name }
                .filterNot { it.startsWith(".") }
        } else {
            emptyList()
        }
    }

    fun lsPaths(path: Path): List<Path> {
        val fileSystem = FileSystem.SYSTEM

        return if (fileSystem.exists(path) && fileSystem.metadata(path).isDirectory) {
            fileSystem.list(path)
                .filterNot { it.name.startsWith(".") }
        } else {
            emptyList()
        }
    }

    fun getDatabaseFiles(): List<String> {
        val path = getPathToDBs()
        val dbs = ls(path).filter { it.endsWith(".db") }
        println("Looking for db's in ${path} Found ${dbs}")
        return dbs
    }
}
