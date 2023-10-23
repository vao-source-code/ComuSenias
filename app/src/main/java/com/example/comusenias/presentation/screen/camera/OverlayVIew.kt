import android.content.Context
import android.media.MediaPlayer
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.R
import com.example.comusenias.domain.models.ResultOverlayView
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.gesturerecognizer.GestureRecognizerResult
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarker
import kotlin.math.max
import kotlin.math.min

@Composable
fun OverlayView(
    resultOverlayView: ResultOverlayView?,
) {

    var score = remember { mutableStateOf<Int>(0) }
    var timeLeft = remember { mutableStateOf<Int>(60) } // tiempo inicial en segundos


    if (resultOverlayView != null) {
        val density = LocalDensity.current.density
        val imageHeight = resultOverlayView.inputImageHeight.toFloat()
        val imageWidth = resultOverlayView.inputImageWidth.toFloat()
        val scaleFactor = 1f


        val result = resultOverlayView.result

        result.forEach {


            val gestures = it.gestures()
            val firstGesture = gestures.getOrNull(0)
            val category = firstGesture?.get(0)?.categoryName() ?: "none"
            val landmarksResult = it.landmarks()


            val isCorrect = category != "none" && category.isNotEmpty()


            PutTextCategory(text = category, isCorrect)

            Canvas(
                modifier = Modifier.fillMaxSize()
            ) {

                /*val scaleFactor = when (runningMode) {
                    RunningMode.IMAGE, RunningMode.VIDEO -> {
                        min(size.width / imageWidth.toFloat(), size.height / imageHeight.toFloat())
                    }

                    RunningMode.LIVE_STREAM -> {
                        max(size.width / imageWidth.toFloat(), size.height / imageHeight.toFloat())
                    }
                }*/

                val linePaint = Paint().apply {
                    strokeWidth = LANDMARK_STROKE_WIDTH
                }

                val pointPaint = Paint().apply {
                    strokeWidth = LANDMARK_STROKE_WIDTH
                }

                val lineColor = if (category == "none" || category == "") {
                    Color.Red // Si la categoría es "None", establece el color en rojo
                } else {
                    Color.Green // Si la categoría no es "None", establece el color en verde
                }





                for (landmark in landmarksResult) {
                    for (normalizedLandmark in landmark) {


                        val scaledX = normalizedLandmark.x() * size.width * scaleFactor
                        val scaledY = normalizedLandmark.y() * size.height * scaleFactor

                        // Ajuste adicional para posicionar los puntos de las manos
                        val xOffset =
                            (size.width - imageWidth * scaleFactor) / 5  // Ajusta el valor de 20 según sea necesario
                        val yOffset =
                            (size.height - imageHeight * scaleFactor) / 20  // Ajusta el valor de 20 según sea necesario

                        drawCircle(
                            color = Color.Yellow,
                            center = Offset(scaledX + xOffset, scaledY + yOffset),
                            radius = 4f * density
                        )


                        var allConnectionsDetected = true


                        HandLandmarker.HAND_CONNECTIONS.forEach {
                            val start = landmarksResult[0].get(it!!.start())
                            val end = landmarksResult[0].get(it.end())

                            val startX = (start.x() * size.width * scaleFactor) + xOffset
                            val startY = (start.y() * size.height * scaleFactor) + yOffset
                            val endX = (end.x() * size.width * scaleFactor) + xOffset
                            val endY = (end.y() * size.height * scaleFactor) + yOffset

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

    } else {
        Text(
            text = "Cargando resultados...", // Personaliza este mensaje según sea necesario
            color = Color.Gray // Personaliza el color según sea necesario
        )
    }


}

@Composable
fun PutTextCategory(text: String, isCorrect: Boolean) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        androidx.compose.material3.Button(
            onClick = { },
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isCorrect) Color.Green else Color.Red,
                contentColor = Color.White
            )
        ) {

            if(isCorrect && text != "none") {
               // PlaySound(LocalContext.current, text) // Este metodo se usa para reproducir
                Text(text = "Letra:${text}", fontSize = 20.sp)
            }
            else{
                Text(text = "Incorrecto", fontSize = 20.sp)
            }
        }



    }
}

private const val LANDMARK_STROKE_WIDTH = 10f // Puedes ajustar este valor según tus necesidades

/*@Composable
fun PlaySound(context: Context, text: String ) {
    val mediaPlayer = remember {
        MediaPlayer()
    }

    mediaPlayer.reset()
    val assetFileDescriptor = context.resources.openRawResourceFd(getSoundResourceId(text))
    mediaPlayer.setDataSource(
        assetFileDescriptor.fileDescriptor,
        assetFileDescriptor.startOffset,
        assetFileDescriptor.length
    )
    mediaPlayer.prepare()
    val playbackParams = mediaPlayer.playbackParams
    playbackParams.speed = 0.5f // Reducir la velocidad a la mitad
    mediaPlayer.playbackParams = playbackParams
    mediaPlayer.start()
}*/

/*private fun getSoundResourceId(text: String): Int {
    return when (text) {
        "A" -> R.raw.a
        "E" -> R.raw.e
        "I" -> R.raw.i
        "O" -> R.raw.o
        "U" -> R.raw.u
         // Este es el sonido predeterminado en caso de que no haya un sonido específico para la letra detectada
        else -> {0}
    }
}*/