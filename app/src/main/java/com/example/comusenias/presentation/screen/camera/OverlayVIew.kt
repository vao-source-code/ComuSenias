import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.gesturerecognizer.GestureRecognizerResult
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarker
import kotlin.math.max
import kotlin.math.min

@Composable
fun OverlayView(
    gestureRecognizerResults: List<GestureRecognizerResult>,
    imageHeight: Int,
    imageWidth: Int,
    runningMode: RunningMode = RunningMode.IMAGE
) {
    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        for (result in gestureRecognizerResults) {
            val gestures = result.gestures()
            val firstGesture = gestures.getOrNull(0)
            val category = firstGesture?.get(0)?.categoryName() ?: "none"

            val scaleFactor = when (runningMode) {
                RunningMode.IMAGE, RunningMode.VIDEO -> {
                    min(size.width / imageWidth.toFloat(), size.height / imageHeight.toFloat())
                }
                RunningMode.LIVE_STREAM -> {
                    max(size.width / imageWidth.toFloat(), size.height / imageHeight.toFloat())
                }
            }

            val linePaint = Paint().apply {
                strokeWidth = LANDMARK_STROKE_WIDTH
            }

            val pointPaint = Paint().apply {
                strokeWidth = LANDMARK_STROKE_WIDTH
            }

            val lineColor = if (category=="none" || category=="") {
                Color.Red // Si la categoría es "None", establece el color en rojo
            } else {
                Color.Green // Si la categoría no es "None", establece el color en verde
            }

            for (landmark in result.landmarks()) {
                for (normalizedLandmark in landmark) {
                    val correctedX = (normalizedLandmark.x() * imageWidth * scaleFactor)
                    val correctedY = (normalizedLandmark.y() * imageHeight * scaleFactor)

                    drawCircle(
                        color = pointPaint.color,
                        radius = 5f,
                        center = Offset(correctedX, correctedY)
                    )
                }

                var allConnectionsDetected = true

                HandLandmarker.HAND_CONNECTIONS.forEach {
                    val start = result.landmarks()[0].get(it!!.start())
                    val end = result.landmarks()[0].get(it.end())

                    val startX = (start.x() * imageWidth * scaleFactor)
                    val startY = (start.y() * imageHeight * scaleFactor)
                    val endX = (end.x() * imageWidth * scaleFactor)
                    val endY = (end.y() * imageHeight * scaleFactor)

                    if (startX < 0 || startY < 0 || endX < 0 || endY < 0) {
                        allConnectionsDetected = false
                    } else {
                        drawLine(
                            color = lineColor, // Usa el color determinado por la categoría
                            strokeWidth = linePaint.strokeWidth,
                            start = Offset(startX, startY),
                            end = Offset(endX, endY)
                        )
                    }
                }

                if (!allConnectionsDetected) {
                    drawLine(
                        color = Color.Red, // Si no se detectaron todas las conexiones, dibuja la línea en rojo
                        strokeWidth = linePaint.strokeWidth,
                        start = Offset(0f, 0f),
                        end = Offset(0f, 0f)
                    )
                }
            }
        }
    }
}

private const val LANDMARK_STROKE_WIDTH = 8f
