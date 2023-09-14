
import android.Manifest
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
import androidx.core.content.ContextCompat
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
            Toast.makeText(context, "No has solicitado el permiso de Cámara", Toast.LENGTH_SHORT).show()
        }
    )
}
