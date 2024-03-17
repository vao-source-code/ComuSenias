package com.example.comusenias.presentation.screen.camera

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import com.google.mediapipe.tasks.vision.poselandmarker.PoseLandmarker
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.poselandmarker.PoseLandmarkerResult

@Composable
fun OverlayViewPose(
    poseLandmarkerResults: PoseLandmarkerResult?,
    imageHeight: Int,
    imageWidth: Int,
    runningMode: RunningMode = RunningMode.LIVE_STREAM
) {
    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        poseLandmarkerResults?.let { result ->
            val scaleFactor = when (runningMode) {
                RunningMode.IMAGE,
                RunningMode.VIDEO -> {
                    kotlin.math.min(size.width / imageWidth.toFloat(), size.height / imageHeight.toFloat())
                }
                RunningMode.LIVE_STREAM -> {
                    kotlin.math.max(size.width / imageWidth.toFloat(), size.height / imageHeight.toFloat())
                }
            }

            val pointPaint = Paint().apply {
                color = Color.Yellow
                strokeWidth = 1f * density
                style = androidx.compose.ui.graphics.PaintingStyle.Fill
            }

            val linePaint = Paint().apply {
                color = Color.Green // You can replace this with your desired color
                strokeWidth = 1f * density
                style = androidx.compose.ui.graphics.PaintingStyle.Stroke
            }
            val xOffset = (size.width - imageWidth * scaleFactor) / 2
            val yOffset = (size.height - imageHeight * scaleFactor) / 2

            drawIntoCanvas { canvas ->
                result.landmarks().forEach { landmark ->
                    landmark.forEach { normalizedLandmark ->

                        val x = normalizedLandmark.x() * imageWidth * scaleFactor + xOffset
                        val y = normalizedLandmark.y() * imageHeight * scaleFactor + yOffset

                        canvas.drawCircle(Offset(x,y) , LANDMARK_RADIUS * density, pointPaint)
                    }


                    PoseLandmarker.POSE_LANDMARKS.forEach { poseLandmark ->
                        val startLandmark = result.landmarks()[0][poseLandmark.start()]
                        val endLandmark = result.landmarks()[0][poseLandmark.end()]

                        val startX = startLandmark.x() * imageWidth * scaleFactor + xOffset
                        val startY = startLandmark.y() * imageHeight * scaleFactor + yOffset
                        val endX = endLandmark.x() * imageWidth * scaleFactor +xOffset
                        val endY = endLandmark.y() * imageHeight * scaleFactor +yOffset


                        canvas.drawLine(
                            Offset(startX, startY),
                            Offset(endX, endY),
                            linePaint
                        )
                    }
                }
            }
        }
    }
}


private const val LANDMARK_RADIUS = 5f
