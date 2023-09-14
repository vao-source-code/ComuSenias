package com.example.comusenias.presentation.view_model

import android.util.Log
import androidx.camera.core.ImageProxy
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.models.ObjectDetectionResult
import com.example.comusenias.domain.use_cases.camera.DetectObjectsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ObjectDectectionViewModel @Inject constructor(
    private val detectObjectUseCase: DetectObjectsUseCase
) : ViewModel() {

    // Propiedad para almacenar los resultados de detección.
    private val _detectionResults = mutableStateOf<List<ObjectDetectionResult>>(emptyList())
    val detectionResults: State<List<ObjectDetectionResult>> get() = _detectionResults


    // Función para realizar la detección de objetos en una imagen.
    fun detectObject(inputImage: ImageProxy) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Llamar al caso de uso detectObjectUseCase para realizar la detección.
                val results = detectObjectUseCase.invoke(inputImage)

                // Actualizar los resultados en la propiedad _detectionResults.
                _detectionResults.value = results
            } catch (e: Exception) {
                // Manejar errores en la detección de objetos.
                Log.e(TAG, "Error en la detección de objetos: ${e.message}")
            }
        }
    }

    companion object {
        private const val TAG = "ObjectDetectionViewModel"
    }
}
