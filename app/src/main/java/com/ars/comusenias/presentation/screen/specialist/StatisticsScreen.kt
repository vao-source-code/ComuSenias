package com.ars.comusenias.presentation.screen.specialist

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ars.comusenias.presentation.component.statitics.StatisticsComponent
import com.ars.comusenias.presentation.ui.theme.SIZE10
import com.ars.comusenias.presentation.ui.theme.SIZE36
import com.ars.comusenias.presentation.view_model.specialist.ProfilePatientViewModel


@Composable
fun StatisticsScreen(navController: NavHostController, viewModel: ProfilePatientViewModel) {
    StatisticsContent(viewModel = viewModel)
}

@Composable
fun StatisticsContent(
    viewModel: ProfilePatientViewModel,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Spacer(modifier = Modifier.height(SIZE10.dp))
        }
        items(viewModel.getStatistics()) {
            StatisticsComponent(levelModel = it)
        }
        item {
            Spacer(modifier = Modifier.height(SIZE36.dp))
        }
    }
}
