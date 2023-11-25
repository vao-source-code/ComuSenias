package com.example.comusenias.presentation.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.comusenias.domain.models.game.LevelModel
import com.example.comusenias.domain.models.game.SubLevelModel
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.domain.use_cases.level.LevelFactory
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LevelViewModelTest {

    @RelaxedMockK
    private lateinit var levelUsesCases: LevelFactory

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: LevelViewModel
    private lateinit var listLevel: List<LevelModel>

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = LevelViewModel(levelUsesCases = levelUsesCases)

        val listSubLevel = mutableListOf(
            SubLevelModel(
                name = "A",
                image = "image",
                imageOnly = "imageOnly",
                randomLetter = "C",
                randomImage = "randomImage",
                successes = 0,
                failures = 0
            ),
            SubLevelModel(
                name = "E",
                image = "image",
                imageOnly = "imageOnly",
                randomLetter = "E",
                randomImage = "randomImage",
                successes = 0,
                failures = 0
            )
        )

        listLevel = listOf(
            LevelModel(
                id = "1",
                name = "name",
                subLevel = listSubLevel
            )
        )

        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    @After
    fun tearDown() {
        clearAllMocks()
        Dispatchers.resetMain()
    }

    @Test
    fun getLevelsTest() = runBlocking {
        coEvery { levelUsesCases.getLevels() } returns flowOf(Response.Success(listLevel))

        viewModel.getLevels()

        coVerify { levelUsesCases.getLevels() }
        assertEquals(viewModel.levels, listLevel)
    }

//    @Test
//    fun searchNameLevelTest() = runBlocking {
//        coEvery { levelUsesCases.getLevels() } returns flowOf(Response.Success(listLevel))
//
//        viewModel.getLevels()
//
//        viewModel.searchLevelByName("name")
//        coVerify { levelUsesCases.getLevels() }
//        assertEquals(listLevel, viewModel.levels)
//    }
}