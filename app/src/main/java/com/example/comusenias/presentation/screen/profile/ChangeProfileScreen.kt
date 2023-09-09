package com.example.comusenias.presentation.screen.profile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.component.defaults.ButtonDefault
import com.example.comusenias.presentation.component.defaults.DefaultTopBar
import com.example.comusenias.presentation.component.profile.ChangeProfileContent
import com.example.comusenias.presentation.component.profile.ProfileFooterContent
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.view_model.ProfileViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeProfilePasswordScreen(
    navController: NavHostController,
    modifier: Modifier?,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = { DefaultTopBar(title = "Editar Datos", true, navController) },
        content = { it ->

            it.calculateBottomPadding()
            ChangeProfileContent(
                modifier = modifier,
                navController = navController,
                viewModel = viewModel
            )
        },
        bottomBar = {
            ProfileFooterContent {
                navController?.navigate(route = AppScreen.ProfileScreen.route) {
                }
            }

        })

}

