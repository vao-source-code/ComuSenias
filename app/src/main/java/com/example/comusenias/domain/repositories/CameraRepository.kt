package com.example.comusenias.domain.repositories

import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.example.comusenias.domain.models.mediapipe.DetectionFace
import com.example.comusenias.domain.models.mediapipe.DetectionHand
import com.example.comusenias.domain.models.mediapipe.DetectionPose
import kotlinx.coroutines.flow.Flow

interface CameraRepository {
    suspend fun captureAndSaveImage(navController: NavController)
    suspend fun showCameraPreview(previewView: PreviewView, lifecycleOwner: LifecycleOwner)
    suspend fun stopCameraPreview()
    suspend fun startDetection()
    suspend fun resultHands():Flow<DetectionHand>
    suspend fun resultFace():Flow<DetectionFace>
    suspend fun resultPose():Flow<DetectionPose>
    suspend fun recordVideo(navController: NavController)
    suspend fun stopRecording()
}