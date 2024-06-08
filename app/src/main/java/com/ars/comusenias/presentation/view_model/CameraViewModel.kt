package com.ars.comusenias.presentation.view_model

import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.ars.comusenias.domain.models.overlayView.ResultOverlayView
import com.ars.comusenias.domain.use_cases.camera.CameraUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val useCases: CameraUseCases
) : ViewModel() {

    private val _recognitionResults = MutableStateFlow<ResultOverlayView?>(null)
    val recognitionResults: StateFlow<ResultOverlayView?> = _recognitionResults

    /**
     * Muestra la vista previa de la cámara en la vista previa proporcionada.
     *
     * @param preview La vista previa donde se mostrará la cámara.
     * @param lifecycleOwner El propietario del ciclo de vida de la vista previa.
     */
    fun showCameraPreview(preview: PreviewView,lifecycleOwner: LifecycleOwner,isSelectedCameraCapture:Boolean) {
        viewModelScope.launch {
            useCases.showCameraPreview(preview,lifecycleOwner,isSelectedCameraCapture)
        }
    }

    /**
     * Captura una imagen de la cámara y la guarda.
     *
     * @param navController El controlador de navegación utilizado para navegar después de guardar la imagen.
     */
    fun captureAndSave(navController: NavController) {
        viewModelScope.launch {
            useCases.captureAndSave(navController)
        }
    }

    /**
     * Inicia la detección  en el flujo de la cámara.
     *
     * Los resultados se colectan en un flujo que actualiza un LiveData en el ViewModel.
     */
    fun startDetection() {
        viewModelScope.launch {
            val resultsFlow = useCases.startObjectDetection()
            resultsFlow.collect { results ->
                _recognitionResults.value = results
            }
        }
    }


    fun recordVideo(navController: NavController){
        viewModelScope.launch(Dispatchers.IO) {
            useCases.recordVideo(navController)
        }
    }

    fun stopVideo(){
        viewModelScope.launch(Dispatchers.IO) {
            useCases.stopRecording()
        }
    }
}