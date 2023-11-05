package com.example.comusenias.domain.models.users

import com.example.comusenias.domain.models.game.LevelModel
import com.example.comusenias.domain.models.observation.ObservationModel
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
            specialist = specialist,
            idSpecialist = "1",
            id = "1",
            isPremium = false,
            levels = levels,
            observation = observations
        )
    }

    @Test
    fun whenCreateChildrenReturnEqualsDates() {
        val date = "12.12.2021"
        assertEquals(date, childrenModel.date)
        assertEquals(specialist, childrenModel.specialist)
        assertEquals(false, childrenModel.isPremium)
        assertEquals(levels, childrenModel.levels)
        assertEquals(observations, childrenModel.observation)
    }

    @Test
    fun changeParametersChildrenReturnChanges() {
        childrenModel.specialist = null
        childrenModel.isPremium = true
        childrenModel.observation = null

        assertEquals(null, childrenModel.specialist)
        assertEquals(true, childrenModel.isPremium)
        assertEquals(null, childrenModel.observation)
    }
}