package com.ars.comusenias.presentation.component.specialist.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ars.comusenias.domain.models.state.PacientState
import com.ars.comusenias.domain.models.users.ChildrenModel
import com.ars.comusenias.domain.models.users.SpecialistModel
import com.ars.comusenias.presentation.component.defaults.FloatingButtonDefault
import com.ars.comusenias.presentation.component.home.TopBarHome
import com.ars.comusenias.presentation.navigation.AppScreen
import com.ars.comusenias.presentation.ui.theme.SIZE15
import com.ars.comusenias.presentation.ui.theme.SIZE3

@Composable
fun SpecialistHomeContent(
    navController: NavHostController, specialist:   SpecialistModel
) {
    Log.d("SpecialistHomeContent", "Usuario: $specialist")
    Scaffold(topBar = {
        Surface(shadowElevation = SIZE3.dp) {

            TopBarHome(
                name = specialist.name, image = specialist.image,
                onClickNotification = { navController.navigate(AppScreen.NotificationScreen.route) },
                onclickProfile = { navController.navigate(AppScreen.SpecialistProfileScreen.route) },
                onclickSupport = { navController.navigate(AppScreen.SupportScreen.route) },

                )
        }
    }, floatingActionButton = {
        FloatingButtonDefault {
            navController.navigate(AppScreen.LectorQRScreen.route)
        }
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            PatientContainer(
                patients = specialist.childrenInCharge, navController = navController
            )
        }
    }
}

@Composable
fun PatientContainer(
    patients: List<ChildrenModel>?, navController: NavHostController
) {
    val getPatients = remember { patients }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(SIZE15.dp)
    ) {
        item {
            Title()
        }
        getPatients.let {
            if (it != null) {
                if (it.isNotEmpty()) {
                    items(it) { patient ->
                        CardProfileUser(user = patient, onClickCard = {
                            navController.navigate(
                                route = AppScreen.ProfilePatientScreen.passPacient(getDataPacient(patient).toJson())
                            )
                        })
                    }
                }
            } else {
                item {
                    DontYouHavePatients()
                }
            }
        }
        if(getPatients?.size == 0){
            item {
                DontYouHavePatients()
            }
        }
    }
}

private fun getDataPacient(patient: ChildrenModel): PacientState {
    return PacientState(
        id = patient.id,
        name = patient.name,
        phone = patient.phone,
        email = patient.email,
        date = patient.date,
        idSpecialist = patient.idSpecialist,
    )
}


