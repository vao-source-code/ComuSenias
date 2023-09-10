import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.navigation.AppScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestCameraPermission(
    activity: ComponentActivity, // Agrega el parámetro activity
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit
) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            onPermissionGranted()
        } else {
            onPermissionDenied()
        }
    }

    val hasCameraPermission = remember {
        ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.CAMERA
        ) == android.content.pm.PackageManager.PERMISSION_GRANTED
    }

    if (!hasCameraPermission) {
        SideEffect {
            launcher.launch(android.Manifest.permission.CAMERA)
        }
    } else {
        onPermissionGranted()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermissionCameraScreen(navController: NavHostController) {
    val context = LocalContext.current
    val activity = LocalContext.current as ComponentActivity // Obtén el ComponentActivity
    RequestCameraPermission(
        activity = activity, // Pasa el ComponentActivity
        onPermissionGranted = {
            navController.navigate(AppScreen.CameraScreen.route)
        },
        onPermissionDenied = {
            Toast.makeText(context, "No has solicitado el permiso de Cámara", Toast.LENGTH_SHORT).show()
        }
    )
}
