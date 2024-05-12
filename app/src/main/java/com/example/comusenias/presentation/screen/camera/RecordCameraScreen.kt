package com.example.comusenias.presentation.screen.camera


import OverlayViewFace
import OverlayViewHands
import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.comusenias.presentation.component.gameAction.CounterAction
import com.example.comusenias.presentation.view_model.CameraViewModel
import com.example.comusenias.presentation.view_model.LevelViewModel
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

    val recognitionHandsResultsState = viewModel.recognitionHandsResults.collectAsState()
    val recognitionHandsResults = recognitionHandsResultsState.value

    val recognitionPoseResultsState = viewModel.recognitionPoseResults.collectAsState()
    val recognitionPoseResults = recognitionPoseResultsState.value

    val recognitionFaceResultsState = viewModel.recognitionFaceResults.collectAsState()
    val recognitionFaceResults = recognitionFaceResultsState.value


    val showCameraPreview = remember { mutableStateOf(true) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (showCameraPreview.value) {
            AndroidView(
                factory = {
                    val previewView = PreviewView(it)
                    viewModel.showCameraPreview(previewView, lifecycleOwner)
                    previewView
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        val gestureResults = recognitionHandsResults?.results
        val poseLandmarkResult = recognitionPoseResults?.results

        /*if (gestureResults != null && poseLandmarkResult != null) {
            val isAnyGestureOnFace = gestureResults.any { gestureResult ->
                isAnyGestureOnFace(gestureResult, poseLandmarkResult)
            }


            Log.d("GestureInsideFace", "¿Hay algún gesto dentro de la cara? $isAnyGestureOnFace")
        }*/


        BackHandler {activity?.finish() }

        CounterAction()

        if(recognitionFaceResults?.result!=null){
            OverlayViewFace(landmarks =recognitionFaceResults.result ,
                imageWidth = recognitionFaceResults.inputImageWidth,
                imageHeight = recognitionFaceResults.inputImageHeight)
        }


        if(recognitionHandsResults?.results!=null) {
            OverlayViewHands(
                levelViewModel = levelViewModel,
                gestureResults = gestureResults!!,
                imageWidth = recognitionHandsResults.inputImageWidth,
                imageHeight = recognitionHandsResults.inputImageHeight
            )
        }

        if(recognitionPoseResults?.results!=null){
            OverlayViewPose(
                poseLandmarkerResults = recognitionPoseResults.results,
                imageWidth = recognitionPoseResults.inputImageWidth,
                imageHeight = recognitionPoseResults.inputImageHeight
            )
        }

        DisposableEffect(Unit) {
            viewModel.startDetection()

            viewModel.resultFace()
            viewModel.resultHands()
            viewModel.resultPose()

            val cameraCapturingJob =   lifecycleOwner.lifecycleScope.launch {
                viewModel.recordVideo(navController = navController!!)
                delay(6000)
                viewModel.stopVideo()
            }

            onDispose { cameraCapturingJob.cancel() }
        }
    }
}







