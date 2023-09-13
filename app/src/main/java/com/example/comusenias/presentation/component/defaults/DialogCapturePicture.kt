package com.example.comusenias.presentation.component.defaults

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun DialogCapturePicture(
    status: MutableState<Boolean>,
    takePhoto: () -> Unit,
    pickPhoto: () -> Unit,
) {
    if (status.value) {
        AlertDialog(
            modifier = Modifier
                .fillMaxWidth(),
            onDismissRequest = { status.value = false },
            title = {
                Text(
                    text = "Selecciona una opción",
                    color = Color.Black,
                    fontSize = 20.sp
                )
            },
            confirmButton = {
                Text(
                    text = "Tomar foto",
                    color = Color.Black,
                    fontSize = 20.sp,
                    modifier = Modifier.clickable {
                        takePhoto()
                        status.value = false
                    }
                )
            },
            dismissButton = {
                Text(
                    text = "Seleccionar foto",
                    color = Color.Black,
                    fontSize = 20.sp,
                    modifier = Modifier.clickable {
                        pickPhoto()
                        status.value = false
                    }
                )
            }
        )
    }
}
