package com.example.comusenias.presentation.screen.profile

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.component.defaults.ButtonDefault
import com.example.comusenias.presentation.component.defaults.DefaultTopBar
import com.example.comusenias.presentation.component.profile.ChangeProfilePasswordContent
import com.example.comusenias.presentation.component.profile.ProfileContent
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.view_model.ProfileViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeProfilePasswordScreen(
    navController: NavHostController,
    modifier: Modifier?,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    Scaffold(topBar = { DefaultTopBar(title = "Cambiar contraseña" , true , navController )}, content = { it ->

        it.calculateBottomPadding()
        ChangeProfilePasswordContent(
            modifier = modifier,
            navController = navController,
            viewModel = viewModel
        )
    }, bottomBar = {})

}

