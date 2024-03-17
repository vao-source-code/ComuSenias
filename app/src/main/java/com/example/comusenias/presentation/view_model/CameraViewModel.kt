package com.example.comusenias.presentation.view_model

import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.comusenias.domain.models.mediapipe.DetectionFace
import com.example.comusenias.domain.models.mediapipe.DetectionHand
import com.example.comusenias.domain.models.recognizerSign.DetectionPose
import com.example.comusenias.domain.use_cases.camera.CameraUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val useCases: CameraUseCases
) : ViewModel() {

    private val _recognitionHandsResults = MutableStateFlow<DetectionHand?>(null)
    val recognitionHandsResults: StateFlow<DetectionHand?> = _recognitionHandsResults


    private val _recognitionFaceResults = MutableStateFlow<DetectionFace?>(null)
    val recognitionFaceResults: StateFlow<DetectionFace?> = _recognitionFaceResults


    private val _recognitionPoseResults = MutableStateFlow<DetectionPose?>(null)
    val recognitionPoseResults: StateFlow<DetectionPose?> = _recognitionPoseResults

    /**
     * Muestra la vista previa de la cámara en la vista previa proporcionada.
     *
     * @param preview La vista previa donde se mostrará la cámara.
     * @param lifecycleOwner El propietario del ciclo de vida de la vista previa.
     */
    fun showCameraPreview(preview: PreviewView, lifecycleOwner: LifecycleOwner) {
        viewModelScope.launch {
            useCases.showCameraPreview(preview,lifecycleOwner)
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
     * Inicia la detección de objetos en el flujo de la cámara.
     *
     * Los resultados se colectan en un flujo que actualiza un LiveData en el ViewModel.
     */
    fun startDetection() {
        viewModelScope.launch {
            useCases.startDetection()
        }
    }

    fun resultHands(){
       viewModelScope.launch {
           useCases.resultHands().collect {
               _recognitionHandsResults.value = it
           }
       }
    }

    fun resultFace(){
        viewModelScope.launch {
            useCases.resultFace().collect {
                _recognitionFaceResults.value = it
            }
        }
    }


    fun resultPose(){
        viewModelScope.launch {
            useCases.resultPose().collect {
                _recognitionPoseResults.value = it
            }
        }
    }


    /**
     * Detiene la vista  de la cámara.
     */
    fun stopCameraPreview() {
        viewModelScope.launch {
            useCases.stopCameraPreview()
        }
    }

    fun recordVideo(navController: NavController){
        viewModelScope.launch(Main){
            useCases.recordVideo(navController)
        }
    }

    fun stopVideo(){
        viewModelScope.launch(Main){
            useCases.stopRecording()
        }
    }
}