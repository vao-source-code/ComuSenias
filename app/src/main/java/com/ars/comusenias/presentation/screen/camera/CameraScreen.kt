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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.ars.comusenias.constants.PreferencesConstant
import com.ars.comusenias.core.PreferenceManager
import com.ars.comusenias.presentation.component.gameAction.CounterAction
import com.ars.comusenias.presentation.view_model.CameraViewModel
import com.ars.comusenias.presentation.view_model.LevelViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
    val recognitionResultsState = viewModel.recognitionResults.collectAsState()
    val recognitionResults = recognitionResultsState.value


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AndroidView(
            factory = {
                val previewView = PreviewView(it)
                viewModel.showCameraPreview(previewView,lifecycleOwner,true)
                previewView
            },
            modifier = Modifier.fillMaxSize()
        )

        OverlayView(
            resultOverlayView = recognitionResults,
            levelViewModel = levelViewModel
        )

    }

    CounterAction()

    BackHandler {
        activity?.finish()
    }

    DisposableEffect(Unit) {
        viewModel.startDetection()
        val cameraCapturingJob =  lifecycleOwner.lifecycleScope.launch {
            delay(6000)
            viewModel.captureAndSave(navController = navController!!)
        }

        onDispose { cameraCapturingJob.cancel()}
    }
}