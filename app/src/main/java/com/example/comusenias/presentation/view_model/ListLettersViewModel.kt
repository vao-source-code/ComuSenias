package com.example.comusenias.presentation.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.use_cases.letters.LettersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListLettersViewModel @Inject constructor(
    private val lettersUseCase: LettersUseCase,
) : ViewModel() {


    fun getLetraEspecifica(letra : String = "letra_a.png")= viewModelScope.launch (Dispatchers.IO) {
       val image =  lettersUseCase.getImage("letra_a.png")
        Log.d("test" , image.toString())
    }


}