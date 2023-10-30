package com.example.comusenias.presentation.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.comusenias.constants.AlphabetConstants
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.domain.models.letter.LetterModel
import com.example.comusenias.domain.use_cases.letters.LettersFactoryUseCases
import com.example.comusenias.domain.use_cases.letters.SearchImageLetterUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LettersViewModelTest {

    @RelaxedMockK
    private lateinit var lettersUseCase: LettersFactoryUseCases

    @RelaxedMockK
    private lateinit var searchImage: SearchImageLetterUseCase

    private lateinit var viewModel: LettersViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
       MockKAnnotations.init(this)
        viewModel = LettersViewModel(lettersUseCase = lettersUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }


    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun verificarBusquedaDeImagen() {
        //Given
        val letters = LetterModel("id_number", AlphabetConstants.Letter.F.toString(), "")
        val listOf = listOf(letters)

        //When

        coEvery { lettersUseCase.searchImageLetterUseCase("a") } returns object :
            Flow<Response<List<LetterModel>>> {
            override suspend fun collect(collector: FlowCollector<Response<List<LetterModel>>>) {
                collector.emit(Response.Success(listOf(LetterModel("id_number", AlphabetConstants.Letter.F.toString(), ""))))
            }
        }


        //Then
        viewModel.searchSpecificLetter(AlphabetConstants.Letter.F.toString())
        viewModel.letters.letter = AlphabetConstants.Letter.F.toString()
        assert(viewModel.letters.letter== AlphabetConstants.Letter.F.toString())
    }

}