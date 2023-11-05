package com.example.comusenias.utils

import com.example.comusenias.domain.models.game.LevelModel
import com.example.comusenias.domain.models.observation.ObservationModel
import com.example.comusenias.domain.models.users.ChildrenModel
import com.example.comusenias.domain.models.users.SpecialistModel

class DataClassUtil {
    companion object {
        fun createSpecialistModelExample(): SpecialistModel {
            val observation = ObservationModel(
                id = "observation_id",
                dateObservation = "30-10-2023",
                observation = "Example observation Example observation Example observation Example observationExample observation Example observation Example observation Example observation Example observation Example observation Example observation"
            )

            val exampleChildren = ChildrenModel(
                id = "example_id",
                name = "Federico Gamorra",
                tel = "1234567890",
                email = "john.doe@example.com",
                image = "",
                date = "30-10-2023",
                idSpecialist = "specialist_id",
                levelActual = 1,
                subLevelActual = 2,
                isPremium = true,
                levels = listOf(
                    LevelModel(
                        id = "level_id_1",
                        name = "Level 1",
                        subLevelId = listOf("sublevel_id_1", "sublevel_id_2")
                    ),
                    LevelModel(
                        id = "level_id_2",
                        name = "Level 2",
                        subLevelId = listOf("sublevel_id_3", "sublevel_id_4")
                    )
                )
            )

            val exampleChildren1 = ChildrenModel(
                id = "example_id",
                name = "Josefina Bautista",
                tel = "1234567890",
                email = "john.doe@example.com",
                image = "",
                date = "30-10-2023",
                idSpecialist = "specialist_id",
                levelActual = 1,
                subLevelActual = 2,
                isPremium = true,
                levels = listOf(
                    LevelModel(
                        id = "level_id_1",
                        name = "Level 1",
                        subLevelId = listOf("sublevel_id_1", "sublevel_id_2")
                    ),
                    LevelModel(
                        id = "level_id_2",
                        name = "Level 2",
                        subLevelId = listOf("sublevel_id_3", "sublevel_id_4")
                    )
                )
            )

            val exampleChildren2 = ChildrenModel(
                id = "example_id",
                name = "Franco Gutierrez",
                tel = "1234567890",
                email = "john.doe@example.com",
                image = "",
                date = "30-10-2023",
                idSpecialist = "specialist_id",
                levelActual = 1,
                subLevelActual = 2,
                isPremium = true,
                levels = listOf(
                    LevelModel(
                        id = "level_id_1",
                        name = "Level 1",
                        subLevelId = listOf("sublevel_id_1", "sublevel_id_2")
                    ),
                    LevelModel(
                        id = "level_id_2",
                        name = "Level 2",
                        subLevelId = listOf("sublevel_id_3", "sublevel_id_4")
                    )
                ),

                )

            return SpecialistModel(
                id = "specialist_id",
                name = "Dr. Smith",
                tel = "9876543210",
                email = "dr.smith@example.com",
                date = "30-10-2023",
                image = "",
                medicalLicense = "license_number",
                medicalLicenseExpiration = "2024-10-21",
                speciality = "Pediatrics",
                titleMedical = "Medical Director",
                childrenInCharge = listOf(
                    exampleChildren,
                    exampleChildren1,
                    exampleChildren2,
                )
            )
        }
    }
}
