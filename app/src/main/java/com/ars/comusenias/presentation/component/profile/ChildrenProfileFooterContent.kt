package com.ars.comusenias.presentation.component.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ars.comusenias.presentation.component.defaults.DialogDefault
import com.ars.comusenias.presentation.component.defaults.app.ButtonApp
import com.ars.comusenias.presentation.ui.theme.LOGOUT
import com.ars.comusenias.presentation.ui.theme.SIZE20
import com.ars.comusenias.presentation.ui.theme.UPDATE_DATA

@Composable
fun ProfileFooterContent(onClickChangeProfile: () -> Unit?, onclickLogout: () -> Unit) {
    // Lógica para manejar el click en el perfil
    var showDialog by remember { mutableStateOf(false) }



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(SIZE20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ButtonApp(
            titleButton = UPDATE_DATA,
            onClickButton = {
                onClickChangeProfile()

            }
        )
        Spacer(modifier = Modifier.height(10.dp))

        ButtonApp(
            titleButton = LOGOUT,
            onClickButton = {
                showDialog = true
            }
        )

    }

    if (showDialog) {
        DialogDefault(
            title = "Confirmación",
            text = "¿Estás seguro de que deseas cerrar sesión?",
            onConfirm = {
                onclickLogout()
            }, onDismiss = {
                showDialog = false
            })

    }
}
