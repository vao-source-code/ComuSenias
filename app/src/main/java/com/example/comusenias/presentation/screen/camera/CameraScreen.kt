package com.example.comusenias.presentation.screen.camera


import OverlayViewHands
import android.app.Activity
import android.util.Log
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
import com.google.mediapipe.tasks.vision.facelandmarker.FaceLandmarkerResult
import com.google.mediapipe.tasks.vision.gesturerecognizer.GestureRecognizerResult
import com.google.mediapipe.tasks.vision.poselandmarker.PoseLandmarkerResult
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



        /* if (gestureResults != null && poseLandmarkResult != null) {
             val isAnyGestureOnFace = gestureResults.any { gestureResult ->
                 isAnyGestureOnFace(gestureResult, poseLandmarkResult)
             }


             Log.d("GestureInsideFace", "¿Hay algún gesto dentro de la cara? $isAnyGestureOnFace")
         }*/

       /* if(recognitionFaceResults?.result!=null){
            OverlayViewFace(
                landmarks = recognitionFaceResults.result,
                imageWidth = recognitionFaceResults.inputImageWidth,
                imageHeight = recognitionFaceResults.inputImageHeight)
        }*/


       /* if(recognitionPoseResults?.results!=null){
            OverlayViewPose(
                poseLandmarkerResults = recognitionPoseResults.results,
                imageWidth = recognitionPoseResults.inputImageWidth,
                imageHeight = recognitionPoseResults.inputImageHeight
            )
        }*/


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
        //viewModel.resultFace()
       // viewModel.resultPose()
        onDispose { }
    }

}

fun isAnyGestureOnFace(
    gestureResult: GestureRecognizerResult,
    poseLandmarkerResult: PoseLandmarkerResult
): Boolean {
    // Verificar si la pose no fue detectada
    if (poseLandmarkerResult.landmarks().isEmpty()) {
        return false
    }

    // Obtener las landmarks de la pose
    val poseLandmarks = poseLandmarkerResult.landmarks()[0]

    // Definir las regiones de interés en la cara
    val faceLeft = poseLandmarks.minByOrNull { it.x() }?.x() ?: return false
    val faceRight = poseLandmarks.maxByOrNull { it.x() }?.x() ?: return false
    val faceTop = poseLandmarks.minByOrNull { it.y() }?.y() ?: return false
    val faceBottom = poseLandmarks.maxByOrNull { it.y() }?.y() ?: return false

    // Verificar si alguna parte del gesto está dentro del rectángulo delimitador del rostro
    for (handLandmarks in gestureResult.landmarks()) {
        for (landmark in handLandmarks) {
            val handX = landmark.x()
            val handY = landmark.y()

            // Verificar si la parte del gesto está dentro del rostro
            if (handX >= faceLeft && handX <= faceRight && handY >= faceTop && handY <= faceBottom) {
                return true
            }
        }
    }

    // Si ninguna parte del gesto está dentro del rostro
    return false
}


