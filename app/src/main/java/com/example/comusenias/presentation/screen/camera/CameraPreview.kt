package com.example.comusenias.presentation.screen.camera

import OverlayView
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
    viewModel:CameraViewModel = hiltViewModel(),
    levelViewModel: LevelViewModel
) {
    val recognitionResultsState = viewModel.recognitionResults.collectAsState()
    val recognitionResults = recognitionResultsState.value
    val lifecycleOwner = LocalLifecycleOwner.current


    AndroidView(
        factory = {
            val previewView = PreviewView(it)
            viewModel.showCameraPreview(previewView,lifecycleOwner)
            previewView
        },
        modifier = Modifier.fillMaxSize()
    )

    OverlayView(
        resultOverlayView = recognitionResults,
        levelViewModel = levelViewModel
    )




    DisposableEffect(Unit) {
        viewModel.startObjectDetection()
        onDispose { }
    }


}