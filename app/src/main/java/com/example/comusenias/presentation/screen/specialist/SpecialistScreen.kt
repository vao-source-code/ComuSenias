package com.example.comusenias.presentation.screen.specialist

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.presentation.component.defaults.DefaultLoadingProgressIndicator
import com.example.comusenias.presentation.component.specialist.home.SpecialistHomeContent
import com.example.comusenias.presentation.ui.theme.LOGIN_ERROR
import com.example.comusenias.presentation.view_model.specialist.SpecialistViewModel

@Composable
fun SpecialistScreen(
    navController: NavHostController,
    modifier: Modifier,
    viewModel: SpecialistViewModel = hiltViewModel()
) {
    when (viewModel.childrenResponse) {
        Response.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                DefaultLoadingProgressIndicator()
            }
        }
        is Response.Success -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                SpecialistHomeContent(
                    navController = navController,
                    specialist = viewModel.stateSpecialist,
                )
            }

        }
        is Response.Error -> {
            Toast.makeText(
                LocalContext.current,
                (viewModel.childrenResponse as Response.Error).exception?.message + LOGIN_ERROR,
                Toast.LENGTH_SHORT
            ).show()
        }
        else -> {}
    }

}