import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.facelandmarker.FaceLandmarker.FACE_LANDMARKS_CONNECTORS
import com.google.mediapipe.tasks.vision.facelandmarker.FaceLandmarkerResult
import kotlin.math.max
import kotlin.math.min

@Composable
fun OverlayViewFace(
    landmarks: FaceLandmarkerResult?,
    imageWidth: Int,
    imageHeight: Int,
    runningMode: RunningMode = RunningMode.LIVE_STREAM,
) {

    Canvas(modifier = Modifier.fillMaxSize()) {
        val scaleFactor = when (runningMode) {
            RunningMode.IMAGE, RunningMode.VIDEO -> {
                min(
                    size.width / imageWidth.toFloat(),
                    size.height / imageHeight.toFloat()
                )
            }

            RunningMode.LIVE_STREAM -> {
                max(
                    size.width / imageWidth.toFloat(),
                    size.height / imageHeight.toFloat()
                )
            }
        }

        landmarks?.let { landmark ->
            val xOffset = (size.width - imageWidth * scaleFactor) / 2
            val yOffset = (size.height - imageHeight * scaleFactor) / 2

            landmark.faceLandmarks().forEach { landmarksList ->
                landmarksList.forEach { landmark ->
                    val x = (landmark.x() * imageWidth * scaleFactor) + xOffset
                    val y = (landmark.y() * imageHeight * scaleFactor) + yOffset

                    drawCircle(
                        color = Color.Yellow,
                        radius = 1f * density,
                        center = Offset(x, y)
                    )
                }
            }

            FACE_LANDMARKS_CONNECTORS?.forEach {
                val startX =
                    (landmark.faceLandmarks()[0][it.start()].x() * imageWidth * scaleFactor) + xOffset
                val startY =
                    (landmark.faceLandmarks()[0][it.start()].y() * imageHeight * scaleFactor) + yOffset
                val endX =
                    (landmark.faceLandmarks()[0][it.end()].x() * imageWidth * scaleFactor) + xOffset
                val endY =
                    (landmark.faceLandmarks()[0][it.end()].y() * imageHeight * scaleFactor) + yOffset

                drawLine(
                    color = Color.Green,
                    start = Offset(startX, startY),
                    end = Offset(endX, endY),
                    strokeWidth = 1f * density
                )


            }
        }
    }
}


