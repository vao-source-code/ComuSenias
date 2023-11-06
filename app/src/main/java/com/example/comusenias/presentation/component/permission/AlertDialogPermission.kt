package com.example.comusenias.presentation.component.permission

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.presentation.component.defaults.app.ButtonApp
import com.example.comusenias.presentation.ui.theme.CONFIG_PERMISSION
import com.example.comusenias.presentation.ui.theme.DESCRIPTION_DIALOG_PERMISSION
import com.example.comusenias.presentation.ui.theme.PACKAGE
import com.example.comusenias.presentation.ui.theme.SIZE16
import com.example.comusenias.presentation.ui.theme.SIZE24
import com.example.comusenias.presentation.ui.theme.TITLE_DIALOG_PERMISSION
import com.example.comusenias.presentation.ui.theme.blackColorApp

@Composable
fun AlertDialogPermission(
    permissionDenied: Boolean,
    showSettings: (Boolean) -> Unit
) {
    val context = LocalContext.current
    val openDialog = remember { mutableStateOf(permissionDenied) }

    if (openDialog.value) {

        AlertDialog(
            modifier = Modifier.padding(SIZE24.dp),
            onDismissRequest = {
                openDialog.value = false
                showSettings(false)
            },
            title = {
                TextAlertDialog(
                    text = TITLE_DIALOG_PERMISSION,
                    fontWeight = Bold
                )
            },
            text = {
                TextAlertDialog(
                    text = DESCRIPTION_DIALOG_PERMISSION,
                    fontWeight = FontWeight.Normal
                )
            },
            confirmButton = {
                ButtonApp(
                    titleButton = CONFIG_PERMISSION,
                    onClickButton = {
                        showSettings(true)
                        openDialog.value = false
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri = Uri.fromParts(PACKAGE, context.packageName, null)
                        intent.data = uri
                        context.startActivity(intent)
                    }
                )
            }
        )
    }
}

@Composable
fun TextAlertDialog(
    text: String,
    fontWeight: FontWeight,
) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = SIZE16.sp,
            fontWeight = fontWeight,
            color = blackColorApp,
            textAlign = Center
        )
    )
}