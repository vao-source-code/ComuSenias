package com.ars.comusenias.domain.use_cases.camera

import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.ars.comusenias.domain.repositories.CameraRepository
import javax.inject.Inject

/**
 * Clase que contiene los casos de uso para la cámara.
 * Esta clase proporciona métodos para mostrar la vista previa de la cámara,
 * capturar y guardar una imagen, iniciar la detección de objetos y detener la vista previa de la cámara.
 *
 * @property customCameraRepository El repositorio que proporciona las operaciones de la cámara.
 */
class CameraUseCases @Inject constructor(private val customCameraRepository: CameraRepository) {

    /**
     * Muestra la vista previa de la cámara.
     *
     * @param previewView La vista de la cámara.
     * @param lifecycleOwner El propietario del ciclo de vida.
     */
    suspend fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner:LifecycleOwner,
        isSelectedCameraCapture:Boolean
    ) =
        customCameraRepository.showCameraPreview(previewView, lifecycleOwner,isSelectedCameraCapture)

    /**
     * Captura y guarda una imagen.
     *
     * @param navController El controlador de navegación.
     */
    suspend fun captureAndSave(navController: NavController ) = customCameraRepository.captureAndSaveImage(navController)

    /**
     * Inicia la detección de objetos.
     */
    suspend fun startObjectDetection() = customCameraRepository.startDetection()


    /**
     * Graba la cámara.
     */
    suspend fun recordVideo(navController: NavController) = customCameraRepository.recordVideo(navController)

    suspend fun stopRecording() = customCameraRepository.stopRecording()
}