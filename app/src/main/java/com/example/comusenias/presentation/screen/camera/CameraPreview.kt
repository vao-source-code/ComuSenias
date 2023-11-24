package com.example.comusenias.presentation.screen.camera

import OverlayView
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.example.comusenias.presentation.view_model.CameraViewModel
import com.example.comusenias.presentation.view_model.LevelViewModel

@OptIn(UnstableApi::class) @Composable
fun CameraPreview(
    controller: LifecycleCameraController,
    modifier: Modifier = Modifier,
    viewModel:CameraViewModel = hiltViewModel(),
    levelViewModel: LevelViewModel
) {
    val recognitionResultsState = viewModel.recognitionResults.collectAsState()
    val recognitionResults = recognitionResultsState.value
    val lifecycleOwner = LocalLifecycleOwner.current


    AndroidView(
        factory = {

            PreviewView(it).apply {
                this.controller = controller
               // controller.cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
                viewModel.showCameraPreview(this,controller,lifecycleOwner)
            }
        },
        modifier = modifier
    )

    OverlayView(
        resultOverlayView = recognitionResults,
        levelViewModel = levelViewModel
    )
    recognitionResults?.result.let {
        it?.forEach {
            Log.d("VideoSign",it.gestures().toString())
        }
    }



    viewModel.startObjectDetection()



}