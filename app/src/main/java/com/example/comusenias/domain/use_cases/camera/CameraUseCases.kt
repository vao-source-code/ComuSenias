package com.example.comusenias.domain.use_cases.camera

import android.content.Context
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.example.comusenias.domain.repositories.CustomCameraRepository
import javax.inject.Inject

class CameraUseCases @Inject constructor(private val customCameraRepository: CustomCameraRepository) {
    suspend fun showCameraPreview(previewView: PreviewView,lifecycleOwner: LifecycleOwner) = customCameraRepository.showCameraPreview(previewView,lifecycleOwner)
    suspend fun captureAndSave(context:Context) = customCameraRepository.captureAndSaveImage(context)
}