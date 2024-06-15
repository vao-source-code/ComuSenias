package com.example.comusenias.presentation.screen.camera


import OverlayViewFace
import OverlayViewHands
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.comusenias.constants.PreferencesConstant
import com.example.comusenias.core.PreferenceManager
import com.example.comusenias.presentation.component.gameAction.CounterAction
import com.example.comusenias.presentation.view_model.CameraViewModel
import com.example.comusenias.presentation.view_model.LevelViewModel
import kotlinx.coroutines.delay

@Composable
fun CameraScreen(
    levelViewModel: LevelViewModel,
    viewModel: CameraViewModel = hiltViewModel(),
    navController: NavController? = null,
) {
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }
    preferenceManager.saveInt(PreferencesConstant.PREFERENCE_LEVEL, 1) // capturar el level de mejor manera

    val activity = (context as? Activity)
    val lifecycleOwner = LocalLifecycleOwner.current

    val recognitionFaceResultsState = viewModel.recognitionFaceResults.collectAsState()
    val recognitionFaceResults = recognitionFaceResultsState.value


    val recognitionHandsResultsState = viewModel.recognitionHandsResults.collectAsState()
    val recognitionHandsResults = recognitionHandsResultsState.value


    val recognitionPoseResultsState = viewModel.recognitionPoseResults.collectAsState()
    val recognitionPoseResults = recognitionPoseResultsState.value


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AndroidView(
            factory = {
                val previewView = PreviewView(it)
                viewModel.showCameraPreview(previewView,lifecycleOwner)
                previewView
            },
            modifier = Modifier.fillMaxSize()
        )


        val gestureResults = recognitionHandsResults?.results
        val poseLandmarkResult = recognitionPoseResults?.results



        if(recognitionHandsResults?.results!=null) {
            OverlayViewHands(
                levelViewModel = levelViewModel,
                gestureResults = gestureResults!!,
                imageWidth = recognitionHandsResults.inputImageWidth,
                imageHeight = recognitionHandsResults.inputImageHeight
            )
        }




        if(recognitionFaceResults?.result!=null){
            OverlayViewFace(
                landmarks = recognitionFaceResults.result,
                imageWidth = recognitionFaceResults.inputImageWidth,
                imageHeight = recognitionFaceResults.inputImageHeight)
        }


        if(recognitionPoseResults?.results!=null){
            OverlayViewPose(
                poseLandmarkerResults = recognitionPoseResults.results,
                imageWidth = recognitionPoseResults.inputImageWidth,
                imageHeight = recognitionPoseResults.inputImageHeight
            )
        }


        LaunchedEffect(key1 = Unit) {
            delay(5000)
            viewModel.captureAndSave(navController!!)
        }
    }

    CounterAction()

    BackHandler {
        activity?.finish()
    }

    DisposableEffect(Unit) {
        viewModel.startDetection()
        viewModel.resultHands()
        viewModel.resultFace()
        viewModel.resultPose()
        onDispose { }
    }

}




