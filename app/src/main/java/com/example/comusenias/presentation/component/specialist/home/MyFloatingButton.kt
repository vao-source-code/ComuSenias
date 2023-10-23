package com.example.comusenias.presentation.component.specialist.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.comusenias.R
import com.example.comusenias.presentation.ui.theme.ADD_PATIENT
import com.example.comusenias.presentation.ui.theme.SIZE16
import com.example.comusenias.presentation.ui.theme.SIZE28
import com.example.comusenias.presentation.ui.theme.primaryColorApp

@Composable
fun MyFloatingButton(
    icon: Int = R.drawable.person_add,
    click: () -> Unit = {}
) {
    FloatingActionButton(
        onClick = { click() },
        modifier = Modifier
            .padding(SIZE16.dp)
            .background(
                color = primaryColorApp,
                shape = RoundedCornerShape(SIZE28.dp)
            ),
        backgroundColor = primaryColorApp,
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = ADD_PATIENT
        )
    }
}

