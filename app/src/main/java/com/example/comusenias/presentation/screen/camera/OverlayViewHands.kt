import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import com.example.comusenias.R
import com.example.comusenias.presentation.screen.camera.isAnyGestureOnFace
import com.example.comusenias.presentation.ui.theme.NONE
import com.example.comusenias.presentation.view_model.LevelViewModel
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.facelandmarker.FaceLandmarkerResult
import com.google.mediapipe.tasks.vision.gesturerecognizer.GestureRecognizerResult
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarker
import kotlin.math.max
import kotlin.math.min

private val listCategory = mutableListOf<String>()
@SuppressLint("SuspiciousIndentation")
@Composable
fun OverlayViewHands(
    levelViewModel: LevelViewModel,
    results: List<GestureRecognizerResult>,
    imageWidth: Int,
    imageHeight: Int,
    runningMode: RunningMode = RunningMode.LIVE_STREAM
) {
    val density = LocalDensity.current.density

    val context = LocalContext.current


        results.forEach { gestureRecognizerResult ->

            val gestures = gestureRecognizerResult.gestures()
            val firstGesture = gestures.getOrNull(0)
            val category = firstGesture?.get(0)?.categoryName() ?: context.getString(R.string.noneResultGesture)


            listCategory.add(category)

            val isCorrect = verifyOptionSelected(category, levelViewModel)

            if (listCategory.contains(levelViewModel.subLevelSelected)) {
                levelViewModel.onOptionSelected = levelViewModel.subLevelSelected
            }
            Canvas(modifier = Modifier.fillMaxSize()) {

                    val scaleFactor = when (runningMode) {
                        RunningMode.IMAGE,
                        RunningMode.VIDEO -> {
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


                    val xOffset = (size.width - imageWidth * scaleFactor) / 2
                    val yOffset = (size.height - imageHeight * scaleFactor) / 2

                    val lineColor = if (!isCorrect) Color.Red else Color.Green

                    for (landmark in gestureRecognizerResult.landmarks()) {
                        for (normalizedLandmark in landmark) {
                            val x = normalizedLandmark.x() * imageWidth * scaleFactor + xOffset
                            val y = normalizedLandmark.y() * imageHeight * scaleFactor + yOffset

                            drawCircle(
                                color = Color.Yellow,
                                radius = 2f * density,
                                center = Offset(x, y)
                            )


                            HandLandmarker.HAND_CONNECTIONS.forEach {


                                    /*-------------------------------Mano izquierda------------------------------------------------- */

                                        val startXRight =
                                            gestureRecognizerResult.landmarks()[0][it!!.start()].x() * imageWidth * scaleFactor + xOffset
                                        val startYRight =
                                            gestureRecognizerResult.landmarks()[0][it.start()].y() * imageHeight * scaleFactor + yOffset
                                        val endXRight =
                                            gestureRecognizerResult.landmarks()[0][it.end()].x() * imageWidth * scaleFactor + xOffset
                                        val endYRight =
                                            gestureRecognizerResult.landmarks()[0][it.end()].y() * imageHeight * scaleFactor + yOffset

                                        drawLine(
                                            color = lineColor,
                                            start = Offset(startXRight, startYRight),
                                            end = Offset(endXRight, endYRight),
                                            strokeWidth = 5f * density
                                        )



                                        //  -----------------------------------Mano derecha-------------------------------------------------

                                       /* val startXLeft =
                                            gestureLandmarks[1][it.start()].x() * imageWidth * scaleFactor + xOffset
                                        val startYLeft =
                                            gestureLandmarks[1][it.start()].y() * imageHeight * scaleFactor + yOffset
                                        val endXLeft =
                                            gestureLandmarks[1][it.end()].x() * imageWidth * scaleFactor + xOffset
                                        val endYLeft =
                                            gestureLandmarks[1][it.end()].y() * imageHeight * scaleFactor + yOffset



                                        drawLine(
                                            color = Color.Green,
                                            start = Offset(startXLeft, startYLeft),
                                            end = Offset(endXLeft, endYLeft),
                                            strokeWidth = LANDMARK_STROKE_WIDTH * density
                                        )*/


                                    }
                                    // --------------------------------------------------------------------------------------------//

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
