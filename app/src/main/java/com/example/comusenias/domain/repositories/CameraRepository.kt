package com.example.comusenias.domain.repositories

import android.content.Context
import android.net.Uri
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.ResultOverlayView
import kotlinx.coroutines.flow.Flow


interface CameraRepository {

    suspend fun captureAndSaveImage(context: Context): Response<Boolean>
    suspend fun showCameraPreview(previewView: PreviewView, lifecycleOwner: LifecycleOwner)
    suspend fun stopCameraPreview()
    fun startObjectDetection(): Flow<ResultOverlayView>
    suspend fun sendImageToServer(imageUri: Uri)
}