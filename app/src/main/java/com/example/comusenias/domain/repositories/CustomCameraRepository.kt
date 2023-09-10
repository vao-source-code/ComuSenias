package com.example.comusenias.domain.repositories

import android.content.Context
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner

interface CustomCameraRepository {
    suspend fun captureAndSaveImage(context: Context)
    suspend fun  showCameraPreview(previewView:PreviewView, lifecycleOwner: LifecycleOwner)
}