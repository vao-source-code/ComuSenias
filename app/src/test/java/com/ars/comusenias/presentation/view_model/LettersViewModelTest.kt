package com.ars.comusenias.presentation.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ars.comusenias.domain.use_cases.letters.LettersFactoryUseCases
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
class LettersViewModelTest {

    @RelaxedMockK
    private lateinit var lettersUseCase: LettersFactoryUseCases

    private lateinit var viewModel: LettersViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = LettersViewModel(lettersUseCase = lettersUseCase)
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    @After
    fun tearDown() {
        clearAllMocks()
        Dispatchers.resetMain()
    }
/*
    @Test
    fun `verificar busqueda de imagen`() {
        runBlocking {
            val letter = AlphabetConstants.Letter.F.toString()
            val expectedLetterModel = LetterModel("id_number", letter, "")
            coEvery { lettersUseCase.searchImageLetterUseCase(letter) } returns flowOf(Response.Success(listOf(expectedLetterModel)))

            viewModel.searchSpecificLetter(letter)

            coVerify { lettersUseCase.searchImageLetterUseCase(letter) }
            assertEquals(viewModel.letters.letter, letter)
        }
    }*/
}