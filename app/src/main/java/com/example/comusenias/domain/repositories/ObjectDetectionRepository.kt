package com.example.comusenias.domain.repositories

import android.graphics.Bitmap
import android.media.Image
import androidx.camera.core.ImageProxy
import com.example.comusenias.domain.models.ObjectDetectionResult
import java.nio.ByteBuffer

interface ObjectDetectionRepository {
    suspend fun detectObjects(imageProxy: ImageProxy): List<ObjectDetectionResult>
}