package com.ars.comusenias.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ars.comusenias.constants.AlphabetConstants
import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.domain.models.letter.LetterModel
import com.ars.comusenias.domain.use_cases.letters.LettersFactoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LettersViewModel @Inject constructor(
    private val lettersUseCase: LettersFactoryUseCases,
) : ViewModel() {

    var lettersResponse by mutableStateOf<Response<List<LetterModel>>?>(Response.Loading)
    var letters by mutableStateOf(LetterModel())

    fun searchSpecificLetter(letter: String = AlphabetConstants.Letter.A.toString()) =
        viewModelScope.launch(Dispatchers.IO) {
            lettersUseCase.searchImageLetterUseCase(letter).collect() { response ->
                lettersResponse = response as Response<List<LetterModel>>?
                if (response is Response.Success) {
                    letters = response.data[0]
                }
            }
        }
}