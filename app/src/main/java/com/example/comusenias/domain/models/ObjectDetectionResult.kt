package com.example.comusenias.domain.models

import android.graphics.RectF


data class ObjectDetectionResult(
    val label: String,
    val confidence: Float,
    val boundingBox: RectF
)