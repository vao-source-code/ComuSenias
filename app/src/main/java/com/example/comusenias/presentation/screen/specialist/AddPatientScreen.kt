package com.example.comusenias.presentation.screen.specialist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.component.addPatient.UserProfileContent
import com.example.comusenias.presentation.component.defaults.app.ButtonApp
import com.example.comusenias.presentation.component.specialist.profilePatient.ChildData
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.CONFIRM
import com.example.comusenias.presentation.ui.theme.FLOAT01
import com.example.comusenias.presentation.ui.theme.SIZE100
import com.example.comusenias.presentation.ui.theme.SIZE20
import com.example.comusenias.presentation.ui.theme.SIZE30
import com.example.comusenias.presentation.ui.theme.SIZE48

@Composable
fun AddPatientScreen(modifier: Modifier, navHostController: NavHostController) {
    val patient = DataClassUtil.createSpecialistModelExample().childrenInCharge?.get(0)!!

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(
                top = SIZE100.dp,
                start = SIZE30.dp,
                end = SIZE30.dp,
                bottom = SIZE20.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            UserProfileContent(
                image = "patient.userModel.image",
                name = "patient.userModel.userName"
            )
            Spacer(modifier = Modifier.height(SIZE48.dp))
            ChildData(patient = patient)
            Spacer(modifier = Modifier.weight(FLOAT01))
            ButtonApp(
                titleButton = CONFIRM,
                onClickButton = { navHostController.navigate(AppScreen.SpecialistScreen.route) }
            )
        }
    }
}