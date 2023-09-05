package com.example.comusenias.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.example.comusenias.presentation.screen.login.LoginScreen
import com.example.comusenias.presentation.splashScreen.SplashScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    /*
        NavHost(navController = navController, startDestination = AppScreen.LoginScreen.route) {
            composable(AppScreen.LoginScreen.route) {
                LoginScreen(navController = navController, modifier = Modifier.fillMaxSize())
            }
            composable(AppScreen.RegisterScreen.route) {
                RegisterScreen(navController = navController, modifier = Modifier.fillMaxSize())
            }
            composable(AppScreen.ProfileScreen.route) {
                ProfileScreen(navController = navController, modifier = Modifier.fillMaxSize())
            }
        }*/
    NavHost(navController = navController, startDestination = AppScreen.SplashScreen.route) {
        composable(AppScreen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(AppScreen.MainActivity.route) {
            LoginScreen(navController = navController, modifier = Modifier.fillMaxSize())
        }
    }
}