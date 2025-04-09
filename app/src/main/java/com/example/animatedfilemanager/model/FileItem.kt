package com.example.animatedfilemanager.model

import java.io.File
import java.util.Date

import androidx.compose.material3.icons.Icons
import androidx.compose.material3.icons.filled.Folder
import androidx.compose.material3.icons.filled.Description
import androidx.compose.material3.icons.filled.PictureAsPdf
import androidx.compose.material3.icons.filled.Image
import androidx.compose.material3.icons.filled.AudioFile
import androidx.compose.material3.icons.filled.Videocam
import androidx.compose.material3.icons.filled.InsertDriveFile
import androidx.compose.ui.graphics.vector.ImageVector

data class FileItem(
    val name: String,
    val path: String,
    val isDirectory: Boolean,
    val size: Long,
    val lastModified: Date,
    val icon: ImageVector = when {
        isDirectory -> Icons.Filled.Folder
        name.endsWith(".txt") -> Icons.Filled.Description
        name.endsWith(".pdf") -> Icons.Filled.PictureAsPdf
        name.endsWith(".jpg", ignoreCase = true) ||
        name.endsWith(".png", ignoreCase = true) -> Icons.Filled.Image
        name.endsWith(".mp3", ignoreCase = true) ||
        name.endsWith(".wav", ignoreCase = true) -> Icons.Filled.AudioFile
        name.endsWith(".mp4", ignoreCase = true) ||
        name.endsWith(".mov", ignoreCase = true) -> Icons.Filled.Videocam
        else -> Icons.Filled.InsertDriveFile
    }
) {
    companion object {
        fun fromFile(file: File): FileItem {
            return FileItem(
                name = file.name,
                path = file.absolutePath,
                isDirectory = file.isDirectory,
                size = file.length(),
                lastModified = Date(file.lastModified())
            )
        }
    }
}
