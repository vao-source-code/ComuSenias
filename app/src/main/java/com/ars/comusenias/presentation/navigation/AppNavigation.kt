package com.ars.comusenias.presentation.navigation

import PermissionCameraScreen
import PermissionRecordCameraScreen
import VideoPlayer
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
import com.ars.comusenias.presentation.activities.MainActivity
import com.ars.comusenias.presentation.activities.MainActivity.Companion.getChildrenProfileViewModel
import com.ars.comusenias.presentation.activities.MainActivity.Companion.getLevelViewModel
import com.ars.comusenias.presentation.screen.camera.CameraScreen
import com.ars.comusenias.presentation.screen.camera.RecordCameraScreen
import com.ars.comusenias.presentation.screen.gallery.GalleryScreen
import com.ars.comusenias.presentation.screen.gameAction.ChoseTheLetterPlayScreen
import com.ars.comusenias.presentation.screen.gameAction.ChoseTheSignPlayScreen
import com.ars.comusenias.presentation.screen.gameAction.CongratsPlayScreen
import com.ars.comusenias.presentation.screen.gameAction.InfoMakeSignScreen
import com.ars.comusenias.presentation.screen.gameAction.InterpretationStatusScreen
import com.ars.comusenias.presentation.screen.gameAction.LearnSignScreen
import com.ars.comusenias.presentation.screen.gameAction.MakeSignPlayScreen
import com.ars.comusenias.presentation.screen.home.HomeScreen
import com.ars.comusenias.presentation.screen.home.SupportScreen
import com.ars.comusenias.presentation.screen.notification.NotificationScreen
import com.ars.comusenias.presentation.screen.premiun.PremiunScreen
import com.ars.comusenias.presentation.screen.profile.ChangeSpecialistProfileScreen
import com.ars.comusenias.presentation.screen.profile.ChildrenProfileScreen
import com.ars.comusenias.presentation.screen.profile.SpecialistProfileScreen
import com.ars.comusenias.presentation.screen.qr.GenerateQRScreen
import com.ars.comusenias.presentation.screen.qr.LectorQRScreen
import com.ars.comusenias.presentation.screen.specialist.ProfilePatientScreen
import com.ars.comusenias.presentation.screen.specialist.SendObservationScreen
import com.ars.comusenias.presentation.screen.specialist.SpecialistScreen
import com.ars.comusenias.presentation.splashScreen.SplashScreen
import com.ars.comusenias.presentation.ui.theme.CHILDREN_OBSERVATION
import com.ars.comusenias.presentation.ui.theme.EMPTY_STRING
import com.ars.comusenias.presentation.ui.theme.PACIENT
import com.ars.comusenias.presentation.ui.theme.SPECIALIST_PROFILE
import com.ars.comusenias.presentation.ui.theme.SUB_LEVEL
import com.ars.comusenias.presentation.view_model.CameraViewModel
import com.ars.comusenias.presentation.view_model.ChildrenProfileViewModel
import com.ars.comusenias.presentation.view_model.LevelViewModel
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
    val childrenProfileViewModel: ChildrenProfileViewModel = hiltViewModel()
    val cameraViewModel: CameraViewModel = hiltViewModel()

    getLevelViewModel = levelViewModel
    getChildrenProfileViewModel = childrenProfileViewModel

    NavHost(
        navController = navController,
        startDestination = AppScreen.SplashScreen.route
    ) {

        authNavGraph(navController = navController, modifier = modifier)

        composable(AppScreen.SplashScreen.route) {
            SplashScreen(navController)
        }

        composable(AppScreen.ChildrenProfileScreen.route) {
            ChildrenProfileScreen(navController = navController, modifier = modifier)
        }
        composable(AppScreen.HomeScreen.route) {
            HomeScreen(
                navController = navController,
                levelViewModel = levelViewModel
            )
        }

        composable(AppScreen.SupportScreen.route){
            SupportScreen(
                navController = navController,
                modifier = modifier
            )
        }

        composable(AppScreen.InfoMakeSignScreen.route) {
            InfoMakeSignScreen(navController)
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

        composable(AppScreen.RecordCameraScreen.route) {
            RecordCameraScreen(
                levelViewModel = levelViewModel,
                viewModel = cameraViewModel,
                navController = navController
            )
        }

        //Permission Camera
        composable(AppScreen.CameraScreenPermission.route) {
            PermissionCameraScreen(navController = navController)
        }

        //Permission Record Camera
        composable(AppScreen.PermissioRecordCameraScreen.route) {
            PermissionRecordCameraScreen(navController = navController)
        }

        composable(
            AppScreen.ShowVideoOrImageScreen.route + "/{path}",
            arguments = listOf(navArgument("path") { type = NavType.StringType })
        ) { navBackStackEntry ->
            val path = navBackStackEntry.arguments?.getString("path") ?: ""
            VideoPlayer(path)
        }

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
        composableSpecialist(navController, modifier)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
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

    composable(AppScreen.SpecialistProfileScreen.route) {
        SpecialistProfileScreen(
            navController = navController, modifier = modifier
        )
    }

    composable(
        route = AppScreen.ChangeSpecialistProfileScreen.route,
        arguments = listOf(navArgument(SPECIALIST_PROFILE) {
            type = NavType.StringType
        })
    ) { profile ->
        profile.arguments?.getString(SPECIALIST_PROFILE)?.let {
            ChangeSpecialistProfileScreen(
                navController = navController,
                modifier = modifier,
                specialistProfile = it
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