package com.example.comusenias.presentation.navigation

sealed class AppScreen(val route : String) {

    object LoginScreen : AppScreen("login_screen")
    object RegisterScreen : AppScreen("register_screen")
    object ProfileScreen : AppScreen("profile_screen")
    object SplashScreen : AppScreen("splash_screen")
    object ChangeProfileScreen : AppScreen("change_profile_screen")
    object HomeScreen : AppScreen("home_screen")

}