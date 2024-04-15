package com.ars.comusenias.presentation.screen.specialist

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
import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.presentation.component.defaults.DefaultLoadingProgressIndicator
import com.ars.comusenias.presentation.component.defaults.ToastMake
import com.ars.comusenias.presentation.component.defaults.app.ShowRetrySnackBar
import com.ars.comusenias.presentation.component.specialist.home.SpecialistHomeContent
import com.ars.comusenias.presentation.ui.theme.LOADING_ERROR
import com.ars.comusenias.presentation.ui.theme.LOGIN_ERROR
import com.ars.comusenias.presentation.view_model.specialist.SpecialistViewModel

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
            ToastMake.showError(
                LocalContext.current,
                LOGIN_ERROR
            )
            ShowRetrySnackBar(text = LOADING_ERROR, true, onActionClick = {
                viewModel.getUserData()
                viewModel.getChildrenBySpecialist()
            })
        }
        else -> {}
    }

}