package com.example.comusenias.presentation.screen.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.component.profile.ProfileContent
import com.example.comusenias.presentation.component.profile.ProfileFooterContent
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.view_model.ProfileViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    modifier: Modifier,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ProfileContent(
                viewModel = viewModel
            )
            val onClick = {
                navController.navigate(
                    route = AppScreen.ChangeProfileScreen.passUser(viewModel.userData.toJson())
                )
            }

            ProfileFooterContent(
                onClickButton = onClick
            )
        }
    }
}