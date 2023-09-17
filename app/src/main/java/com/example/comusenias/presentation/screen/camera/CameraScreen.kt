package com.example.comusenias.presentation.screen.camera


import android.app.Activity
import android.graphics.Paint
import androidx.activity.compose.BackHandler
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.comusenias.presentation.view_model.CameraViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController


@Composable
fun CameraScreen(
    viewModel: CameraViewModel = hiltViewModel(),
    navController: NavController? = null
) {
    val context = LocalContext.current
    val activity = (context as? Activity)
    val lifecycleOwner = LocalLifecycleOwner.current
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val detectionResultsState = viewModel.objectDetectionResults.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AndroidView(
            factory = {
                val previewView = PreviewView(it)
                viewModel.showCameraPreview(previewView, lifecycleOwner)
                previewView
            },
            modifier = Modifier.fillMaxSize()
        )

        // Dibuja cuadrados sin relleno alrededor de los objetos detectados
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawWithContent {
                    for (result in detectionResultsState.value) {
                        val left = result.boundingBox.left * screenWidth.toPx()
                        val top = result.boundingBox.top * screenHeight.toPx()
                        val right = result.boundingBox.right * screenWidth.toPx()
                        val bottom = result.boundingBox.bottom * screenHeight.toPx()
                        val strokeWidth = 5.dp.toPx() // Ajusta el grosor del trazo

                        // Dibuja un cuadrado sin relleno alrededor del objeto detectado
                        drawRect(
                            color = Color.Red,
                            topLeft = Offset(left, top),
                            size = Size(right - left, bottom - top),
                            style = Stroke(strokeWidth)
                        )

                        // Dibuja un texto con información adicional
                        val text = "Label: ${result.label}, Confidence: ${result.confidence}"
                        val textSize = 16.sp.toPx()
                        val textPaint = Paint().apply {
                            color = Color.Red.toArgb()
                        }
                        drawContext.canvas.nativeCanvas.drawText(
                            text,
                            left,
                            top - textSize,
                            textPaint
                        )
                    }
                }
        )
    }
    BackHandler {
        activity?.finish()
    }

    DisposableEffect(Unit) {
        viewModel.startObjectDetection()
        onDispose { /* Limpieza si es necesario */ }
    }
}




