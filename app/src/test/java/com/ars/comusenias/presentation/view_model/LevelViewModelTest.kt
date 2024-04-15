package com.ars.comusenias.presentation.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ars.comusenias.domain.models.game.LevelModel
import com.ars.comusenias.domain.use_cases.level.LevelFactory
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
class LevelViewModelTest {

    @RelaxedMockK
    private lateinit var levelUsesCases: LevelFactory

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: LevelViewModel
    private lateinit var listLevel: List<LevelModel>

//    @Before
//    fun setup() {
//        MockKAnnotations.init(this)
//        viewModel = LevelViewModel(levelUsesCases = levelUsesCases)
//
//        val listSubLevel = mutableListOf(
//            SubLevelModel(
//                name = "A",
//                image = "image",
//                imageOnly = "imageOnly",
//                randomLetter = "C",
//                randomImage = "randomImage",
//                successes = 0,
//                failures = 0
//            ),
//            SubLevelModel(
//                name = "E",
//                image = "image",
//                imageOnly = "imageOnly",
//                randomLetter = "E",
//                randomImage = "randomImage",
//                successes = 0,
//                failures = 0
//            )
//        )
//
//        listLevel = listOf(
//            LevelModel(
//                id = "1",
//                name = "name",
//                subLevel = listSubLevel
//            )
//        )
//
//        Dispatchers.setMain(TestCoroutineDispatcher())
//    }
//
//    @After
//    fun tearDown() {
//        clearAllMocks()
//        Dispatchers.resetMain()
//    }
//
//    @Test
//    fun getLevelsTest() = runBlocking {
//        coEvery { levelUsesCases.getLevels() } returns flowOf(Response.Success(listLevel))
//
//        viewModel.getLevels()
//
//        coVerify { levelUsesCases.getLevels() }
//        assertEquals(viewModel.levels, listLevel)
//    }
//
////    @Test
////    fun searchNameLevelTest() = runBlocking {
////        coEvery { levelUsesCases.getLevels() } returns flowOf(Response.Success(listLevel))
////
////        viewModel.getLevels()
////
////        viewModel.searchLevelByName("name")
////        coVerify { levelUsesCases.getLevels() }
////        assertEquals(listLevel, viewModel.levels)
////    }
}