package com.ars.comusenias.domain.models.mediapipe

import com.google.mediapipe.tasks.vision.gesturerecognizer.GestureRecognizerResult

data class DetectionHand(
    val results: List<GestureRecognizerResult>,
    val inputImageHeight: Int,
    val inputImageWidth: Int,
)
