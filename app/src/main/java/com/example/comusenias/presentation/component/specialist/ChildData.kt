package com.example.comusenias.presentation.component.specialist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.comusenias.R
import com.example.comusenias.domain.models.users.ChildrenModel
import com.example.comusenias.presentation.component.addPatient.FieldWithIcon
import com.example.comusenias.presentation.ui.theme.AGE
import com.example.comusenias.presentation.ui.theme.SIZE12

@Composable
fun ChildData(patient: ChildrenModel) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SIZE12.dp)
    ) {
        FieldWithIcon(
            icon = painterResource(R.drawable.baseline_calendar_month_24),
            text = AGE
        )
        FieldWithIcon(
            icon = painterResource(R.drawable.phone_icon),
            text = patient.userModel.numberPhone
        )
        FieldWithIcon(
            icon = painterResource(R.drawable.mail_icon),
            text = patient.userModel.email
        )
    }
}