package com.example.comusenias.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.comusenias.presentation.component.login.ResetPasswordScreen
import com.example.comusenias.presentation.screen.login.LoadingLoginScreen
import com.example.comusenias.presentation.screen.login.LoginScreen
import com.example.comusenias.presentation.screen.onboarding.OnBoardingScreen
import com.example.comusenias.presentation.screen.register.ChildFormScreen
import com.example.comusenias.presentation.screen.register.RegisterScreen
import com.example.comusenias.presentation.screen.register.SpecialistFormScreen

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.authNavGraph(navController: NavHostController, modifier: Modifier) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.LoginScreen.route
    ) {

        composable(route = AuthScreen.LoginScreen.route) {
            LoginScreen(navController = navController, modifier = modifier)
        }
        composable(route = AuthScreen.LoadingScreen.route) {
            LoadingLoginScreen(navController = navController, modifier = modifier)
        }
        composable(route = AuthScreen.RegisterScreen.route) {
            RegisterScreen(navController = navController, modifier = modifier)
        }
        composable(AuthScreen.ResetPasswordScreen.route) {
            ResetPasswordScreen(navController = navController, modifier = modifier)
        }
        composable(AppScreen.OnboardingScreen.route) {
            OnBoardingScreen(navController = navController, modifier = modifier)
        }
        composable(AppScreen.RegisterScreen.route) {
            RegisterScreen(navController = navController, modifier = modifier)
        }
        composable(AuthScreen.SpecialistFormScreen.route) {
            SpecialistFormScreen(navController = navController, modifier = modifier)
        }
        composable(AuthScreen.ChildFormScreen.route) {
            ChildFormScreen(navController = navController, modifier = modifier)
        }
    }
}

sealed class AuthScreen(val route: String) {
    object LoginScreen : AuthScreen("login_screen")
    object RegisterScreen : AuthScreen("register_screen")
    object ResetPasswordScreen : AuthScreen("reset_password_screen")
    object LoadingScreen : AuthScreen("loading_screen")
    object SpecialistFormScreen : AppScreen("specialist_form_screen")
    object ChildFormScreen : AppScreen("child_form_screen")

}