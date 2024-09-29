package com.ars.comusenias.presentation.navigation

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
import com.ars.comusenias.domain.library.LibraryDebugger
import com.ars.comusenias.presentation.activities.SpecialistActivity.Companion.getChildrenProfileViewModel
import com.ars.comusenias.presentation.screen.camera.CameraScreen
import com.ars.comusenias.presentation.screen.profile.ChangeSpecialistProfileScreen
import com.ars.comusenias.presentation.screen.profile.SpecialistProfileScreen
import com.ars.comusenias.presentation.screen.qr.GenerateQRScreen
import com.ars.comusenias.presentation.screen.qr.LectorQRScreen
import com.ars.comusenias.presentation.screen.specialist.ProfilePatientScreen
import com.ars.comusenias.presentation.screen.specialist.SendObservationScreen
import com.ars.comusenias.presentation.screen.specialist.SpecialistScreen
import com.ars.comusenias.presentation.ui.theme.CHILDREN_OBSERVATION
import com.ars.comusenias.presentation.ui.theme.PACIENT
import com.ars.comusenias.presentation.ui.theme.SPECIALIST_PROFILE
import com.ars.comusenias.presentation.view_model.CameraViewModel
import com.ars.comusenias.presentation.view_model.ChildrenProfileViewModel
import com.ars.comusenias.presentation.view_model.LevelViewModel
import com.ars.comusenias.presentation.screen.home.SupportScreen

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SpecialistNavigation(
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
    val childrenProfileViewModel: ChildrenProfileViewModel = hiltViewModel()
    val cameraViewModel: CameraViewModel = hiltViewModel()


    getChildrenProfileViewModel = childrenProfileViewModel

    NavHost(
        navController = navController,
        startDestination = AppScreen.SpecialistScreen.route
    ) {


        composable(AppScreen.SupportScreen.route) {
            SupportScreen(
                navController = navController,
                modifier = modifier
            )
        }

        /*------------Specialist-----------------------*/

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
