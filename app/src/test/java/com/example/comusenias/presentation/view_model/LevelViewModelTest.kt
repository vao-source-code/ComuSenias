package com.example.comusenias.presentation.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.game.LevelModel
import com.example.comusenias.domain.models.game.SubLevelModel
import com.example.comusenias.domain.use_cases.level.LevelFactory
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LevelViewModelTest {

    @RelaxedMockK
    private lateinit var levelUsesCases: LevelFactory

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModels: LevelViewModel
    private var listLevel = listOf<LevelModel>()
    private val listSubLevel = arrayListOf<SubLevelModel>()
    private val listStringSubLevel = arrayListOf<String>()


    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        viewModels = LevelViewModel(levelUsesCases = levelUsesCases)

        listSubLevel.add(
            SubLevelModel(
                name = "A",
                image = "image",
                imageOnly = "imageOnly",
                randomLetter = "C",
                randomImage = "randomImage",
            )
        )
        listSubLevel.add(
            SubLevelModel(
                name = "E",
                image = "image",
                imageOnly = "imageOnly",
                randomLetter = "E",
                randomImage = "randomImage",

                )
        )

        listStringSubLevel.add("1.1")
        listStringSubLevel.add("1.2")

        listLevel = listOf(
            LevelModel(
                id = "1", name = "name", subLevel = listSubLevel
            )
        )

        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun getLevelsTest() {

        val result = Response.Success(listLevel)

        coEvery { levelUsesCases.getLevels() } returns object : Flow<Response<List<LevelModel>>> {
            override suspend fun collect(collector: FlowCollector<Response<List<LevelModel>>>) {
                collector.emit(result)
            }
        }

        // Call the method to be tested
        viewModels.getLevels()

        // Verify the results
        coVerify { levelUsesCases.getLevels() }

        // Assert
        assertEquals(listLevel, viewModels.levels)
    }

    @Test
    fun searchNameLevelTest() {
        val result = Response.Success(listLevel)

        coEvery { levelUsesCases.getLevels() } returns object : Flow<Response<List<LevelModel>>> {
            override suspend fun collect(collector: FlowCollector<Response<List<LevelModel>>>) {
                collector.emit(result)
            }
        }

        // Call the method to be tested
        viewModels.getLevels()

        // Verify the results
        coVerify { levelUsesCases.getLevels() }

        // Assert
        assertEquals(listLevel, viewModels.levels)
        assertEquals(listLevel, viewModels.searchLevelByName("name"))
    }

}