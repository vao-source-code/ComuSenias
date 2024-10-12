package com.ars.comusenias.presentation.component.login

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.ars.comusenias.R
import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.presentation.component.defaults.DefaultLoadingProgressIndicator
import com.ars.comusenias.presentation.component.defaults.DialogImageTextDefault
import com.ars.comusenias.presentation.component.defaults.ToastMake
import com.ars.comusenias.presentation.navigation.AppScreen
import com.ars.comusenias.presentation.ui.theme.RESET_PASSWORD_ERROR
import com.ars.comusenias.presentation.ui.theme.RESET_PASSWORD_SUCCESS

@Composable
fun ResponsePasswordReset(
    response: Response<Boolean>?,
    navController: NavController
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
            PasswordResetDialog(
                onDismiss = {},
                onButton = {
                    navController.navigate(route = AppScreen.LoginScreen.route)
                })
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

@Composable
fun PasswordResetDialog(onDismiss: () -> Unit, onButton: () -> Unit) {
    DialogImageTextDefault(
        icon = R.drawable.icon_send_email,
        title = "Genial! Estás a un paso",
        text = "Enviamos un email al correo electrónico que nos proporcionaste con instrucciones para cambiar tu contraseña. \n " +
                "Si no aparece en tu bandeja de entrada recuerda revisar la carpeta de spam o no deseados.",
        onDismissRequest = onDismiss,
        onButtonOk = onButton
    )
}