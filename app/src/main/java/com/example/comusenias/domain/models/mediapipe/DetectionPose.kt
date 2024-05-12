package com.example.comusenias.domain.models.mediapipe

import com.google.mediapipe.tasks.vision.poselandmarker.PoseLandmarkerResult

data class DetectionPose(
    val results: List<PoseLandmarkerResult>,
    val inputImageHeight: Int,
    val inputImageWidth: Int,
)