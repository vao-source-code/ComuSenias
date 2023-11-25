package com.example.comusenias.presentation.component.specialist.profilePatient

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.comusenias.R.drawable.baseline_calendar_month_24
import com.example.comusenias.R.drawable.mail_icon
import com.example.comusenias.R.drawable.phone_icon
import com.example.comusenias.domain.models.users.ChildrenModel
import com.example.comusenias.presentation.component.addPatient.FieldWithIcon
import com.example.comusenias.presentation.ui.theme.SIZE7

@Composable
fun ChildData(patient: ChildrenModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = spacedBy(SIZE7.dp)
    ) {
        FieldWithIcon(
            icon = painterResource(baseline_calendar_month_24),
            text = patient.date
        )
        FieldWithIcon(
            icon = painterResource(phone_icon),
            text = patient.phone
        )
        FieldWithIcon(
            icon = painterResource(mail_icon),
            text = patient.email
        )
    }
}