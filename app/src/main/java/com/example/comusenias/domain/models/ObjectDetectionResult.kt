package com.example.comusenias.domain.models

import android.graphics.Rect


data class ObjectDetectionResult(
    val label:String,
    val confidence:Float,
    val boundingBox: Rect
)