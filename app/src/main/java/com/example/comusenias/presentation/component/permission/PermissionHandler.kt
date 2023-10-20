import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult


import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.comusenias.presentation.navigation.AppScreen

@Composable
fun RequestPermissions(
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit
) {
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions.all { it.value }) {
            // Todos los permisos fueron concedidos.
            onPermissionGranted()
        } else {
            // Al menos un permiso fue denegado.
            onPermissionDenied()
        }
    }

    DisposableEffect(Unit) {
        requestPermissionLauncher.launch(
            arrayOf(
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        )
        onDispose { /* Hacer algo cuando se desecha el efecto */ }
    }
}


@Composable
fun PermissionCameraScreen(navController: NavHostController) {
    val context = LocalContext.current
    RequestPermissions(
        onPermissionGranted = {
            navController.navigate(AppScreen.CameraScreen.route)
        },
        onPermissionDenied = {
            Toast.makeText(context, "No has solicitado el permiso de Cámara", Toast.LENGTH_SHORT)
                .show()
        }
    )
}


@Composable
fun RequestPermissionsGallery(
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit
) {

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions.all { it.value }) {
            // Todos los permisos fueron concedidos.
            onPermissionGranted()
        } else {
            // Al menos un permiso fue denegado.
            onPermissionDenied()
        }
    }

    DisposableEffect(Unit) {
        requestPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        )
        onDispose { /* Hacer algo cuando se desecha el efecto */ }
    }
    /*val galleryPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            onPermissionGranted(uri)
        } else {
            onPermissionDenied()
        }
    }*/

    DisposableEffect(Unit) {
        onDispose { /* Hacer algo cuando se desecha el efecto */ }
    }
}

@Composable
fun PermissionGaleryScreen(navController: NavHostController) {
    val context = LocalContext.current
    RequestPermissionsGallery(
        onPermissionGranted = {
            navController.navigate(AppScreen.GalleryScreen.route)
        },
        onPermissionDenied = {
            Toast.makeText(context, "No has solicitado el permiso de Galeria", Toast.LENGTH_SHORT)
                .show()
        }
    )
}


