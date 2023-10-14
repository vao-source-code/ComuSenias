package com.example.comusenias.domain.models.users

import com.example.comusenias.domain.models.game.LevelModel
import com.example.comusenias.domain.models.observation.Observation
import com.example.comusenias.presentation.component.home.StatusGame
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class ChildrenModelTest {

    private lateinit var userModel: UserModel
    private lateinit var specialist: SpecialistModel
    private lateinit var childrenModel: ChildrenModel
    private lateinit var levels: List<LevelModel>
    private lateinit var observations: List<Observation>

    @Before
    fun setUp() {
        levels = listOf(
            LevelModel(
                name = "Level 1",
                isCompleted = StatusGame.IN_PROGRESS,
                subLevel = ArrayList()
            ),
            LevelModel(
                name = "Level 2",
                isCompleted = StatusGame.IN_PROGRESS,
                subLevel = ArrayList()
            )
        )
        observations = listOf(Observation("1", "1", "1"), Observation("2", "2", "2"))


        userModel = UserModel(
                userName = "name",
                password = "password",
                email = "email",
        )

        specialist = SpecialistModel(
                userModel,
                "12.12.2021",
                "license",
                "12.12.2021",
                "speciality",
                listOf()
        )


        childrenModel = ChildrenModel(
                userModel = userModel,
                date = "12.12.2021",
                specialist = specialist,
                isPremium = false,
                levels = levels,
                observation = observations
        )
    }

    @Test
    fun whenCreateChildrenReturnEqualsDates() {
        val date = "12.12.2021"
        assertEquals(userModel, childrenModel.userModel)
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