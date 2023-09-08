package com.example.comusenias.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.screen.login.LoginScreen
import com.example.comusenias.presentation.screen.profile.ChangeProfilePasswordScreen
import com.example.comusenias.presentation.screen.profile.ProfileScreen
import com.example.comusenias.presentation.screen.register.RegisterScreen

@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = AppScreen.LoginScreen.route) {
        composable(AppScreen.LoginScreen.route) {
            LoginScreen(navController = navController , modifier = Modifier.fillMaxSize())
        }
        composable(AppScreen.RegisterScreen.route) {
           RegisterScreen(navController = navController , modifier = Modifier.fillMaxSize())
        }

        /* Profile */


        composable(AppScreen.ProfileScreen.route) {
            ProfileScreen(navController = navController , modifier = Modifier.fillMaxSize())
        }

        composable(AppScreen.ChangeProfileScreen.route) {
            ChangeProfilePasswordScreen(navController = navController , modifier = Modifier.fillMaxSize())
        }

    }
}