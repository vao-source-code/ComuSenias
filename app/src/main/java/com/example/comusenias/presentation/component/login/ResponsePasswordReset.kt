package com.example.comusenias.presentation.component.login

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.comusenias.domain.models.Response
import com.example.comusenias.presentation.component.defaults.DefaultLoadingProgressIndicator
import com.example.comusenias.presentation.component.defaults.app.showToast
import com.example.comusenias.presentation.ui.theme.LOGIN_ERROR
import com.example.comusenias.presentation.ui.theme.RESET_PASSWORD_SUCCESS

@Composable
fun ResponsePasswordReset(
    response: Response<Boolean>?,
    navController: NavHostController,
) {
    when (response) {
        Response.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                DefaultLoadingProgressIndicator()
            }
        }

        is Response.Success -> {
            showToast(LocalContext.current, RESET_PASSWORD_SUCCESS, Toast.LENGTH_SHORT)
            navController.popBackStack()
        }

        is Response.Error -> {
            showToast(
                LocalContext.current,
                response.exception?.message + LOGIN_ERROR,
                Toast.LENGTH_SHORT
            )
        }

        else -> {}
    }
}