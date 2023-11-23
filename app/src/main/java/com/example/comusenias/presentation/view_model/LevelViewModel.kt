package com.example.comusenias.presentation.view_model

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.R
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.domain.models.game.LevelModel
import com.example.comusenias.domain.models.game.SubLevelModel
import com.example.comusenias.domain.use_cases.level.LevelFactory
import com.example.comusenias.presentation.component.home.StatusGame
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
    var isVideo by mutableStateOf(false)
    var onOptionSelected by mutableStateOf(EMPTY_STRING)
    var levelSelected by mutableStateOf(EMPTY_STRING)
    var subLevelSelected by mutableStateOf(EMPTY_STRING)
    var subLevelModelSelected by mutableStateOf(SubLevelModel())
/*
    init {
        getLevels()
    }
*/

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


    fun getSubLevelById(idLevel: String, nameSubLevel: String): SubLevelModel {
        if(this.levelSelected != idLevel  || this.subLevelSelected != nameSubLevel) {
            levelSelected = idLevel
            subLevelSelected = nameSubLevel
            levelsResponse = Response.Loading
            val subLevel = returnSubLevelModel()
            levelsResponse = if (subLevel != null) Response.Success(levels) else Response.Error(
                exception = Exception(NOT_RESPONSE_SUB_LEVEL)
            )
            isVideo = subLevel?.esVideo ?: false
            subLevelModelSelected = subLevel ?: SubLevelModel()
            return subLevel ?: throw Exception(NOT_RESPONSE_SUB_LEVEL)
        }
        return subLevelModelSelected
    }

    fun validateLetterCamera(): Boolean {
        return onOptionSelected.equals(subLevelSelected, ignoreCase = true)
    }

    fun returnSubLevelModel () =
        SubLevelModel(
            "1",
            "prueba",
            "https://noticias.nmas.com.mx/wp-content/uploads/2022/03/WhatsApp-ok-mano.jpg",
            "https://firebasestorage.googleapis.com/v0/b/pruebacomu-c4bd2.appspot.com/o/Permiso.mp4?alt=media&token=25801565-2121-4524-819d-b4555e301866",
            "Permiso",
            "https://noticias.nmas.com.mx/wp-content/uploads/2022/03/WhatsApp-ok-mano.jpg",
            "https://firebasestorage.googleapis.com/v0/b/pruebacomu-c4bd2.appspot.com/o/Permiso.mp4?alt=media&token=25801565-2121-4524-819d-b4555e301866",
            StatusGame.IN_PROGRESS,
            true
        )

}



