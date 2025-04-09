package com.example.animatedfilemanager.ui.filemanager

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.animatedfilemanager.utils.AnimationUtils

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FileOperationsDialog(
    selectedFile: String,
    onDismiss: () -> Unit,
    onRename: (String) -> Unit,
    onDelete: () -> Unit,
    onShare: () -> Unit
) {
    AnimationUtils.FadeAnimation(visible = true) {
        Dialog(onDismissRequest = onDismiss) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .width(280.dp)
                    .padding(16.dp)
            ) {
                Column {
                    Text(
                        text = "File Options",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(16.dp)
                    )
                    Divider()
                    IconTextButton(
                        icon = Icons.Default.Edit,
                        text = "Rename",
                        onClick = {
                            onDismiss()
                            onRename(selectedFile)
                        }
                    )
                    IconTextButton(
                        icon = Icons.Default.Delete,
                        text = "Delete",
                        onClick = onDelete
                    )
                    IconTextButton(
                        icon = Icons.Default.Share,
                        text = "Share",
                        onClick = onShare
                    )
                }
            }
        }
    }
}

@Composable
private fun IconTextButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(icon, contentDescription = null)
        Spacer(Modifier.width(16.dp))
        Text(text)
    }
}
