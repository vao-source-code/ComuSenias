package com.example.comusenias.domain.models

import android.graphics.RectF


data class ObjectDetectionResult(
    var label: String,
    var confidence: Float,
    var boundingBox: RectF
)