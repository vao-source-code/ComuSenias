package com.example.comusenias.presentation.component.register.especialistForm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.component.defaults.app.TextFieldApp
import com.example.comusenias.presentation.ui.theme.size16

@Composable
fun EspecialistFormContent(){
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(size16.dp)
    ) {
        TextFieldApp(
            value = "",
            onValueChange = {},
            label = "Nombre",
            icon = null
        )
        TextFieldApp(
            value = "",
            onValueChange = {},
            label = "Numero de Matricula",
            icon = null
        )
        TextFieldApp(
            value = "",
            onValueChange = {},
            label = "Vencimiento de Matricula",
            icon = null
        )
        TextFieldApp(
            value = "",
            onValueChange = {},
            label = "Titulo Medico",
            icon = null
        )
        TextFieldApp(
            value = "",
            onValueChange = {},
            label = "Especialidad",
            icon = null
        )
        TextFieldApp(
            value = "",
            onValueChange = {},
            label = "Telefono",
            icon = null
        )
        AcceptTerm()
    }
}