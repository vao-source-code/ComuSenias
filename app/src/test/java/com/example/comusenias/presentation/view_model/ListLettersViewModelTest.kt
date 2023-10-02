package com.example.comusenias.presentation.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.comusenias.constants.AlphabetConstants
import com.example.comusenias.domain.models.Letters
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.use_cases.letters.LettersUseCase
import com.example.comusenias.domain.use_cases.letters.SearchImage
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
class ListLettersViewModelTest {

    @RelaxedMockK
    private lateinit var lettersUseCase: LettersUseCase

    @RelaxedMockK
    private lateinit var searchImage: SearchImage

    private lateinit var viewModel: ListLettersViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
       MockKAnnotations.init(this)
        viewModel = ListLettersViewModel(lettersUseCase = lettersUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }


    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun verificarBusquedaDeImagen() {
        //Given
        val letters = Letters("id_number", AlphabetConstants.Letter.F.toString(), "")
        val listOf = listOf(letters)

        //When

        coEvery { lettersUseCase.searchImage("a") } returns object :
            Flow<Response<List<Letters>>> {
            override suspend fun collect(collector: FlowCollector<Response<List<Letters>>>) {
                collector.emit(Response.Success(listOf(Letters("id_number", AlphabetConstants.Letter.F.toString(), ""))))
            }
        }


        //Then
        viewModel.searchSpecificLetter(AlphabetConstants.Letter.F.toString())
        viewModel.letters.letter = AlphabetConstants.Letter.F.toString()
        assert(viewModel.letters.letter== AlphabetConstants.Letter.F.toString())
    }

}