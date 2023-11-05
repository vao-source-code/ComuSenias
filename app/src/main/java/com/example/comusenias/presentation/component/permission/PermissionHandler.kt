import android.Manifest.permission.CAMERA
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.Intent
import android.net.Uri
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.TIRAMISU
import android.provider.Settings
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.comusenias.R
import com.example.comusenias.presentation.navigation.AppScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestPermissions(
    onPermissionStatus: (Boolean) -> Unit
) {
    var permissionCamera = listOf(CAMERA, WRITE_EXTERNAL_STORAGE)

    if (SDK_INT >= TIRAMISU) {
        permissionCamera = listOf(CAMERA)
    }

    val multilpePermissionState = rememberMultiplePermissionsState(
        permissionCamera
    ) { permission ->
        onPermissionStatus(permission.all { it.value })
    }

    LaunchedEffect(true) {
        multilpePermissionState.launchMultiplePermissionRequest()
    }
}

@Composable
fun PermissionCameraScreen(
    navController: NavHostController
) {
    val context = LocalContext.current
    var permissionStatus by remember {
         mutableStateOf(false)
    }

    RequestPermissions {
        permissionStatus
    }


    if (!permissionStatus) {
        Box(modifier = Modifier.fillMaxSize()) {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", context.packageName, null)
            intent.data = uri
            context.startActivity(intent)
        }
    } else {
        navController.navigate(AppScreen.InfoMakeSignScreen.route)
    }
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
                WRITE_EXTERNAL_STORAGE,
                READ_EXTERNAL_STORAGE
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
                LENGTH_SHORT
            )
                .show()
        }
    )
}