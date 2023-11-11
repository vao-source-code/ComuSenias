package com.example.comusenias.presentation.navigation

sealed class AppScreen(val route: String) {

    object LoginScreen : AppScreen("login_screen")
    object RegisterScreen : AppScreen("register_screen")
    object PremiumScreen : AppScreen("premium_screen")
    object ChildrenProfileScreen : AppScreen("children_profile_screen")
    object SplashScreen : AppScreen("splash_screen")
    object MainActivity : AppScreen("main_screen")
    object HomeScreen : AppScreen("home_screen")
    object OnboardingScreen : AppScreen("onboarding_screen")
    object LearnSignScreen : AppScreen("learn_sign/{level}/{subLevel}") {
        fun createRoute(level: String, subLevel: String) = "learn_sign/${level}/${subLevel}"
    }

    object ChoseTheSignPlayScreen : AppScreen("chose_the_sign_play/{level}/{subLevel}") {
        fun createRoute(level: String, subLevel: String) =
            "chose_the_sign_play/${level}/${subLevel}"
    }

    object MakeSignPlayScreen : AppScreen("make_sign_play")
    object InfoMakeSignScreen : AppScreen("info_make_sign_screen")

    object CongratsPlayScreen : AppScreen("congrats_play")
    object InterpretationStatusScreen : AppScreen("Interpretation_status_screen/{path}") {
        fun createRoute(path: String) = "Interpretation_status_screen/${path}"
    }

    object NotificationScreen : AppScreen("notification_screen")

    object ChoseTheLetterPlayScreen : AppScreen("chose_the_letter_play/{level}/{subLevel}") {
        fun createRoute(level: String, subLevel: String) =
            "chose_the_letter_play/${level}/${subLevel}"
    }

    object CameraScreenPermission : AppScreen("camera_screen_permission")

    object CameraScreen : AppScreen("camera_screen")

    object GaleryScreenPermission : AppScreen("galery_screen_permission")

    object GalleryScreen : AppScreen("gallery_screen/{path}") {
        fun createRoute(path: String) = "gallery_screen/${path}"
    }

    object GenerateQRScreen : AppScreen("generate_qr_screen")

    /*-----------------------Specialist Screen -----------------------------------------------*/

    object ProfilePatientScreen : AppScreen("profile_patient_screen/{pacient}") {
        fun passPacient(user: String) = "profile_patient_screen/$user"
    }

    object LectorQRScreen : AppScreen("lector_qr_screen")

    object SendObservationScreen : AppScreen("send_observation_screen/{observation}") {
        fun createRoute(observation: String): String {
            return "send_observation_screen/$observation"
        }
    }

    object SpecialistProfileScreen : AppScreen("specialist_profile_screen")

    object ChangeSpecialistProfileScreen :
        AppScreen("change_profile_patient_screen/{specialist_profile}") {
        fun passProfile(specialistProfile: String): String =
            "change_profile_patient_screen/$specialistProfile"
    }

    object SpecialistScreen : AppScreen("specialist_screen")

}