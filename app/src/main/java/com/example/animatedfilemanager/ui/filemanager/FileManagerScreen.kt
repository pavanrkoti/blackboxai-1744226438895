package com.example.animatedfilemanager.ui.filemanager

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.animatedfilemanager.model.FileItem
import com.example.animatedfilemanager.utils.FileUtils
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FileManagerScreen(
    onSettingsClick: () -> Unit
) {
    val context = LocalContext.current
    val files = remember { mutableStateOf(emptyList<FileItem>()) }
    val selectedFile = remember { mutableStateOf<FileItem?>(null) }
    val showFileDialog = remember { mutableStateOf(false) }
    val showRenameDialog = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        files.value = FileUtils.getFilesInDirectory(context.getExternalFilesDir(null) ?: context.filesDir)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("File Manager") },
                actions = {
                    IconButton(onClick = onSettingsClick) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn(
                modifier = Modifier.padding(16.dp)
            ) {
                items(files.value) { file ->
                    FileItemCard(
                        file = file,
                        modifier = Modifier.padding(vertical = 4.dp),
                        onClick = {
                            selectedFile.value = file
                            showFileDialog.value = true
                        }
                    )
                }
            }

            if (showFileDialog.value && selectedFile.value != null) {
                FileOperationsDialog(
                    selectedFile = selectedFile.value!!.name,
                    onDismiss = { showFileDialog.value = false },
                    onRename = { 
                        showFileDialog.value = false
                        showRenameDialog.value = true
                    },
                    onDelete = {
                        FileUtils.deleteFile(selectedFile.value!!.path)
                        showFileDialog.value = false
                        // Refresh files
                        files.value = FileUtils.getFilesInDirectory(context.getExternalFilesDir(null) ?: context.filesDir)
                    },
                    onShare = {
                        // Will implement sharing later
                        showFileDialog.value = false
                    }
                )
            }

            if (showRenameDialog.value && selectedFile.value != null) {
                RenameDialog(
                    currentName = selectedFile.value!!.name,
                    onDismiss = { showRenameDialog.value = false },
                    onConfirm = { newName ->
                        FileUtils.renameFile(selectedFile.value!!.path, newName)
                        showRenameDialog.value = false
                        // Refresh files
                        files.value = FileUtils.getFilesInDirectory(context.getExternalFilesDir(null) ?: context.filesDir)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FileItemCard(
    file: FileItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier.clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                imageVector = file.icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = file.name,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = if (file.isDirectory) {
                        "Directory"
                    } else {
                        "${file.size / 1024} KB • ${file.lastModified.toLocaleString()}"
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
