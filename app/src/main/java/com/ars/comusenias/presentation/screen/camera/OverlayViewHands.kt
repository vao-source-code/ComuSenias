import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import com.ars.comusenias.R
import com.ars.comusenias.presentation.ui.theme.NONE
import com.ars.comusenias.presentation.view_model.LevelViewModel
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.facelandmarker.FaceLandmarkerResult
import com.google.mediapipe.tasks.vision.gesturerecognizer.GestureRecognizerResult
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarker
import kotlin.math.max

private val listCategory = mutableListOf<String>()

@SuppressLint("SuspiciousIndentation")
@Composable
fun OverlayViewHands(
    levelViewModel: LevelViewModel,
    gestureResults: List<GestureRecognizerResult>,
    imageWidth: Int,
    imageHeight: Int,
    runningMode: RunningMode = RunningMode.LIVE_STREAM
) {
    val density = LocalDensity.current.density

    val context = LocalContext.current


    gestureResults.forEach { gestureRecognizerResult ->

        val gestures = gestureRecognizerResult.gestures()
        val firstGesture = gestures.getOrNull(0)
        val category =
            firstGesture?.get(0)?.categoryName() ?: context.getString(R.string.noneResultGesture)

        listCategory.add(category)

        val isCorrect = verifyOptionSelected(category, levelViewModel)

        if (listCategory.contains(levelViewModel.subLevelSelected)) {
            levelViewModel.onOptionSelected = levelViewModel.subLevelSelected
        }

        Canvas(modifier = Modifier.fillMaxSize()) {
            val scaleFactor = max(
                size.width / imageWidth.toFloat(),
                size.height / imageHeight.toFloat()
            )

            val xOffset = (size.width - imageWidth * scaleFactor) / 2
            val yOffset = (size.height - imageHeight * scaleFactor) / 2

            val lineColor = if (!isCorrect) Color.Red else Color.Green

            for (landmark in gestureRecognizerResult.landmarks()) {
                for (normalizedLandmark in landmark) {
                    val x = normalizedLandmark.x() * imageWidth * scaleFactor + xOffset
                    val y = normalizedLandmark.y() * imageHeight * scaleFactor + yOffset
                }

                HandLandmarker.HAND_CONNECTIONS?.forEach {
                    val startX =
                        landmark[it?.start()!!].x() * imageWidth * scaleFactor + xOffset
                    val startY =
                        landmark[it.start()].y() * imageHeight * scaleFactor + yOffset
                    val endX =
                        landmark[it.end()].x() * imageWidth * scaleFactor + xOffset
                    val endY =
                        landmark[it.end()].y() * imageHeight * scaleFactor + yOffset

                    drawLine(
                        color = lineColor,
                        start = Offset(startX, startY),
                        end = Offset(endX, endY),
                        strokeWidth = 2f * density
                    )
                }
            }
        }

    }


}

@Composable
private fun verifyOptionSelected(
    category: String,
    levelViewModel: LevelViewModel
): Boolean {
    return category.isNotEmpty() && category != NONE && category.equals(
        levelViewModel.subLevelSelected,
        ignoreCase = true
    )
}

fun isAnyGestureOnFace(
    gestureResult: GestureRecognizerResult,
    faceResult: FaceLandmarkerResult
): Boolean {
    // Verificar si la cara no fue detectada
    if (faceResult.faceLandmarks().isEmpty()) {
        return false
    }

    // Obtener las landmarks de la cara
    val faceLandmarks = faceResult.faceLandmarks()[0]

    // Definir las regiones de interés en la cara
    val faceLeft = faceLandmarks.minByOrNull { it.x() }?.x() ?: return false
    val faceRight = faceLandmarks.maxByOrNull { it.x() }?.x() ?: return false
    val faceTop = faceLandmarks.minByOrNull { it.y() }?.y() ?: return false
    val faceBottom = faceLandmarks.maxByOrNull { it.y() }?.y() ?: return false

    // Verificar si alguna parte del gesto está dentro del rectángulo delimitador del rostro
    for (handLandmarks in gestureResult.landmarks()) {
        for (landmark in handLandmarks) {
            val handX = landmark.x()
            val handY = landmark.y()

            // Verificar si la parte del gesto está dentro del rostro
            if (handX >= faceLeft && handX <= faceRight && handY >= faceTop && handY <= faceBottom) {
                return true
            }
        }
    }

    // Si ninguna parte del gesto está dentro del rostro
    return false
}


