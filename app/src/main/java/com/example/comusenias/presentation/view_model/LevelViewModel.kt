package com.example.comusenias.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.game.Level
import com.example.comusenias.domain.models.game.SubLevel
import com.example.comusenias.domain.use_cases.level.LevelFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LevelViewModel @Inject constructor(
    private val levelUsesCases: LevelFactory,
) : ViewModel() {

    var levelsResponse by mutableStateOf<Response<List<Level>>?>(Response.Loading)
    var levels by mutableStateOf<List<Level>>(listOf())

    init {
        getLevels()
    }

    fun getLevels() = viewModelScope.launch(Dispatchers.IO) {
        levelUsesCases.getLevels().collect() { response ->
            levelsResponse = response as Response<List<Level>>?
            if (response is Response.Success) {
                levels = response.data
            }
        }
    }

    fun getSubLevels(idLevel: String): List<SubLevel> {
        if (levels.isEmpty()) {
            getLevels()
        }
        levels.forEach {
            if (it.id == idLevel) {
                return it.subLevelModel
            }
        }
        return listOf()
    }


}
