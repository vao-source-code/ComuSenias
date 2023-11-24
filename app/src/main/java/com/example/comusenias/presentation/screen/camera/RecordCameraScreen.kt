package com.example.comusenias.presentation.screen.camera

import OverlayView
import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.camera.core.CameraSelector
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.comusenias.constants.PreferencesConstant.PREFERENCE_LEVEL
import com.example.comusenias.core.PreferenceManager
import com.example.comusenias.presentation.component.gameAction.CounterAction
import com.example.comusenias.presentation.view_model.CameraViewModel
import com.example.comusenias.presentation.view_model.LevelViewModel


@Composable
fun RecordCameraScreen(
    levelViewModel: LevelViewModel,
    viewModel: CameraViewModel = hiltViewModel(),
    navController: NavController? = null,
) {

    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }
    preferenceManager.saveInt(PREFERENCE_LEVEL, 2) // capt
    val activity = (context as? Activity)
    val lifecycleOwner = LocalLifecycleOwner.current


    val controller = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(
                CameraController.VIDEO_CAPTURE
            )
        }
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val lifecycleOwner = LocalLifecycleOwner.current

        /*AndroidView(

            factory = {
              //  this.controller = controller
                val previewView = PreviewView(it).apply {
                    this.controller = controller
                    viewModel.showCameraPreview(this, lifecycleOwner)

                }
                previewView

                /*PreviewView(it).apply {
                    this.controller = controller
                    viewModel.showCameraPreview(this, lifecycleOwner)
                    //controller.cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
                    //controller.bindToLifecycle(lifecycleOwner)
                }*/
            },
            modifier = Modifier.fillMaxSize()
        )*/

        // OverlayView should be adjusted accordingly


        CameraPreview(
            controller = controller,
            modifier = Modifier
                .fillMaxSize(), viewModel = viewModel, levelViewModel = levelViewModel
        )

        /*recognitionResults?.result.let {
            it?.forEach {
                Log.d("RecordVideoSign",   it.gestures().toString())

            }
        }*/




        IconButton(
            onClick = {
                // viewModel.recordVideo(controller,lifecycleOwner,navController!!)

                viewModel.recordVideo(navController!!,  lifecycleOwner)
            },
            modifier = Modifier
                .padding(16.dp)
                .size(56.dp)
                .align(Alignment.BottomCenter)
        ) {
            Icon(
                imageVector = Icons.Default.Videocam,
                contentDescription = "Record video"
            )
        }

    }


    CounterAction()

    BackHandler {
        activity?.finish()
    }

    /*DisposableEffect(Unit) {
        viewModel.startObjectDetection()
        onDispose { }
    }*/
}







