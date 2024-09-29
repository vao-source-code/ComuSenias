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
import com.ars.comusenias.presentation.activities.MainActivity
import com.ars.comusenias.presentation.activities.AuthActivity.Companion.getChildrenProfileViewModel
import com.ars.comusenias.presentation.screen.camera.CameraScreen
import com.ars.comusenias.presentation.screen.camera.RecordCameraScreen
import com.ars.comusenias.presentation.screen.gameAction.ChoseTheLetterPlayScreen
import com.ars.comusenias.presentation.screen.gameAction.ChoseTheSignPlayScreen
import com.ars.comusenias.presentation.screen.gameAction.CongratsPlayScreen
import com.ars.comusenias.presentation.screen.gameAction.InfoMakeSignScreen
import com.ars.comusenias.presentation.screen.gameAction.InterpretationStatusScreen
import com.ars.comusenias.presentation.screen.gameAction.LearnSignScreen
import com.ars.comusenias.presentation.screen.home.HomeScreen
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
import com.ars.comusenias.presentation.screen.home.SupportScreen
import com.ars.comusenias.presentation.screen.login.LoginScreen
import com.google.firebase.analytics.FirebaseAnalytics.Param.LEVEL

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AuthNavigation(
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

    getChildrenProfileViewModel = childrenProfileViewModel

    NavHost(
        navController = navController,
        startDestination = AuthScreen.LoginScreen.route
    ) {
        composable(route = AuthScreen.LoginScreen.route) {
            LoginScreen(navController = navController, modifier = modifier)
        }

        authNavGraph(navController = navController, modifier = modifier)
    }
}

