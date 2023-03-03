package com.example.pinterestclone

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pinterestclone.homeScreen.HomeScreen

@Composable
fun CatalogNavHost() {

    val navController = rememberNavController()

    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(
                modifier = Modifier
            )
        }
    }
}

sealed class Screen(
    val route: String,
) {
    object HomeScreen : Screen("home_screen")
}
