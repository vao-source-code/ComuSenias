package com.example.comusenias.presentation.screen.camera

import OverlayView
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.comusenias.presentation.view_model.CameraViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.google.mediapipe.tasks.vision.core.RunningMode


@Composable
fun CameraScreen(
    viewModel: CameraViewModel = hiltViewModel(),
    navController: NavController? = null,
    runningMode: RunningMode = RunningMode.LIVE_STREAM // Parámetro opcional con valor por defecto

) {

    val context = LocalContext.current
    val activity = (context as? Activity)
    val lifecycleOwner = LocalLifecycleOwner.current


    // Recolecta los resultados de reconocimiento del ViewModel
    val recognitionResultsState = viewModel.recognitionResults.collectAsState()
    val recognitionResults = recognitionResultsState.value
    val configuration = LocalConfiguration.current


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


        if (recognitionResults != null) {
            OverlayView(
                gestureRecognizerResults = recognitionResults,
                imageHeight = configuration.screenHeightDp,
                imageWidth = configuration.screenWidthDp,
                runningMode = runningMode,
            )

        } else {
            // En este caso, recognitionResults es nulo, puedes mostrar un mensaje de carga o un indicador visual
            Text(
                text = "Cargando resultados...", // Puedes personalizar este mensaje
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                color = Color.Gray // Puedes personalizar el color
            )
        }

    }
    BackHandler {
        activity?.finish()
    }

    DisposableEffect(Unit) {
        viewModel.startObjectDetection()
        onDispose { /* Limpieza si es necesario */ }
    }
}