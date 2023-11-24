package com.example.comusenias.domain.repositories

import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.example.comusenias.domain.models.overlayView.ResultOverlayView
import kotlinx.coroutines.flow.Flow

interface CameraRepository {
    suspend fun captureAndSaveImage(navController: NavController)
    suspend fun showCameraPreview(previewView: PreviewView,cameraController: LifecycleCameraController?, lifecycleOwner: LifecycleOwner)
    suspend fun stopCameraPreview()
    fun startObjectDetection(): Flow<ResultOverlayView>

    suspend fun recordVideo(navController: NavController,lifecycleOwner: LifecycleOwner)
}