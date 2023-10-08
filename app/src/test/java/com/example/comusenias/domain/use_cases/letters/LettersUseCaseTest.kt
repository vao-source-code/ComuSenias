package com.example.comusenias.domain.use_cases.letters

import android.util.Log
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.letter.LetterModel
import com.example.comusenias.domain.repositories.LetterImageRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class LettersUseCaseTest {

    @RelaxedMockK
    private lateinit var letterImageRepositoryImpl: LetterImageRepository

    private lateinit var lettersUseCase: LettersFactoryUseCases

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        lettersUseCase = LettersFactoryUseCases(
            getImageUseCase = GetImageUseCase(letterImageRepositoryImpl),
            searchImageLetterUseCase = SearchImageLetterUseCase(letterImageRepositoryImpl)
        )
    }


    @Test
    fun verificarBusquedaDeImagen() = runBlocking {
        val image = lettersUseCase.getImageUseCase("letra_a.png")
        Log.d("test", image.toString())
        assertNotEquals(image, null)
    }

    @Test
    fun verificarBusquedaDeImagenDeLetraTest() = runBlocking {
        //Given
        coEvery { letterImageRepositoryImpl.searchLetterImage("a") } returns object :
            Flow<Response<List<LetterModel>>> {
            override suspend fun collect(collector: FlowCollector<Response<List<LetterModel>>>) {
                collector.emit(Response.Success(listOf(LetterModel("id_number", "a", ""))))
            }
        }

        //When
        lettersUseCase.searchImageLetterUseCase("a")

        //Then
        coVerify { letterImageRepositoryImpl.searchLetterImage("a") }
    }



}