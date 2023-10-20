package com.example.comusenias.domain.repositories

import android.content.Context
import androidx.camera.core.ImageAnalysis
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.example.comusenias.domain.models.ResultOverlayView
import kotlinx.coroutines.flow.Flow


interface CameraRepository {

    suspend fun captureAndSaveImage(context: Context)
    suspend fun  showCameraPreview(previewView:PreviewView, lifecycleOwner: LifecycleOwner)
    suspend fun stopCameraPreview()


    fun startObjectDetection(): Flow<ResultOverlayView>


}