import android.Manifest
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.comusenias.R
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
            onPermissionGranted()
        } else {
            onPermissionDenied()
        }
    }
    DisposableEffect(Unit) {
        requestPermissionLauncher.launch(
            //TODO ver que esto falla en android 33
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        )
        onDispose { }
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
            Toast.makeText(
                context,
                context.getString(R.string.permissionCameraText),
                Toast.LENGTH_SHORT
            )
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
            onPermissionGranted()
        } else {
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
        onDispose { }
    }

    DisposableEffect(Unit) {
        onDispose { }
    }
}

@Composable
fun PermissionGalleryScreen(navController: NavHostController) {
    val context = LocalContext.current
    RequestPermissionsGallery(
        onPermissionGranted = {
            navController.navigate(AppScreen.GalleryScreen.route)
        },
        onPermissionDenied = {
            Toast.makeText(
                context,
                context.getString(R.string.permissionGalleryText),
                Toast.LENGTH_SHORT
            )
                .show()
        }
    )
}