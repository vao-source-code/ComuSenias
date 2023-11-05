package com.example.comusenias.presentation.navigation

import PermissionCameraScreen
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.comusenias.presentation.activities.MainActivity
import com.example.comusenias.presentation.screen.camera.CameraScreen
import com.example.comusenias.presentation.screen.gallery.GalleryScreen
import com.example.comusenias.presentation.screen.gameAction.ChoseTheLetterPlayScreen
import com.example.comusenias.presentation.screen.gameAction.ChoseTheSignPlayScreen
import com.example.comusenias.presentation.screen.gameAction.CongratsPlayScreen
import com.example.comusenias.presentation.screen.gameAction.InterpretationStatusScreen
import com.example.comusenias.presentation.screen.gameAction.LearnSignScreen
import com.example.comusenias.presentation.screen.gameAction.MakeSignPlayScreen
import com.example.comusenias.presentation.screen.home.HomeScreen
import com.example.comusenias.presentation.screen.notification.NotificationScreen
import com.example.comusenias.presentation.screen.premiun.PremiunScreen
import com.example.comusenias.presentation.screen.profile.ChildrenProfileScreen
import com.example.comusenias.presentation.screen.qr.GenerateQRScreen
import com.example.comusenias.presentation.screen.qr.LectorQRScreen
import com.example.comusenias.presentation.screen.specialist.ProfilePatientScreen
import com.example.comusenias.presentation.screen.specialist.SendObservationScreen
import com.example.comusenias.presentation.screen.specialist.SpecialistScreen
import com.example.comusenias.presentation.splashScreen.SplashScreen
import com.example.comusenias.presentation.ui.theme.CHILDREN_OBSERVATION
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.example.comusenias.presentation.ui.theme.PACIENT
import com.example.comusenias.presentation.ui.theme.SUB_LEVEL
import com.example.comusenias.presentation.view_model.LevelViewModel
import com.google.firebase.analytics.FirebaseAnalytics.Param.LEVEL

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
    val levelViewModel: LevelViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = AppScreen.SplashScreen.route
    ) {

        authNavGraph(navController = navController, modifier = modifier)

        composable(AppScreen.SplashScreen.route) {
            SplashScreen(navController)
        }

        /*------------Children-----------------------*/

        composable(AppScreen.ChildrenProfileScreen.route) {
            ChildrenProfileScreen(navController = navController, modifier = modifier)
        }
        composable(AppScreen.HomeScreen.route) {
            HomeScreen(
                navController = navController,
                levelViewModel = levelViewModel
            )
        }

        composable(AppScreen.PremiumScreen.route) {
            PremiunScreen(navController = navController, modifier = modifier)
        }
        composable(AppScreen.MainActivity.route) {
            MainActivity()
        }

        composableLearnSign(navController, levelViewModel)

        composableChoseTheSignPlay(navController, levelViewModel)

        composable(AppScreen.MakeSignPlayScreen.route) {
            MakeSignPlayScreen(
                navController = navController,
                modifier = modifier,
                levelViewModel = levelViewModel
            )

        }
        composable(AppScreen.InterpretationStatusScreen.route, arguments = listOf(
            navArgument("path") {
                type = NavType.StringType
            }
        )
        ) {
            val path = it.arguments?.getString("path") ?: EMPTY_STRING
            InterpretationStatusScreen(
                navController = navController,
                modifier = modifier,
                path = path
            )
        }
        composable(AppScreen.CongratsPlayScreen.route) {
            CongratsPlayScreen(navController = navController, modifier = modifier)
        }
        composableChoseTheLetterPlay(navController, levelViewModel)

        composable(AppScreen.NotificationScreen.route) {
            NotificationScreen(navController = navController)
        }

        composable(AppScreen.CameraScreen.route) {
            CameraScreen(navController = navController, levelViewModel = levelViewModel)
        }

        //Permission Camera
        composable(AppScreen.CameraScreenPermission.route) {
            PermissionCameraScreen(navController = navController)
        }


        //Permission Gallery
        composable(AppScreen.GaleryScreenPermission.route) {
            PermissionCameraScreen(navController = navController)
        }


        composable(
            AppScreen.GalleryScreen.route, arguments = listOf(
                navArgument("path") {
                    type = NavType.StringType
                }
            )
        ) {
            val path = it.arguments?.getString("path") ?: EMPTY_STRING
            GalleryScreen(
                navController = navController,
                path = path,
                levelViewModel = levelViewModel
            )
        }

        /*------------Specialist-----------------------*/
        composableSpecialist(navController, modifier)

    }
}

