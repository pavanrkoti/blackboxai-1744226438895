package com.example.animatedfilemanager.utils

import android.content.Context
import com.example.animatedfilemanager.model.FileItem
import java.io.File

object FileUtils {
    fun getFilesInDirectory(directory: File): List<FileItem> {
        return directory.listFiles()?.map { file ->
            FileItem.fromFile(file)
        }?.sortedWith(compareBy({ !it.isDirectory }, { it.name })) ?: emptyList()
    }

    fun createDirectory(path: String, name: String): Boolean {
        return File(path, name).mkdir()
    }

    fun deleteFile(path: String): Boolean {
        return File(path).deleteRecursively()
    }

    fun renameFile(path: String, newName: String): Boolean {
        val file = File(path)
        val newFile = File(file.parent, newName)
        return file.renameTo(newFile)
    }

    fun copyFile(sourcePath: String, destPath: String): Boolean {
        val source = File(sourcePath)
        val dest = File(destPath)
        return try {
            source.copyTo(dest, overwrite = true)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getFileCount(directory: File): Int {
        return directory.list()?.size ?: 0
    }

    fun getStoragePermissionStatus(context: Context): Boolean {
        // Will implement proper permission check later
        return true
    }
}
