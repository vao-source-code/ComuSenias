package com.example.comusenias.presentation.screen.camera


import android.Manifest
import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.view.PreviewView
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.comusenias.presentation.view_model.CameraViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.comusenias.R
import com.example.comusenias.presentation.component.defaults.ButtonDefault
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.view_model.ObjectDectectionViewModel


@Composable
fun CameraScreen(
    viewModel: CameraViewModel = hiltViewModel(),
    objectDetectionViewModel:ObjectDectectionViewModel = hiltViewModel(), // Agrega este parámetro
    navController: NavController? = null
) {

    val density = LocalDensity.current.density


    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val activity = (LocalContext.current as? Activity)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Comprueba si todos los permisos necesarios están concedidos.
            Box(
                modifier = Modifier
                    .height(screenHeight * 0.85f)
                    .width(screenWidth)
            ) {
                AndroidView(
                    factory = {
                        val previewView = PreviewView(it)

                        viewModel.showCameraPreview(previewView, lifecycleOwner)

                        previewView
                    },
                    modifier = Modifier
                        .height(screenHeight * 0.85f)
                        .width(screenWidth)
                )
            }
        Box(
            modifier = Modifier
                .height(screenHeight * 0.15f),
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = {
                viewModel.captureAndSave(context)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_camera_alt_24),
                    contentDescription = "",
                    modifier = Modifier.size(45.dp),
                    tint = Color.Black
                )
            }
        }
    }



}







