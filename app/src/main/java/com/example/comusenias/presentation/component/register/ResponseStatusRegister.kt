package com.example.comusenias.presentation.component.register

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.comusenias.domain.models.Response
import com.example.comusenias.presentation.component.defaults.DefaultLoadingProgressIndicator
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.view_model.RegisterViewModel

@Composable
fun ResponseStatusRegister(
    navController: NavHostController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    when (val registerResponse = viewModel.registerResponse) {
        is Response.Loading -> {
            DefaultLoadingProgressIndicator()
        }

        is Response.Success -> {
            LaunchedEffect(Unit) {
                viewModel.createUser()
                //Elimino asi el total del historial de atras
                navController.popBackStack(AppScreen.LoginScreen.route, inclusive = true)
                navController.navigate(route = AppScreen.ProfileScreen.route)
            }
        }

        is Response.Error -> {
            //TODO corregir el error de que no se muestra el mensaje de error
            Toast.makeText(
                LocalContext.current,
                registerResponse.exception?.message,
                Toast.LENGTH_SHORT
            ).show()
        }

        else -> {}
    }
}
