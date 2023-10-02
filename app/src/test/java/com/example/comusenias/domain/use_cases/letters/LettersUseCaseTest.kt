package com.example.comusenias.domain.use_cases.letters

import android.util.Log
import com.example.comusenias.domain.models.Letters
import com.example.comusenias.domain.models.Response
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

    private lateinit var lettersUseCase: LettersUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        lettersUseCase = LettersUseCase(
            getImage = GetImage(letterImageRepositoryImpl),
            searchImage = SearchImage(letterImageRepositoryImpl)
        )
    }


    @Test
    fun verificarBusquedaDeImagen() = runBlocking {
        val image = lettersUseCase.getImage("letra_a.png")
        Log.d("test", image.toString())
        assertNotEquals(image, null)
    }

    @Test
    fun verificarBusquedaDeImagenDeLetraTest() = runBlocking {
        //Given
        coEvery { letterImageRepositoryImpl.searchLetterImage("a") } returns object :
            Flow<Response<List<Letters>>> {
            override suspend fun collect(collector: FlowCollector<Response<List<Letters>>>) {
                collector.emit(Response.Success(listOf(Letters("id_number", "a", ""))))
            }
        }

        //When
        lettersUseCase.searchImage("a")

        //Then
        coVerify { letterImageRepositoryImpl.searchLetterImage("a") }
    }



}