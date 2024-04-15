package com.ars.comusenias.domain.models.overlayView

import com.google.mediapipe.tasks.vision.gesturerecognizer.GestureRecognizerResult

data class ResultOverlayView(
    var result: List<GestureRecognizerResult>,
    val inputImageHeight: Int,
    val inputImageWidth: Int
)
