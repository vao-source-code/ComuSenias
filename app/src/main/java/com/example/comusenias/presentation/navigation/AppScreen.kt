package com.example.comusenias.presentation.navigation

sealed class AppScreen(val route : String) {

    object LoginScreen : AppScreen("login_screen")
    object RegisterScreen : AppScreen("register_screen")

    object ProfileScreen : AppScreen("profile_screen")

    object ChangeProfilePasswordScreen : AppScreen("change_profile_password_screen")

}