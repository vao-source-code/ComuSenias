package com.example.comusenias.presentation.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.comusenias.presentation.activities.MainActivity
import com.example.comusenias.presentation.screen.gameAction.ChoseTheLetterPlayScreen
import com.example.comusenias.presentation.screen.gameAction.ChoseTheSignPlayScreen
import com.example.comusenias.presentation.screen.gameAction.CongratsPlayScreen
import com.example.comusenias.presentation.screen.gameAction.InterpretationStatusScreen
import com.example.comusenias.presentation.screen.gameAction.LearnSignScreen
import com.example.comusenias.presentation.screen.gameAction.MakeSignPlayScreen
import com.example.comusenias.presentation.screen.home.HomeScreen
import com.example.comusenias.presentation.screen.notification.NotificationScreen
import com.example.comusenias.presentation.screen.premiun.PremiunScreen
import com.example.comusenias.presentation.screen.profile.ChangeProfileScreen
import com.example.comusenias.presentation.screen.profile.ProfileScreen
import com.example.comusenias.presentation.screen.specialist.ProfilePatientScreen
import com.example.comusenias.presentation.screen.specialist.SpecialistScreen
import com.example.comusenias.presentation.splashScreen.SplashScreen

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavigation(
    navController: NavHostController,
) {
    Scaffold { paddingValues ->
        GetNavHost(navController, Modifier.padding(paddingValues))
    }
}

@RequiresApi(Build.VERSION_CODES.O)
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
            SplashScreen(navController)
        }

        composable(AppScreen.PremiumScreen.route) {
            PremiunScreen(navController = navController, modifier = modifier)
        }
        composable(AppScreen.MainActivity.route) {
            MainActivity()
        }
        composableChangeProfile(navController)

        composable(AppScreen.SpecialistScreen.route) {
            SpecialistScreen(navController = navController, modifier = modifier)
        }

        composable(AppScreen.ProfilePatientScreen.route) {
            ProfilePatientScreen(navController = navController, modifier = modifier)
        }

        composableLearnSign(navController, modifier)

        composableChoseTheSignPlay(navController)

        composable(AppScreen.MakeSignPlayScreen.route) {
            MakeSignPlayScreen(navController = navController, modifier = modifier)
        }
        composable(AppScreen.InterpretationStatusScreen.route) {
            InterpretationStatusScreen(navController = navController, modifier = modifier)
        }
        composable(AppScreen.CongratsPlayScreen.route) {
            CongratsPlayScreen(navController = navController, modifier = modifier)
        }
        composableChoseTheLetterPlay(navController)
    }
}

fun NavGraphBuilder.composableChangeProfile(navController: NavHostController) {
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
}

private fun NavGraphBuilder.composableLearnSign(
    navController: NavHostController,
    modifier: Modifier
) {
    composable(
        AppScreen.LearnSignScreen.route,
        arguments = listOf(navArgument("subLevel") {
            type = NavType.StringType
        })
    ) { subLevel ->
        subLevel.arguments?.getString("subLevel")?.let {
            LearnSignScreen(navController = navController, modifier = modifier, subLevel = it)
        }
    }
}

private fun NavGraphBuilder.composableChoseTheLetterPlay(
    navController: NavHostController,
) {
    composable(
        AppScreen.ChoseTheLetterPlayScreen.route,
        arguments = listOf(navArgument("subLevel") {
            type = NavType.StringType
        })
    ) { subLevel ->
        subLevel.arguments?.getString("subLevel")?.let {
            ChoseTheLetterPlayScreen(navController = navController, subLevel = it)
        }
    }
}

@SuppressLint("ComposableDestinationInComposeScope")
private fun NavGraphBuilder.composableChoseTheSignPlay(
    navController: NavHostController,
) {
    composable(
        AppScreen.ChoseTheSignPlayScreen.route,
        arguments = listOf(navArgument("subLevel") {
            type = NavType.StringType
        })
    ) { subLevel ->
        subLevel.arguments?.getString("subLevel")?.let {
            ChoseTheSignPlayScreen(navController = navController, subLevel = it)
        }
        composable(AppScreen.NotificationScreen.route) {
            NotificationScreen(navController = navController)
        }
    }
}