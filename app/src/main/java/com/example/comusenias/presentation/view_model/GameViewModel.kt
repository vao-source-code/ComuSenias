package com.example.comusenias.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.models.game.Game
import com.example.comusenias.domain.models.game.SubLevel

import com.example.comusenias.domain.use_cases.game.GameFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameUsesCases: GameFactory,
    var savedStateHandle: SavedStateHandle,
) : ViewModel() {

    /*Traigo los datos de la navegacion anterior el sub nivel para precargar los datos mas rapido */
    val data = savedStateHandle.get<String>("sublevel")
    val user = SubLevel.fromJson(data!!)
    var state by mutableStateOf(SubLevel())
        private set

    var game by mutableStateOf<Game>(Game())


    init {
        state = state.copy(
            idLevel = state.idLevel,
            id = state.id,
            name = state.name,
            imageSing = state.imageSing,
            learnSign = state.learnSign,
            idGame = state.idGame,
            game = state.game,
            isCompleted = state.isCompleted
        )
        getGameBySubLevelId(state.id)
    }

    fun getGameBySubLevelId(id: String) = viewModelScope.launch(Dispatchers.IO) {
        gameUsesCases.searchBySublevelId(id).collect() { response ->
            game = response
            state.game = game
        }
    }
}