package com.ars.comusenias.presentation.component.login

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.presentation.component.defaults.DefaultLoadingProgressIndicator
import com.ars.comusenias.presentation.component.defaults.ToastMake
import com.ars.comusenias.presentation.ui.theme.RESET_PASSWORD_ERROR
import com.ars.comusenias.presentation.ui.theme.RESET_PASSWORD_SUCCESS

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