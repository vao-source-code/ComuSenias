package com.example.comusenias.presentation.screen.specialist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.comusenias.domain.models.game.LevelModel
import com.example.comusenias.domain.models.observation.Observation
import com.example.comusenias.domain.models.users.ChildrenModel
import com.example.comusenias.domain.models.users.SpecialistModel
import com.example.comusenias.presentation.component.specialist.home.SpecialistHomeContent

@Composable
fun SpecialistScreen(navController: NavHostController, modifier: Modifier) {

    Box(modifier = modifier.fillMaxSize()) {
        SpecialistHomeContent(
            navController = navController,
            specialist =  DataClassUtil.createSpecialistModelExample()
        )
    }
}

class DataClassUtil {
    companion object {
        fun createSpecialistModelExample(): SpecialistModel {
            val observation = Observation(
                id = "observation_id",
                dateObservation = "2023-10-21",
                observation = "Example observation Example observation Example observation Example observationExample observation Example observation Example observation Example observation Example observation Example observation Example observation"
            )

            val exampleChildren = ChildrenModel(
                id = "example_id",
                name = "John Doe",
                tel = "1234567890",
                email = "john.doe@example.com",
                image = "example_image_url",
                date = "2023-10-21",
                idSpecialist = "specialist_id",
                levelActual = 1,
                subLevelActual = 2,
                isPremium = true,
                levels = listOf(
                    LevelModel(id = "level_id_1", name = "Level 1", subLevelId = listOf("sublevel_id_1", "sublevel_id_2")),
                    LevelModel(id = "level_id_2", name = "Level 2", subLevelId = listOf("sublevel_id_3", "sublevel_id_4"))
                ),
                observation = listOf(
                    observation,
                    observation,
                    observation,
                    observation,
                    )
            )

            return SpecialistModel(
                id = "specialist_id",
                name = "Dr. Smith",
                tel = "9876543210",
                email = "dr.smith@example.com",
                date = "2023-10-21",
                image = "example_image_url",
                medicalLicense = "license_number",
                medicalLicenseExpiration = "2024-10-21",
                speciality = "Pediatrics",
                titleMedical = "Medical Director",
                childrenInCharge = listOf(
                    exampleChildren,
                    exampleChildren,
                    exampleChildren,
                    exampleChildren
                )
            )
        }
    }
}