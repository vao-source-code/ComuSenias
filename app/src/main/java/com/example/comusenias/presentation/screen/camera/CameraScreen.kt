package com.example.comusenias.presentation.screen.camera

import OverlayView
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.comusenias.presentation.view_model.CameraViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.comusenias.R
import com.example.comusenias.presentation.screen.gallery.PreferenceManager


@Composable
fun CameraScreen(
    viewModel: CameraViewModel = hiltViewModel(),
    navController: NavController? = null,
) {

    val context = LocalContext.current
    val activity = (context as? Activity)
    val lifecycleOwner = LocalLifecycleOwner.current




    val preferenceManager = remember { PreferenceManager(context) }

    val isChecked by remember { mutableStateOf(preferenceManager.getBoolean("key_boolean", false)) }



    // Recolecta los resultados de reconocimiento del ViewModel
    val recognitionResultsState = viewModel.recognitionResults.collectAsState()
    val recognitionResults = recognitionResultsState.value


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


        if(!isChecked){
            OverlayView(resultOverlayView = recognitionResults)
           /* Button(
                onClick = {
                    viewModel.recordVideo(navController!!)
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
                    .size(56.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_camera_24),
                    contentDescription = "Capture",
                    modifier = Modifier.size(15.dp),
                    tint = Color.White
                )

            }*/
        }
        else {
            preferenceManager.removeKey("key_boolean")

            // Botón de captura
            Button(
                onClick = {
                    viewModel.captureAndSave(navController!!)
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
                    .size(56.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_camera_24),
                    contentDescription = "Capture",
                    modifier = Modifier.size(15.dp),
                    tint = Color.White
                )

            }
        }


    }

    BackHandler {
        activity?.finish()
    }

    DisposableEffect(Unit) {
        viewModel.startObjectDetection()
        onDispose { }
    }
}



