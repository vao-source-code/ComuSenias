package com.example.comusenias.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.constants.AlphabetConstants
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.model.LetterModel
import com.example.comusenias.domain.use_cases.letters.LettersFactoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LettersViewModel @Inject constructor(
    private val lettersUseCase: LettersFactoryUseCases,
) : ViewModel() {

    var lettersResponse by mutableStateOf<Response<List<LetterModel>>?>(Response.Loading)
    var letters by mutableStateOf<LetterModel>(LetterModel())


    fun searchSpecificLetter(letter: String = AlphabetConstants.Letter.A.toString()) =
        viewModelScope.launch(Dispatchers.IO) {
            lettersUseCase.searchImageLetterUseCase(letter).collect() { response ->
                lettersResponse = response as Response<List<LetterModel>>?
                if (response is Response.Success) {
                    //TODO traigo el primer elemento de la lista ya que se maneja de manera univoca
                    letters = response.data[0]
                }
            }
        }

}