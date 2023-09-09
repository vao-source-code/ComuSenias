package com.example.comusenias.presentation.navigation

sealed class AppScreen(val route : String) {

    object LoginScreen : AppScreen("login_screen")
    object RegisterScreen : AppScreen("register_screen")

    object ProfileScreen : AppScreen("profile_screen")

    object ChangeProfileScreen : AppScreen("change_profile_screen")

}