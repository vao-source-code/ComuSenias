package com.example.comusenias.presentation.navigation

sealed class AppScreen(val route : String) {

    object LoginScreen : AppScreen("login_screen")
    object RegisterScreen : AppScreen("register_screen")

    object PremiumScreen : AppScreen("premium_screen")

    object ProfileScreen : AppScreen("profile_screen")
    object SplashScreen : AppScreen("splash_screen")
    object MainActivity : AppScreen("main_screen")
    object ChangeProfileScreen : AppScreen("change_profile_screen/{user}"){
        fun passUser(user : String) : String{
            return "change_profile_screen/$user"
        }
    }
    object HomeScreen : AppScreen("home_screen")
    object OnboardingScreen : AppScreen("onboarding_screen")
    object ChoseYourProfileScreen : AppScreen("chose_your_profile_screen")
    object EspecialistFormScreen : AppScreen("especialist_screen")
    object ChildFormScreen : AppScreen("child_form_screen")
}