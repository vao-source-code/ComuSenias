package com.example.comusenias.presentation.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.game.Level
import com.example.comusenias.domain.models.game.SubLevel
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

    private lateinit var viewModels: LevelViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        viewModels = LevelViewModel(levelUsesCases = levelUsesCases)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }


    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun getLevelsTest() {
        // Mock data
        val listSubLevel = arrayListOf<SubLevel>()
        listSubLevel.add(
            SubLevel(
                id = "1.1",
                name = "name",
                learnSign = "A",
                imageSing = "image",
                idGame = "idGame",
                idLevel = "1"
            )
        )
        listSubLevel.add(
            SubLevel(
                id = "1.2",
                name = "name",
                learnSign = "E",
                imageSing = "image",
                idGame = "idGame",
                idLevel = "1"
            )
        )
        val listStringSubLevel = arrayListOf<String>()
        listStringSubLevel.add("1.1")
        listStringSubLevel.add("1.2")

        val levels = listOf(
            Level(
                id = "1",
                name = "name",
                listStringSubLevel,
                listSubLevel
            )
        )
        val result = Response.Success(levels)

        coEvery { levelUsesCases.getLevels() } returns object :
            Flow<Response<List<Level>>> {
            override suspend fun collect(collector: FlowCollector<Response<List<Level>>>) {
                collector.emit(result)
            }
        }

        // Call the method to be tested
        viewModels.getLevels()

        // Verify the results
        coVerify { levelUsesCases.getLevels() }

        // Assert
        assertEquals(levels, viewModels.levels)
    }

}