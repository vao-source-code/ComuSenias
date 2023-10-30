package com.example.comusenias.presentation.screen.specialist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.comusenias.domain.models.game.LevelModel
import com.example.comusenias.domain.models.game.SubLevelModel
import com.example.comusenias.domain.models.observation.Observation
import com.example.comusenias.domain.models.users.ChildrenModel
import com.example.comusenias.domain.models.users.SpecialistModel
import com.example.comusenias.domain.models.users.UserModel
import com.example.comusenias.presentation.component.home.StatusGame
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

            val subLevelModel = SubLevelModel(
                idLevel = "level_id",
                name = "SubLevelName",
                image = "example_image_url",
                imageOnly = "example_image_url",
                randomLetter = "A",
                randomImage = "example_image_url",
                isCompleted = StatusGame.BLOCKED
            )

            val levelModel = LevelModel(
                id = "level_id",
                name = "LevelName",
                subLevelId = listOf("sublevel_id_1", "sublevel_id_2"),
                subLevel = mutableListOf(subLevelModel),
                isCompleted = StatusGame.IN_PROGRESS
            )

            val userModelSpecialist = UserModel(
                id = "example_id",
                email = "example_email@example.com",
                password = "example_password",
            )
            val userModelChildren = UserModel(
                id = "1",
                email = "example_email@example.com",
                password = "example_password",
            )


            val childrenModel = ChildrenModel(
                id = "",
                date = "2023-10-21",
                specialist = null,
                levelActual = 1,
                subLevelActual = 2,
                isPremium = true,
                levels = listOf(levelModel),
                observation = listOf(
                    observation,
                    observation,
                    observation,
                    observation,
                    observation,
                    observation,
                    observation,
                    observation,
                    observation,
                    observation,
                    observation,
                    observation,
                    observation,
                    observation,
                    observation,
                    observation,
                    observation,
                    observation
                    )
            )

            return  SpecialistModel(
                id = "",
                date = "2023-10-21",
                medicalLicense = "example_license_number",
                medicalLicenseExpiration = "2024-10-21",
                speciality = "Pediatrics",
                childrenInCharge =
                listOf(
                    childrenModel,
                    childrenModel,
                    childrenModel,
                    childrenModel,
                    childrenModel,
                    childrenModel,
                    childrenModel,
                    childrenModel,
                    childrenModel,
                    childrenModel,
                    childrenModel,
                    childrenModel,
                    childrenModel,
                    childrenModel,
                    childrenModel,
                    childrenModel,
                )
            )
        }
    }
}