private fun NavGraphBuilder.composableSpecialist(
    navController: NavHostController,
    modifier: Modifier
) {
    composable(AppScreen.SpecialistScreen.route) {
        SpecialistScreen(navController = navController, modifier = modifier)
    }

    composable(AppScreen.GenerateQRScreen.route) {
        GenerateQRScreen(
            navController = navController,
        )
    }

    composable(AppScreen.LectorQRScreen.route) {
        LectorQRScreen(
            navController = navController, modifier = modifier
        )
    }

    composable(
        route = AppScreen.ProfilePatientScreen.route,
        arguments = listOf(navArgument(PACIENT) {
            type = NavType.StringType
        })
    ) { pacient ->
        pacient.arguments?.getString(PACIENT)?.let {
            ProfilePatientScreen(
                navController = navController,
                modifier = modifier,
                pacient = it
            )
        }
    }

    composable(
        route = AppScreen.SendObservationScreen.route,
        arguments = listOf(navArgument(CHILDREN_OBSERVATION) {
            type = NavType.StringType
        })
    ) { observation ->
        observation.arguments?.getString(CHILDREN_OBSERVATION)?.let {
            SendObservationScreen(
                navController = navController,
                modifier = modifier,
                observation = it
            )
        }
    }

}


private fun NavGraphBuilder.composableLearnSign(
    navController: NavHostController,
    levelViewModel: LevelViewModel
) {
    composable(
        AppScreen.LearnSignScreen.route,
        arguments = listOf(
            navArgument(LEVEL) {
                type = NavType.StringType
            }, navArgument(SUB_LEVEL) {
                type = NavType.StringType
            })
    ) {
        val level = it.arguments?.getString(LEVEL) ?: EMPTY_STRING
        val subLevel = it.arguments?.getString(SUB_LEVEL) ?: EMPTY_STRING
        LearnSignScreen(
            navController = navController,
            level = level,
            subLevel = subLevel,
            levelViewModel = levelViewModel
        )
    }
}

private fun NavGraphBuilder.composableChoseTheLetterPlay(
    navController: NavHostController,
    levelViewModel: LevelViewModel
) {
    composable(
        AppScreen.ChoseTheLetterPlayScreen.route,
        arguments = listOf(
            navArgument(LEVEL) {
                type = NavType.StringType
            }, navArgument(SUB_LEVEL) {
                type = NavType.StringType
            })
    ) {
        val level = it.arguments?.getString(LEVEL) ?: EMPTY_STRING
        val subLevel = it.arguments?.getString(SUB_LEVEL) ?: EMPTY_STRING
        ChoseTheLetterPlayScreen(
            navController = navController,
            level,
            subLevel,
            levelViewModel = levelViewModel
        )

    }
}

@SuppressLint("ComposableDestinationInComposeScope")
private fun NavGraphBuilder.composableChoseTheSignPlay(
    navController: NavHostController,
    levelViewModel: LevelViewModel
) {
    composable(
        AppScreen.ChoseTheSignPlayScreen.route,
        arguments = listOf(
            navArgument(LEVEL) {
                type = NavType.StringType
            }, navArgument(SUB_LEVEL) {
                type = NavType.StringType
            })
    ) {
        val level = it.arguments?.getString(LEVEL) ?: EMPTY_STRING
        val subLevel = it.arguments?.getString(SUB_LEVEL) ?: EMPTY_STRING
        ChoseTheSignPlayScreen(navController = navController, level, subLevel, levelViewModel)
    }

}