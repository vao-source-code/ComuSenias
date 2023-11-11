package com.example.comusenias.presentation.component.defaults

import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.comusenias.presentation.component.defaults.app.ButtonApp

@Composable
fun DialogDefault(title: String, text: String, onConfirm: () -> Unit, onDismiss: () -> Unit) {

    AlertDialog(
        onDismissRequest = {},
        title = {
            androidx.compose.material.Text(
                modifier = Modifier.testTag("titleAlertDialog"),
                color = Color.Black,
                text = title,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )
        },
        text = {
            androidx.compose.material.Text(
                modifier = Modifier.testTag("textAlertDialog"),
                color = Color.DarkGray,
                text = text,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )
        },
        confirmButton = {
            // Botón de confirmación
            ButtonApp(
                titleButton = "Aceptar",
                onClickButton = {
                    // Cierra el cuadro de diálogo y ejecuta la acción de confirmación
                    onConfirm()
                }
            )
        },
        dismissButton = {
            // Botón para cancelar el cierre de sesión
            ButtonApp(
                titleButton = "Cancelar",
                onClickButton = {
                    onDismiss()
                }
            )
        }
    )

}

