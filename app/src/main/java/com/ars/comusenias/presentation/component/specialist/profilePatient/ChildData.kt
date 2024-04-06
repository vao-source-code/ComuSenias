package com.ars.comusenias.presentation.component.specialist.profilePatient

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ars.comusenias.R.drawable.baseline_calendar_month_24
import com.ars.comusenias.R.drawable.mail_icon
import com.ars.comusenias.R.drawable.phone_icon
import com.ars.comusenias.domain.models.users.ChildrenModel
import com.ars.comusenias.presentation.component.addPatient.FieldWithIcon
import com.ars.comusenias.presentation.ui.theme.SIZE7

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