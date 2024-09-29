package com.ars.comusenias.presentation.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.ars.comusenias.presentation.splashScreen.SplashScreen
import com.ars.comusenias.presentation.view_model.CameraViewModel
import com.ars.comusenias.presentation.view_model.ChildrenProfileViewModel
import com.ars.comusenias.presentation.view_model.LevelViewModel

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InitNavigation(
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

        composable(AppScreen.SplashScreen.route) {
            SplashScreen(navController)
        }

    }
}