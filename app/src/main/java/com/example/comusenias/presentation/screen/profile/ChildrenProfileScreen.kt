package com.example.comusenias.presentation.screen.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.comusenias.R
import com.example.comusenias.presentation.component.bottomBar.ShowBottomBar
import com.example.comusenias.presentation.component.defaults.FloatingButtonDefault
import com.example.comusenias.presentation.component.profile.ChildrenProfileContent
import com.example.comusenias.presentation.component.profile.ProfileFooterContent
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.view_model.ChildrenProfileViewModel

@Composable
fun ChildrenProfileScreen(
    navController: NavHostController,
    modifier: Modifier,
    viewModel: ChildrenProfileViewModel = hiltViewModel()
) {
    Scaffold(
        bottomBar = {
            ShowBottomBar(navController = navController)
        }, floatingActionButton = {
            FloatingButtonDefault(icon = R.drawable.qr_code_scanner) {
                navController.navigate(AppScreen.GenerateQRScreen.route)
            }
        }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                ChildrenProfileContent(
                    viewModel = viewModel
                )
                val onClick: () -> Unit =
                    {
                        viewModel.saveImage()
                    }
                ProfileFooterContent(
                    onClickChangeProfile = onClick
                ) {
                    viewModel.logout()
                    navController.navigate(com.example.comusenias.presentation.navigation.AppScreen.LoginScreen.route)
                }
            }
        }
    }
}