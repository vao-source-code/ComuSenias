package com.example.comusenias.presentation.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.comusenias.domain.models.game.Game
import com.example.comusenias.domain.models.game.SubLevel
import com.example.comusenias.domain.use_cases.game.GameFactory
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GameViewModelTest {

    @RelaxedMockK
    private lateinit var gameUsesCases: GameFactory

    private  var savedStateHandle: SavedStateHandle = SavedStateHandle()
    private lateinit var viewModel: GameViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        savedStateHandle["sublevel"] = SubLevel("id", "name", "description", "image", "levelId").toJson()
        viewModel = GameViewModel(
            gameUsesCases = gameUsesCases,
            savedStateHandle = savedStateHandle
        )
        Dispatchers.setMain(Dispatchers.Unconfined)
    }


    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }


    @Test
    fun testGetGameBySubLevelId()  {
        // Mock data
        val subLevelId = "subLevelId"
        val game = Game() // Replace with appropriate Game object

        // Set up the mock behavior for gameUseCases.searchBySublevelId
        coEvery { gameUsesCases.searchBySublevelId(subLevelId) } returns flowOf(game)

        // Call the method to be tested
        viewModel.getGameBySubLevelId(subLevelId)

        // Verify the results
        coVerify { gameUsesCases.searchBySublevelId(subLevelId) }
    }

}