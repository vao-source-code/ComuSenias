package com.example.comusenias.presentation.component.register.especialistForm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.component.defaults.app.TextFieldApp
import com.example.comusenias.presentation.ui.theme.EXPIRY_OF_PROFESSIONAL_REGISTRATION
import com.example.comusenias.presentation.ui.theme.MEDICAL_TITLE
import com.example.comusenias.presentation.ui.theme.NAME
import com.example.comusenias.presentation.ui.theme.NUMBER_PHONE
import com.example.comusenias.presentation.ui.theme.PROFESSIONAL_REGISTRATION
import com.example.comusenias.presentation.ui.theme.SIZE16
import com.example.comusenias.presentation.ui.theme.SPECIALTY

@Composable
fun EspecialistFormContent(){
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SIZE16.dp)
    ) {
        TextFieldApp(
            value = "",
            onValueChange = {},
            label = NAME,
            icon = null
        )
        TextFieldApp(
            value = "",
            onValueChange = {},
            label = PROFESSIONAL_REGISTRATION,
            icon = null
        )
        TextFieldApp(
            value = "",
            onValueChange = {},
            label = EXPIRY_OF_PROFESSIONAL_REGISTRATION,
            icon = null
        )
        TextFieldApp(
            value = "",
            onValueChange = {},
            label = MEDICAL_TITLE,
            icon = null
        )
        TextFieldApp(
            value = "",
            onValueChange = {},
            label = SPECIALTY,
            icon = null
        )
        TextFieldApp(
            value = "",
            onValueChange = {},
            label = NUMBER_PHONE,
            icon = null
        )
        AcceptTerm()
    }
}