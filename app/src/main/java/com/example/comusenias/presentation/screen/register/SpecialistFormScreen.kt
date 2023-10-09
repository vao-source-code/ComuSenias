package com.example.comusenias.presentation.screen.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.component.defaults.app.AuthenticationHeaderContent
import com.example.comusenias.presentation.component.defaults.app.ButtonApp
import com.example.comusenias.presentation.component.register.especialistForm.EspecialistFormContent
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.CONTINUE
import com.example.comusenias.presentation.ui.theme.SIZE30
import com.example.comusenias.presentation.ui.theme.SIZE50
import com.example.comusenias.presentation.ui.theme.SIZE90

@Composable
fun SpecialistFormScreen(modifier: Modifier, navController: NavHostController) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(start = SIZE30.dp, end = SIZE30.dp, top = SIZE90.dp),
            verticalArrangement = Arrangement.spacedBy(SIZE50.dp)
        ) {
            AuthenticationHeaderContent()
            EspecialistFormContent()
            ButtonApp(
                titleButton = CONTINUE,
                onClickButton = { navController.navigate(route = AppScreen.SpecialistScreen.route) }
            )
        }
    }
}
