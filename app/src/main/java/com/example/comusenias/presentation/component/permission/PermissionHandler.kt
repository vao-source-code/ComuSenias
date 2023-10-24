import android.Manifest
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
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
            //TODO ver que esto falla en android 33
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
                //TODO ver que esto falla en android 33
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        )
        onDispose { /* Hacer algo cuando se desecha el efecto */ }
    }

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


