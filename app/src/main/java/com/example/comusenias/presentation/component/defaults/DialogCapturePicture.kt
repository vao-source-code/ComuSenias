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
import com.example.comusenias.presentation.ui.theme.DIALOG_CAPTURE_PICTURE
import com.example.comusenias.presentation.ui.theme.DIALOG_CAPTURE_TITLE
import com.example.comusenias.presentation.ui.theme.DIALOG_GALLERY
import com.example.comusenias.presentation.ui.theme.size20

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
                    text = DIALOG_CAPTURE_TITLE,
                    color = Color.Black,
                    fontSize = size20.sp
                )
            },
            confirmButton = {
                Text(
                    text = DIALOG_CAPTURE_PICTURE,
                    color = Color.Black,
                    fontSize = size20.sp,
                    modifier = Modifier.clickable {
                        takePhoto()
                        status.value = false
                    }
                )
            },
            dismissButton = {
                Text(
                    text = DIALOG_GALLERY,
                    color = Color.Black,
                    fontSize = size20.sp,
                    modifier = Modifier.clickable {
                        pickPhoto()
                        status.value = false
                    }
                )
            }
        )
    }
}
