package com.example.comusenias.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.comusenias.presentation.activities.MainActivity
import com.example.comusenias.presentation.component.bottomBar.ShowBottomBar
import com.example.comusenias.presentation.screen.login.LoginScreen
import com.example.comusenias.presentation.screen.onboarding.OnBoardingScreen
import com.example.comusenias.presentation.screen.profile.ChangeProfilePasswordScreen
import com.example.comusenias.presentation.screen.profile.ProfileScreen
import com.example.comusenias.presentation.screen.register.RegisterScreen
import com.example.comusenias.presentation.splashScreen.SplashScreen
import com.example.comusenias.presentation.view_model.BottomBarViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(
    navController: NavHostController,
    bottomBarViewModel: BottomBarViewModel = hiltViewModel()
) {

    Scaffold(
        bottomBar = {
            ShowBottomBar(navController = navController)
        }
    ) { paddingValues ->
        GetNavHost(
            navController,
            Modifier.padding(paddingValues),
            bottomBarViewModel
        )
    }
}

@Composable
private fun GetNavHost(
    navController: NavHostController,
    modifier: Modifier,
    bottomBarViewModel: BottomBarViewModel
) {

    NavHost(
        navController = navController,
        startDestination = AppScreen.OnBoardingScreen.route
    ) {
        composable(AppScreen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(AppScreen.OnBoardingScreen.route) {
            OnBoardingScreen(navController = navController)
        }
        composable(AppScreen.LoginScreen.route) {
            LoginScreen(navController = navController, modifier, bottomBarViewModel)
        }

        composable(AppScreen.RegisterScreen.route) {
            RegisterScreen(navController = navController, modifier = modifier)
        }
        composable(AppScreen.ProfileScreen.route) {
            ProfileScreen(navController = navController, modifier = modifier)
        }
        composable(AppScreen.MainActivity.route) {
            MainActivity()
        }
        composable(AppScreen.ChangeProfileScreen.route) {
            ChangeProfilePasswordScreen(
                navController = navController,
                modifier = modifier
            )
        }
    }
}