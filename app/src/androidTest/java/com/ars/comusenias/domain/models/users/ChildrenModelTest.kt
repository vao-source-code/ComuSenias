package com.ars.comusenias.domain.models.users

import com.ars.comusenias.domain.models.game.LevelModel
import com.ars.comusenias.domain.models.observation.ObservationModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class ChildrenModelTest {

    private lateinit var userModel: UserModel
    private lateinit var specialist: SpecialistModel
    private lateinit var childrenModel: ChildrenModel
    private lateinit var levels: List<LevelModel>
    private lateinit var observations: List<ObservationModel>

    @Before
    fun setUp() {
        levels = listOf(LevelModel("1", "1", listOf()))
        observations = listOf(ObservationModel("1", "1", "1"), ObservationModel("2", "2", "2"))

        userModel = UserModel(
            password = "password",
            email = "email",
        )

        specialist = SpecialistModel(
            "12.12.2021",
            "license",
            "12.12.2021",
            "speciality",
        )


        childrenModel = ChildrenModel(
            date = "12.12.2021",
            idSpecialist = "1",
            id = "1",
            isPremium = false,
            levels = levels,
        )
    }

    @Test
    fun whenCreateChildrenReturnEqualsDates() {
        val date = "12.12.2021"
        assertEquals(date, childrenModel.date)
        assertEquals(false, childrenModel.isPremium)
        assertEquals(levels, childrenModel.levels)
    }

    @Test
    fun changeParametersChildrenReturnChanges() {
        childrenModel.isPremium = true

        assertEquals(true, childrenModel.isPremium)
    }
}