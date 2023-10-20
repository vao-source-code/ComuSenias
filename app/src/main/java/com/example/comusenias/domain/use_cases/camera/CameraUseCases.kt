package com.example.comusenias.domain.use_cases.camera

import android.app.Activity
import android.content.Context
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.example.comusenias.domain.repositories.CameraRepository
import javax.inject.Inject

class CameraUseCases @Inject constructor(private val customCameraRepository: CameraRepository) {
    suspend fun showCameraPreview(previewView: PreviewView, lifecycleOwner: LifecycleOwner) =
        customCameraRepository.showCameraPreview(previewView, lifecycleOwner)

    suspend fun captureAndSave(activity: Activity) =
        customCameraRepository.captureAndSaveImage(activity)

    fun startObjectDetection() = customCameraRepository.startObjectDetection()
}