package com.example.comusenias.domain.models.mediapipe

import com.google.mediapipe.tasks.vision.facelandmarker.FaceLandmarkerResult

data class DetectionFace(
    val result: FaceLandmarkerResult,
    val inputImageHeight: Int,
    val inputImageWidth: Int
)