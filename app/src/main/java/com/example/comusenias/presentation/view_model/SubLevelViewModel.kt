package com.example.comusenias.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.models.room.SubLevelEntity
import com.example.comusenias.domain.repositories.SubLevelRepository
import com.example.comusenias.presentation.component.home.StatusGame
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SubLevelViewModel @Inject constructor(
    private val subLevelRepository: SubLevelRepository
) : ViewModel() {

    var subLevel by mutableStateOf(SubLevelEntity("", StatusGame.BLOCKED))
        private set

    val subLevels = subLevelRepository.getAllSubLevel()

    /**
     * Obtiene un subnivel específico por su ID y lo establece como el subnivel actual.
     * @param idSubLevel el ID del subnivel a obtener.
     */
    fun fetchSubLevel(idSubLevel: String) {
        viewModelScope.launch {
            val result = getSubLevelById(idSubLevel)
            subLevel = result
        }
    }

    /**
     * Obtiene un subnivel específico por su ID.
     * @param idSubLevel el ID del subnivel a obtener.
     * @return el subnivel obtenido.
     */
    suspend fun getSubLevelById(idSubLevel: String): SubLevelEntity {
        return withContext(Dispatchers.IO) {
            subLevelRepository.getSubLevelById(idSubLevel)
        }
    }

    /**
     * Inserta una lista de subniveles en la base de datos.
     * @param subLevelEntity la lista de subniveles a insertar.
     */
    fun insertSubLevel(subLevelEntity: List<SubLevelEntity>) = viewModelScope.launch {
        subLevelRepository.insertSubLevel(subLevelEntity)
    }

    /**
     * Actualiza un subnivel específico en la base de datos.
     * @param subLevelEntity el subnivel a actualizar.
     */
    fun updateSubLevel(subLevelEntity: SubLevelEntity) = viewModelScope.launch {
        subLevelRepository.updateSubLevel(subLevelEntity)
    }

    /**
     * Elimina un subnivel específico de la base de datos.
     * @param subLevelEntity el subnivel a eliminar.
     */
    fun deleteSubLevel(subLevelEntity: SubLevelEntity) = viewModelScope.launch {
        subLevelRepository.deleteSubLevel(subLevelEntity)
    }
}