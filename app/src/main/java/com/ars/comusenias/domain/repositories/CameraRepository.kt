package com.ars.comusenias.domain.repositories

import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.ars.comusenias.domain.models.overlayView.ResultOverlayView
import kotlinx.coroutines.flow.Flow

interface CameraRepository {

    suspend fun captureAndSaveImage(navController: NavController)

    suspend fun showCameraPreview(previewView: PreviewView,lifecycleOwner: LifecycleOwner)
    suspend fun startDetection(): Flow<ResultOverlayView>

    suspend fun recordVideo(navController: NavController)
    suspend fun stopRecording()
}