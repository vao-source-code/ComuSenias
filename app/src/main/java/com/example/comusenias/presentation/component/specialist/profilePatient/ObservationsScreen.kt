package com.example.comusenias.presentation.component.specialist.profilePatient

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.comusenias.R
import com.example.comusenias.domain.models.observation.Observation
import com.example.comusenias.presentation.component.defaults.FloatingButtonDefault
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.SIZE1
import com.example.comusenias.presentation.ui.theme.SIZE10
import com.example.comusenias.presentation.ui.theme.SIZE14
import com.example.comusenias.presentation.ui.theme.SIZE16
import com.example.comusenias.presentation.ui.theme.SIZE6
import com.example.comusenias.presentation.ui.theme.blackColorApp
import com.example.comusenias.presentation.ui.theme.line_divisor

@Composable
fun ObservationsScreen(
    observations: List<Observation>, navController: NavController
) {
    Scaffold(floatingActionButton = {
        FloatingButtonDefault(icon = R.drawable.note_add,
            click = { navController.navigate(AppScreen.SendObservationScreen.route) })
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            ListObservations(observations = observations)
        }
    }
}

@Composable
fun ListObservations(observations: List<Observation>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(observations) { observation ->
            CardObservation(observation = observation)
        }
    }
}

@Composable
fun CardObservation(observation: Observation) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = SIZE10.dp),
        verticalArrangement = Arrangement.spacedBy(SIZE6.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = observation.dateObservation,
            style = TextStyle(
                fontSize = SIZE14.sp,
                fontWeight = FontWeight.Medium,
                color = blackColorApp,
                textAlign = TextAlign.Right
            )
        )
        Text(
            modifier = Modifier.fillMaxWidth(), text = "Dr. Gutierrez", style = TextStyle(
                fontSize = SIZE14.sp,
                fontWeight = FontWeight.Bold,
                color = blackColorApp,
                textAlign = TextAlign.Left
            )
        )
        Text(
            modifier = Modifier.fillMaxWidth(), text = observation.observation, style =
            TextStyle(
                fontSize = SIZE14.sp,
                lineHeight = SIZE16.sp,
                fontWeight = FontWeight.Normal,
                color = blackColorApp,
                textAlign = TextAlign.Left
            )
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(SIZE1.dp), color = line_divisor
        )
    }
}
