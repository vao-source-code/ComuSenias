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
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.view_model.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavHostController,
    modifier: Modifier?,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    Scaffold(topBar = {}, content = { it ->
        it.calculateBottomPadding()
        ButtonDefault(
            text = "Cerrar Sesion",
            icon = Icons.Default.ArrowForward,
            onClick = {
                viewModel.logout()
                navController.navigate(route = AppScreen.LoginScreen.route) {
                    //es el TOPFLAG de la pila de navegacion
                    popUpTo(AppScreen.LoginScreen.route) {
                        inclusive = true
                    }
                }
            })
    }, bottomBar = {})

}