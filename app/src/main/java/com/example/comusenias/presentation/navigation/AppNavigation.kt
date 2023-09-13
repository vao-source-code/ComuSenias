package com.example.comusenias.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.comusenias.presentation.screen.login.LoginScreen
import com.example.comusenias.presentation.screen.profile.ChangeProfileScreen
import com.example.comusenias.presentation.screen.profile.ProfileScreen
import com.example.comusenias.presentation.screen.register.RegisterScreen
import com.example.comusenias.presentation.splashScreen.SplashScreen

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreen.SplashScreen.route) {
        composable(AppScreen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(AppScreen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(AppScreen.RegisterScreen.route) {
            RegisterScreen(navController = navController , modifier = Modifier.fillMaxSize())
        }
        composable(AppScreen.ProfileScreen.route) {
            ProfileScreen(navController = navController , modifier = Modifier.fillMaxSize())
        }

        composable(
            route = AppScreen.ChangeProfileScreen.route,
            arguments = listOf(navArgument("user"){
                type = NavType.StringType
            })
        ) { user ->
            user.arguments?.getString("user")?.let {
                ChangeProfileScreen(modifier = Modifier.fillMaxSize(), navController=navController, user = it)
            }

        }
    }

}