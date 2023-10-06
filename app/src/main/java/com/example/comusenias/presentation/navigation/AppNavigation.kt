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
import com.example.comusenias.presentation.component.specialist.SpecialistDetailsScreen
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
import com.example.comusenias.presentation.splashScreen.SplashScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(
    navController: NavHostController,
) {
    Scaffold{ paddingValues ->
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

        authNavGraph(navController = navController , modifier = modifier)

        composable(AppScreen.ProfileScreen.route) {
            ProfileScreen(navController = navController, modifier = modifier)
        }
        composable(AppScreen.HomeScreen.route) {
            HomeScreen(navController = navController, modifier = modifier)
        }

        composable(AppScreen.SplashScreen.route) {
            SplashScreen(navController)
        }

        composable(AppScreen.PremiumScreen.route) {
            PremiunScreen(navController= navController , modifier = modifier)
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
                ChangeProfileScreen(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController,
                    user = it
                )
            }

        }
        composable(AppScreen.HomeScreen.route) {
            HomeScreen(navController = navController, modifier = modifier)
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
    }
}