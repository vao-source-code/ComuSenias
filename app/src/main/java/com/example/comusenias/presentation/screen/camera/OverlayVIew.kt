import androidx.annotation.OptIn
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.example.comusenias.R
import com.example.comusenias.core.PreferenceManager
import com.example.comusenias.domain.models.overlayView.ResultOverlayView
import com.example.comusenias.presentation.activities.MainActivity.Companion.getLevelViewModel
import com.example.comusenias.presentation.ui.theme.LANDMARK_STROKE_WIDTH
import com.example.comusenias.presentation.ui.theme.NONE
import com.example.comusenias.presentation.view_model.LevelViewModel
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarker
import companion.KEY_CATEGORY

var listCategory = mutableListOf<String>()

@OptIn(UnstableApi::class) @Composable
fun OverlayView(
    resultOverlayView: ResultOverlayView?,
    levelViewModel: LevelViewModel
) {
    val context = LocalContext.current


    if (resultOverlayView != null) {
        val density = LocalDensity.current.density
        val imageHeight = resultOverlayView.inputImageHeight.toFloat()
        val imageWidth = resultOverlayView.inputImageWidth.toFloat()
        val scaleFactor = 1f
        val result = resultOverlayView.result

        result.forEach { it ->
            val gestures = it.gestures()
            val firstGesture = gestures.getOrNull(0)
            val category = firstGesture?.get(0)?.categoryName()
                ?: context.getString(R.string.noneResultGesture)
            val landmarksResult = it.landmarks()

            listCategory.add(category)


            val isCorrect = verifyOptionSelected( category, getLevelViewModel)
            val listaDePrueba  = listOf<String>("A","E","I","O","U","Hola","Chau","Permiso","Disculpa","Gracias")

            if (listCategory.contains(getLevelViewModel.subLevelSelected)) {
                getLevelViewModel.onOptionSelected =   getLevelViewModel.subLevelSelected
            }

            Log.d("VideoSign", gestures.toString())



                Canvas(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val linePaint = Paint().apply { strokeWidth = LANDMARK_STROKE_WIDTH }
                    val lineColor = if (!isCorrect) Color.Red else Color.Green

                    for (landmark in landmarksResult) {
                        for (normalizedLandmark in landmark) {
                            val scaledX = normalizedLandmark.x() * size.width * scaleFactor
                            val scaledY = normalizedLandmark.y() * size.height * scaleFactor
                            val xOffset =
                                (size.width - imageWidth * scaleFactor) / 5
                            val yOffset =
                                (size.height - imageHeight * scaleFactor) / 20

                            drawCircle(
                                color = Color.Yellow,
                                center = Offset(scaledX + xOffset, scaledY + yOffset),
                                radius = 4f * density
                            )
                            var allConnectionsDetected = true


                            HandLandmarker.HAND_CONNECTIONS.forEach {
                                val start = landmarksResult[0][it.start()]
                                val end = landmarksResult[0][it.end()]

                                val startX = (start.x() * size.width * scaleFactor) + xOffset
                                val startY = (start.y() * size.height * scaleFactor) + yOffset
                                val endX = (end.x() * size.width * scaleFactor) + xOffset
                                val endY = (end.y() * size.height * scaleFactor) + yOffset

                                if (startX < 0 || startY < 0 || endX < 0 || endY < 0) {
                                    allConnectionsDetected = false
                                } else {
                                    drawLine(
                                        color = lineColor,
                                        strokeWidth = linePaint.strokeWidth,
                                        start = Offset(startX, startY),
                                        end = Offset(endX, endY)
                                    )
                                }
                            }
                            if (!allConnectionsDetected) {
                                drawLine(
                                    color = Color.Red,
                                    strokeWidth = linePaint.strokeWidth,
                                    start = Offset(0f, 0f),
                                    end = Offset(0f, 0f)
                                )
                            }
                        }
                    }
                }
            }
        } else {
            Text(
                text = context.getString(R.string.loadingResults),
                color = Color.Gray
            )
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

object companion{
    const val KEY_CATEGORY="KEY_CATEGORY"
}
