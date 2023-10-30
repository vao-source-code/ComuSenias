package com.example.comusenias.presentation.component.defaults

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun DialogDefault(title: String, text: String, onConfirm: () -> Unit, onDismiss: () -> Unit) {

    AlertDialog(
        onDismissRequest = { },
        title = { Text(text = title) },
        text = { Text(text = text) },
        confirmButton = {
            TextButton(onClick = { onConfirm() },
                content = { Text(text = "Confirmar") })
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() },
                content = { Text(text = "Cancelar") })
        })
}