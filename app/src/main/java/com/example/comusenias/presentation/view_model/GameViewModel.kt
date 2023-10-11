package com.example.comusenias.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.models.model.game.GameModel
import com.example.comusenias.domain.models.model.game.SubLevelModel
import com.example.comusenias.domain.use_cases.game.GameFactory
import com.example.comusenias.domain.use_cases.level.LevelFactoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val levelUsesCases: LevelFactoryUseCases,
    private val gameUsesCases: GameFactory,
    private var savedStateHandle: SavedStateHandle,
) : ViewModel() {

    /*Traigo los datos de la navegacion anterior el sub nivel para precargar los datos mas rapido */
    val data = savedStateHandle.get<String>("sublevel")
    val user = SubLevelModel.fromJson(data!!)
    var state by mutableStateOf(SubLevelModel())
        private set

    var game by mutableStateOf<GameModel>(GameModel())



    init {
        state = state.copy(
            idLevel = state.idLevel,
            id = state.id,
            name = state.name,
            image = state.image,
            letter = state.letter
        )
        getGameBySubLevelId(state.id)
    }

    fun getGameBySubLevelId(id: String) = viewModelScope.launch(Dispatchers.IO) {
        gameUsesCases.searchBySublevelId(id).collect() { response ->
            game = response
        }
    }
}