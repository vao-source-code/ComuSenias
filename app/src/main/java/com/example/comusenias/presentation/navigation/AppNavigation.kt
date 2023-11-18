package com.example.comusenias.presentation.navigation

import PermissionCameraScreen
import PermissionGaleryScreen
import android.annotation.SuppressLint
import android.net.Uri
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
import com.example.comusenias.presentation.component.specialist.SpecialistDetailsScreen
import com.example.comusenias.presentation.screen.gallery.GalleryScreen
import com.example.comusenias.presentation.screen.camera.CameraScreen
import com.example.comusenias.presentation.screen.camera.TestCamera
import com.example.comusenias.presentation.screen.camera.VideoScreen
import com.example.comusenias.presentation.screen.gameAction.ChoseTheLetterPlayScreen
import com.example.comusenias.presentation.screen.home.HomeScreen
import com.example.comusenias.presentation.screen.gameAction.ChoseTheSignPlayScreen
import com.example.comusenias.presentation.screen.gameAction.CongratsPlayScreen
import com.example.comusenias.presentation.screen.gameAction.InterpretationStatusScreen
import com.example.comusenias.presentation.screen.gameAction.LearnSignScreen
import com.example.comusenias.presentation.screen.gameAction.MakeSignPlayScreen
import com.example.comusenias.presentation.screen.premiun.PremiunScreen
import com.example.comusenias.presentation.screen.profile.ChangeProfileScreen
import com.example.comusenias.presentation.screen.profile.ProfileScreen
import com.example.comusenias.presentation.screen.specialist.SpecialistHomeExample
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(
    navController: NavHostController,
) {
    Scaffold { paddingValues ->
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
        startDestination = AppScreen.SplashScreen.route
    ) {

        authNavGraph(navController = navController, modifier = modifier)

        composable(AppScreen.ProfileScreen.route) {
            ProfileScreen(navController = navController, modifier = modifier)
        }
        composable(AppScreen.HomeScreen.route) {
            HomeScreen(navController = navController, modifier = modifier)
        }

        composable(AppScreen.SplashScreen.route) {
            // SplashScreen(navController)
            TestCamera(navController = navController)

        }

        composable(AppScreen.PremiumScreen.route) {
            PremiunScreen(navController = navController, modifier = modifier)
        }

        //Permission Camera
        composable(AppScreen.CameraScreenPermission.route) {
            PermissionCameraScreen(navController = navController)
        }
        composable(AppScreen.CameraScreen.route) {
            if(true){
                VideoScreen(navController = navController)
            }
            //CameraScreen(navController = navController)
        }


        //Permission Gallery
        composable(AppScreen.GaleryScreenPermission.route){
            PermissionGaleryScreen(navController = navController)
        }


        composable(
            AppScreen.GalleryScreen.route, arguments = listOf(
                navArgument("path") {
                    type = NavType.StringType
                }
            )
        ) {
            val path = it.arguments?.getString("path") ?: EMPTY_STRING
            GalleryScreen(navController = navController,path = path)
        }





        composable(
            route = AppScreen.ChangeProfileScreen.route,
            arguments = listOf(navArgument("user") {
                type = NavType.StringType
            })
        ) { user ->
            user.arguments?.getString("user")?.let {
                ChangeProfileScreen(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController,
                    user = it
                )
            }
        }

        composable(AppScreen.SpecialistScreen.route) {
            SpecialistHomeExample(navController = navController, modifier = modifier)
        }

        composable(AppScreen.SpecialistDetailsScreen.route) {
            SpecialistDetailsScreen(navController = navController, modifier = modifier)
        }
        composable(AppScreen.LearnSignScreen.route) {
            LearnSignScreen(navController = navController, modifier = modifier)
        }
        composable(AppScreen.ChoseTheSignPlayScreen.route) {
            ChoseTheSignPlayScreen(navController = navController, modifier = modifier)
        }
        composable(AppScreen.MakeSignPlayScreen.route) {
            MakeSignPlayScreen(navController = navController, modifier = modifier)
        }
        composable(AppScreen.InterpretationStatusScreen.route) {
            InterpretationStatusScreen(navController = navController, modifier = modifier)
        }
        composable(AppScreen.CongratsPlayScreen.route) {
            CongratsPlayScreen(navController = navController, modifier = modifier)
        }
        composable(AppScreen.ChoseTheLetterPlayScreen.route) {
            ChoseTheLetterPlayScreen(navController = navController)
        }


    }
}