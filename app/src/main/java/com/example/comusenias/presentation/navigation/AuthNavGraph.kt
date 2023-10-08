package com.example.comusenias.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.comusenias.presentation.screen.gameAction.ChoseTheLetterPlayScreen
import com.example.comusenias.presentation.screen.login.LoginScreen
import com.example.comusenias.presentation.screen.onboarding.OnBoardingScreen
import com.example.comusenias.presentation.screen.register.ChildFormScreen
import com.example.comusenias.presentation.screen.register.ChoseYourProfileScreen
import com.example.comusenias.presentation.screen.register.SpecialistFormScreen
import com.example.comusenias.presentation.screen.register.RegisterScreen


fun NavGraphBuilder.authNavGraph(navController: NavHostController , modifier: Modifier) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.LoginScreen.route
    ) {

        composable(route = AuthScreen.LoginScreen.route) {
            LoginScreen(navController= navController , modifier = modifier)
        }

        composable(route = AuthScreen.RegisterScreen.route) {
            RegisterScreen(navController= navController, modifier = modifier)
        }
        composable(AppScreen.OnboardingScreen.route) {
            OnBoardingScreen(navController = navController, modifier = modifier)
        }
        composable(AppScreen.RegisterScreen.route) {
            RegisterScreen(navController = navController, modifier = modifier)
        }
        composable(AppScreen.ChoseYourProfileScreen.route) {
            ChoseYourProfileScreen(navController = navController, modifier = modifier)
        }
        composable(AppScreen.EspecialistFormScreen.route) {
            SpecialistFormScreen(navController = navController, modifier = modifier)
        }
        composable(AppScreen.ChildFormScreen.route) {
            ChildFormScreen(navController = navController, modifier = modifier)
        }

    }
}

sealed class AuthScreen(val route: String) {

    object LoginScreen : AuthScreen("login_screen")
    object RegisterScreen : AuthScreen("register_screen")

}