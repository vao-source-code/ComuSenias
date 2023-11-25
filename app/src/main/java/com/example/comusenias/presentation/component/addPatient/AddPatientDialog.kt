package com.example.comusenias.presentation.component.addPatient

import androidx.compose.material.AlertDialog
import androidx.compose.runtime.Composable
import com.example.comusenias.presentation.component.defaults.app.ButtonApp

@Composable
fun AddPatientDialog(
    image: String?,
    name: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        text = {
            UserProfileContent(image = image, name = name)
        },
        confirmButton = {
            ButtonApp(
                titleButton = "Aceptar",
                onClickButton = {
                    onConfirm()
                }
            )
        },
        dismissButton = {
            ButtonApp(
                titleButton = "Cancelar",
                onClickButton = {
                    onDismiss()
                }
            )
        }
    )
}