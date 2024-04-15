package com.ars.comusenias.presentation.component.defaults

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ars.comusenias.R.drawable.person_add
import com.ars.comusenias.presentation.ui.theme.ADD_PATIENT
import com.ars.comusenias.presentation.ui.theme.SIZE16
import com.ars.comusenias.presentation.ui.theme.SIZE28
import com.ars.comusenias.presentation.ui.theme.primaryColorApp

@Composable
fun FloatingButtonDefault(
    icon: Int = person_add,
    click: () -> Unit = {}
) {
    FloatingActionButton(
        modifier = Modifier
            .padding(SIZE16.dp)
            .background(
                color = primaryColorApp,
                shape = RoundedCornerShape(SIZE28.dp)
            ),
        backgroundColor = primaryColorApp,
        onClick = { click() },
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = ADD_PATIENT
        )
    }
}

