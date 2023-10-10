package com.example.comusenias.domain

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.comusenias.R
import com.example.comusenias.domain.models.model.Paciente
import com.example.comusenias.presentation.component.addPatient.FieldWithIcon
import com.example.comusenias.presentation.component.addPatient.UserProfileContent
import com.example.comusenias.presentation.component.defaults.app.ButtonApp
import com.example.comusenias.presentation.ui.theme.AgregaPaciente
import com.example.comusenias.presentation.ui.theme.SIZE12
import com.example.comusenias.presentation.ui.theme.SIZE24
import com.example.comusenias.presentation.ui.theme.SIZE25


@Composable
fun AddPatientScreen() {
    val paciente = remember {
        mutableStateOf(
            Paciente(
                nombre = "Jose Augusto",
                edad = 6,
                tel = 1158548647,
                email = "12345@gmail.com",
                ubicacion = "Moreno"
            )
        )
    }

    Column(
        modifier = Modifier.padding(SIZE24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        UserProfileContent(
            imageResId = R.drawable.profile_avatar,
            contentDescription = "User Profile",
            name = paciente.value.nombre
        )
    }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(SIZE25.dp)

        ) {

            FieldWithIcon(
                icon = painterResource(R.drawable.baseline_calendar_month_24),
                text = "${paciente.value.edad} años"
            )
            FieldWithIcon(
                icon = painterResource(R.drawable.phone_icon),
                text = "${paciente.value.tel}"
            )
            FieldWithIcon(
                icon = painterResource(R.drawable.mail_icon),
                text = paciente.value.email
            )
            FieldWithIcon(
                icon = painterResource(R.drawable.lugar_icon),
                text = paciente.value.ubicacion
            )

        }

        ButtonApp(titleButton = AgregaPaciente)

    }


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AddPatientScreen()
}