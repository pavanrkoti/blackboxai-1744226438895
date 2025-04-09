package com.example.animatedfilemanager

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animatedfilemanager.ui.filemanager.FileManagerScreen
import com.example.animatedfilemanager.ui.settings.SettingsScreen

@Composable
fun FileManagerApp() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "file_manager"
    ) {
        composable("file_manager") {
            FileManagerScreen(
                onSettingsClick = { navController.navigate("settings") }
            )
        }
        composable("settings") {
            SettingsScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
