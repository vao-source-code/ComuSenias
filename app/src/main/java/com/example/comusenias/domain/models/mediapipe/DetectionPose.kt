package com.example.comusenias.domain.models.recognizerSign

import com.google.mediapipe.tasks.vision.poselandmarker.PoseLandmarkerResult

data class DetectionPose(
    val results: PoseLandmarkerResult,
    val inputImageHeight: Int,
    val inputImageWidth: Int,
)