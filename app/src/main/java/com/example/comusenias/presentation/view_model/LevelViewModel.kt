package com.example.comusenias.presentation.view_model

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.game.LevelModel
import com.example.comusenias.domain.models.game.SubLevelModel
import com.example.comusenias.domain.use_cases.level.LevelFactory
import com.example.comusenias.presentation.ui.theme.ERROR_UNKNOWN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class LevelViewModel @Inject constructor(
    private val levelUsesCases: LevelFactory,
) : ViewModel() {

    var levelsResponse by mutableStateOf<Response<List<LevelModel>>?>(Response.Loading)
    var levels by mutableStateOf<List<LevelModel>>(listOf())
    var choiceOfOption by mutableStateOf<MutableList<Boolean>>(mutableListOf())
    var onOptionSelected by mutableStateOf("")

    init {
        getLevels()
    }

    fun getLevels() = viewModelScope.launch(Dispatchers.IO) {
        levelUsesCases.getLevels().collect() { response ->
            levelsResponse = response
            if (response is Response.Success) {
                levels = response.data
            }
        }
    }

    fun getSubLevelsByName(nameLevel: String): List<SubLevelModel> {
        levels.forEach {
            if (it.name == nameLevel) {
                return it.subLevel
            }
        }
        return listOf()
    }

    fun searchLevelByName(nameLevel: String): List<LevelModel> {
        val list = ArrayList<LevelModel>()
        levels.forEach {
            if (it.name == nameLevel) {
                list.add(it)
            }
        }
        return list
    }

    fun getSubLevelById(idLevel: String, nameSubLevel: String): SubLevelModel? {
        levelsResponse = Response.Loading
        levels.forEach {
            if (it.id == idLevel) {
                it.subLevel.forEach { subLevel ->
                    if (subLevel.name == nameSubLevel) {
                        levelsResponse = Response.Success(levels)
                        return subLevel
                    }
                }
            }
        }
        levelsResponse = Response.Error(exception = Exception(ERROR_UNKNOWN))
        return null
    }
}
