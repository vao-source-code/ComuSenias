package com.ars.comusenias.presentation.screen.camera

import OverlayView
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.ars.comusenias.presentation.component.gameAction.CounterAction
import com.ars.comusenias.presentation.view_model.CameraViewModel
import com.ars.comusenias.presentation.view_model.LevelViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RecordCameraScreen(
    levelViewModel: LevelViewModel,
    viewModel: CameraViewModel,
    navController: NavController? = null,
) {
    val context = LocalContext.current
    val activity = (context as? Activity)
    val lifecycleOwner = LocalLifecycleOwner.current
    val recognitionResultsState = viewModel.recognitionResults.collectAsState()
    val recognitionResults = recognitionResultsState.value
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AndroidView(
            factory = {
                val previewView = PreviewView(it)
                viewModel.showCameraPreview(previewView,lifecycleOwner,false)
                previewView
            },
            modifier = Modifier.fillMaxSize()
        )
        OverlayView(
            resultOverlayView = recognitionResults,
            levelViewModel = levelViewModel
        )
        CounterAction()

        BackHandler {
            activity?.finish()
        }

        DisposableEffect(Unit) {
            val cameraCapturingJob = lifecycleOwner.lifecycleScope.launch {
                viewModel.recordVideo(navController = navController!!)
                delay(6000)
                viewModel.stopVideo()
            }

            onDispose { cameraCapturingJob.cancel() }
        }

        /*LaunchedEffect(Unit) {
            viewModel.startDetection()
            viewModel.recordVideo(navController = navController!!)
            delay(6000)
            viewModel.stopVideo()
        }*/



    }
}