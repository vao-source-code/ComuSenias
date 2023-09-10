package com.example.comusenias.presentation.navigation

import PermissionCameraScreen
import RequestCameraPermission
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.comusenias.presentation.screen.camera.CameraScreen
import com.example.comusenias.presentation.screen.login.LoginScreen
import com.example.comusenias.presentation.screen.profile.ChangeProfilePasswordScreen
import com.example.comusenias.presentation.screen.profile.ProfileScreen
import com.example.comusenias.presentation.screen.register.RegisterScreen
import com.example.comusenias.presentation.splashScreen.SplashScreen

@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = AppScreen.SplashScreen.route) {
        composable(AppScreen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(AppScreen.MainActivity.route) {
            LoginScreen(navController = navController)
        }
        composable(AppScreen.RegisterScreen.route) {
            RegisterScreen(navController = navController , modifier = Modifier.fillMaxSize())
        }
        composable(AppScreen.ProfileScreen.route) {
            ProfileScreen(navController = navController , modifier = Modifier.fillMaxSize())
        }

        composable(AppScreen.ChangeProfileScreen.route) {
            ChangeProfilePasswordScreen(navController = navController , modifier = Modifier.fillMaxSize())
        }
        composable(AppScreen.CameraScreenPermission.route){
            PermissionCameraScreen(navController = navController)
        }

        composable(AppScreen.CameraScreen.route){
           CameraScreen(navController = navController)
        }




    }

}

fun RequestCameraPermission() {
    TODO("Not yet implemented")
}
