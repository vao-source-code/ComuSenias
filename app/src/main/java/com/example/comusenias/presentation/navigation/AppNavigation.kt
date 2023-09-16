package com.example.comusenias.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.comusenias.presentation.activities.MainActivity
import com.example.comusenias.presentation.component.bottomBar.ShowBottomBar
import com.example.comusenias.presentation.screen.login.LoginScreen
import com.example.comusenias.presentation.screen.profile.ChangeProfileScreen
import com.example.comusenias.presentation.screen.profile.ProfileScreen
import com.example.comusenias.presentation.screen.register.ChoseYourProfile
import com.example.comusenias.presentation.screen.register.RegisterScreen
import com.example.comusenias.presentation.splashScreen.SplashScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(
    navController: NavHostController,
) {
    Scaffold(
        bottomBar = {
            ShowBottomBar(navController = navController)
        }
    ) { paddingValues ->
        GetNavHost(navController, Modifier.padding(paddingValues))
    }
}

@Composable
private fun GetNavHost(
    navController: NavHostController,
    modifier: Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = AppScreen.LoginScreen.route
    ) {
        composable(AppScreen.LoginScreen.route) {
            LoginScreen(navController = navController, modifier)
        }
        composable(AppScreen.ProfileScreen.route) {
            ProfileScreen(navController = navController, modifier = modifier)
        }
        composable(AppScreen.RegisterScreen.route) {
            RegisterScreen(navController = navController, modifier = modifier)
        }
        composable(AppScreen.ChoseYourProfile.route) {
            ChoseYourProfile(navController = navController, modifier = modifier)
        }
        composable(AppScreen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(AppScreen.MainActivity.route) {
            MainActivity()
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