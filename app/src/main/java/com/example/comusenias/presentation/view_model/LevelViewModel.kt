package com.example.comusenias.presentation.view_model

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.domain.models.game.LevelModel
import com.example.comusenias.domain.models.game.SubLevelModel
import com.example.comusenias.domain.use_cases.level.LevelFactory
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.example.comusenias.presentation.ui.theme.NOT_RESPONSE_SUB_LEVEL
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
    var onOptionSelected by mutableStateOf(EMPTY_STRING)
    var levelSelected by mutableStateOf(EMPTY_STRING)
    var subLevelModel by mutableStateOf(EMPTY_STRING)

    init {
        getLevels()
    }

    /**
     * Obtiene una lista de niveles utilizando el caso de uso [levelUsesCases.getLevels].
     *
     * Esta función se lanza en [Dispatchers.IO] porque la operación de obtener niveles es potencialmente de larga duración.
     *
     * Los resultados se recogen en un flujo y se almacenan en [levelsResponse]. Si la respuesta es un éxito, la lista de niveles se almacena en [levels].
     */
    fun getLevels() = viewModelScope.launch(Dispatchers.IO) {
        levelUsesCases.getLevels().collect { response ->
            levelsResponse = response
            if (response is Response.Success) {
                levels = response.data
            }
        }
    }

    /**
     * Busca niveles por su nombre.
     *
     * @param nameLevel El nombre del nivel que se busca.
     *
     * @return Una lista de [LevelModel] que coincide con el nombre proporcionado.
     *
     * Esta función recorre la lista [levels] y devuelve una nueva lista que contiene solo los elementos cuyo nombre coincide con [nameLevel].
     */
    fun searchLevelByName(nameLevel: String): List<LevelModel> {
        return levels.filter { it.name == nameLevel }
    }

    /**
     * Obtiene un subnivel a partir de su identificador y nombre.
     *
     * @param idLevel El identificador del nivel que contiene el subnivel.
     * @param nameSubLevel El nombre del subnivel que se busca.
     *
     * @return El [SubLevelModel] del subnivel si se encuentra.
     * @throws Exception si no se encuentra el subnivel.
     *
     * Esta función realiza las siguientes acciones:
     * 1. Establece [levelsResponse] a [Response.Loading].
     * 2. Busca en la lista [levels] un nivel con el identificador [idLevel] y, dentro de ese nivel, un subnivel con el nombre [nameSubLevel].
     * 3. Establece [levelsResponse] a [Response.Success] con la lista [levels] si se encuentra el subnivel, o a [Response.Error] con una excepción si no se encuentra.
     * 4. Devuelve el [SubLevelModel] del subnivel si se encuentra, o lanza una excepción si no se encuentra.
     */
    fun getSubLevelById(idLevel: String, nameSubLevel: String): SubLevelModel {
        levelsResponse = Response.Loading
        val subLevel = levels.find { it.id == idLevel }?.subLevel?.find { it.name == nameSubLevel }
        levelsResponse = if (subLevel != null) Response.Success(levels) else Response.Error(
            exception = Exception(NOT_RESPONSE_SUB_LEVEL)
        )
        return subLevel ?: throw Exception(NOT_RESPONSE_SUB_LEVEL)
    }

    fun validateLetterCamera(): Boolean {
        return onOptionSelected.equals(subLevelModel, ignoreCase = true)
    }
}
