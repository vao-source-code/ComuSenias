package com.example.comusenias.presentation.component.login

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.presentation.component.defaults.DefaultLoadingProgressIndicator
import com.example.comusenias.presentation.component.defaults.ToastMake
import com.example.comusenias.presentation.ui.theme.RESET_PASSWORD_ERROR
import com.example.comusenias.presentation.ui.theme.RESET_PASSWORD_SUCCESS

@Composable
fun ResponsePasswordReset(
    response: Response<Boolean>?,
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
            Log.d("Success", (response as Response.Success).data.toString())
            ToastMake.showSuccess(LocalContext.current, RESET_PASSWORD_SUCCESS)
        }

        is Response.Error -> {
            Log.e("Error", (response as Response.Error).exception?.message.toString())
            ToastMake.showError(
                LocalContext.current,
                RESET_PASSWORD_ERROR
            )
        }

        else -> {}
    }
}