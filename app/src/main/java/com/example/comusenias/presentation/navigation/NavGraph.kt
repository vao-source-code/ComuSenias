package com.example.comusenias.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.comusenias.presentation.activities.MainActivity
import com.example.comusenias.presentation.component.bottomBar.ShowBottomNavigation
import com.example.comusenias.presentation.screen.home.HomeScreen
import com.example.comusenias.presentation.screen.login.LoginScreen
import com.example.comusenias.presentation.screen.profile.ChangeProfilePasswordScreen
import com.example.comusenias.presentation.screen.profile.ProfileScreen
import com.example.comusenias.presentation.screen.register.RegisterScreen
import com.example.comusenias.presentation.splashScreen.SplashScreen

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreen.LoginScreen.route
    ) {
        composable(AppScreen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(AppScreen.HomeScreen.route) {
            HomeScreen()
        }
        composable(AppScreen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(AppScreen.ProfileScreen.route) {
            ProfileScreen(navController = navController, modifier = Modifier.fillMaxSize())
        }
        composable(AppScreen.RegisterScreen.route) {
            RegisterScreen(navController = navController, modifier = Modifier.fillMaxSize())
        }
        composable(AppScreen.ChangeProfileScreen.route) {
            ChangeProfilePasswordScreen(
                navController = navController,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(AppScreen.MainActivity.route) {
            MainActivity()
        }
    }
    ShowBottomNavigation(navController = navController)
}