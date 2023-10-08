package com.example.comusenias.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.model.game.LevelModel
import com.example.comusenias.domain.use_cases.level.LevelFactoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LevelViewModel @Inject constructor(
    private val levelUsesCases: LevelFactoryUseCases
) : ViewModel() {


    var levelsResponse by mutableStateOf<Response<List<LevelModel>>?>(Response.Loading)

    init {
        getLevels()
    }

    fun getLevels() = viewModelScope.launch(Dispatchers.IO) {
        levelUsesCases.getLevelsUseCase().collect() { response ->
            levelsResponse = response as Response<List<LevelModel>>?
        }
    }

}
