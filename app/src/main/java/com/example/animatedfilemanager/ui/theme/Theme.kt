package com.example.animatedfilemanager.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFBB86FC),  // purple_200
    primaryContainer = Color(0xFF3700B3),  // purple_700
    secondary = Color(0xFF03DAC5),  // teal_200
    secondaryContainer = Color(0xFF018786),  // teal_700
    onPrimary = Color.White,
    onSecondary = Color.Black
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EE),  // purple_500
    primaryContainer = Color(0xFFBB86FC),  // purple_200
    secondary = Color(0xFF03DAC5),  // teal_200
    secondaryContainer = Color(0xFF018786),  // teal_700
    onPrimary = Color.White,
    onSecondary = Color.Black
)

@Composable
fun AnimatedFileManagerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
