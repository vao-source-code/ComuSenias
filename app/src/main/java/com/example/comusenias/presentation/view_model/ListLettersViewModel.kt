package com.example.comusenias.presentation.view_model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.constants.AlphabetConstants
import com.example.comusenias.domain.models.Letters
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.use_cases.letters.LettersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListLettersViewModel @Inject constructor(
    private val lettersUseCase: LettersUseCase,
) : ViewModel() {

    var lettersResponse by mutableStateOf<Response<List<Letters>>?>(Response.Loading)
    var letters by mutableStateOf<Letters>(Letters())

    fun getSpecificLetter(letra: String = "letra_a.png") = viewModelScope.launch(Dispatchers.IO) {
        val image = lettersUseCase.getImage("letra_a.png")
        Log.d("test", image.toString())
    }

    fun searchSpecificLetter(letra: String = AlphabetConstants.Letter.A.toString()) =
        viewModelScope.launch(Dispatchers.IO) {
            lettersUseCase.searchImage(letra).collect() { response ->
                lettersResponse = response as Response<List<Letters>>?
                if (response is Response.Success) {
                    //TODO traigo el primer elemento de la lista ya que se maneja de manera univoca
                    letters = response.data[0]
                }
            }
        }

}