package com.example.comusenias.presentation.component.defaults.app

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ShowRetrySnackBar(
    text: String,
    onActionClick: () -> Unit
) {
    var snackbarVisible by remember { mutableStateOf(false) }

    if (snackbarVisible) {
        Snackbar(
            modifier = Modifier.fillMaxWidth(),
            action = {
                TextButton(
                    onClick = {
                        snackbarVisible = false
                        onActionClick()
                    }
                ) {
                    Text(
                        text = "REINTENTAR",
                        style = MaterialTheme.typography.button.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    )
                }
            },
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.body1,
                    color = Color.White,
                )
            }
        }
    }
}