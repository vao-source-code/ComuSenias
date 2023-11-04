package com.example.comusenias.presentation.component.specialist.profilePatient

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.comusenias.domain.models.observation.ObservationModel
import com.example.comusenias.domain.models.users.ChildrenModel
import com.example.comusenias.presentation.screen.specialist.StatisticsScreen
import com.example.comusenias.presentation.ui.theme.SIZE20
import com.example.comusenias.presentation.ui.theme.SIZE36

@Composable
fun PatientProfileContent(
    navController: NavController,
    patient: ChildrenModel,
    observations: List<ObservationModel>
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ContentTopProfile(patient = patient)
        TabsPage(
            tabContent = listOf(
                {
                    StatisticsScreen()
                },
                {
                    ObservationsScreen(
                        observations = observations,
                        navController = navController
                    )
                }
            )
        )
    }
}

@Composable
fun ContentTopProfile(
    patient: ChildrenModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = SIZE36.dp, start = SIZE20.dp, end = SIZE20.dp, bottom = SIZE20.dp),
        horizontalArrangement = Arrangement.spacedBy(SIZE20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ImageProfile(userImage = patient.image)
        ChildData(patient = patient)
    }
}
