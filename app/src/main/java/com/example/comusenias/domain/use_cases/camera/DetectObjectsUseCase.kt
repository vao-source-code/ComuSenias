package com.example.comusenias.domain.use_cases.camera

import android.graphics.Bitmap
import android.media.Image
import androidx.camera.core.ImageProxy
import com.example.comusenias.domain.models.ObjectDetectionResult
import com.example.comusenias.domain.repositories.ObjectDetectionRepository
import java.nio.ByteBuffer
import javax.inject.Inject

class DetectObjectsUseCase @Inject constructor(
    private val objectDetectionRepository: ObjectDetectionRepository
) {
    suspend operator fun invoke(imageProxy: ImageProxy): List<ObjectDetectionResult> {
        return objectDetectionRepository.detectObjects(imageProxy)
    }
}