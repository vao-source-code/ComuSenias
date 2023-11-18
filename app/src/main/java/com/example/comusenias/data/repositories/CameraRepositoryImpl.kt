import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.annotation.OptIn
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.example.comusenias.data.api.ApiService
import com.example.comusenias.data.repositories.GestureRecognizerHelper
import com.example.comusenias.domain.models.ResultOverlayView
import com.example.comusenias.domain.repositories.CameraRepository
import com.example.comusenias.presentation.navigation.AppScreen

import com.google.mediapipe.tasks.vision.core.RunningMode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okio.Utf8
import java.io.File
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.Executors
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CameraRepositoryImpl @Inject constructor(
    private val cameraProvider: ProcessCameraProvider,
    private val cameraSelector: CameraSelector,
    private val preview: Preview,
    private val imageCapture: ImageCapture,
    private val context: Context,
) : CameraRepository, GestureRecognizerHelper.GestureRecognizerListener{


    private val minHandDetectionConfidence = 0.5f
    private val minHandTrackingConfidence = 0.5f
    private val minHandPresenceConfidence = 0.5f
    private val currentDelegate = GestureRecognizerHelper.DELEGATE_CPU
    private val runningMode = RunningMode.LIVE_STREAM

    private val gestureRecognizerHelper = GestureRecognizerHelper(
        minHandDetectionConfidence,
        minHandTrackingConfidence,
        minHandPresenceConfidence,
        currentDelegate,
        runningMode,
        context
    )


    private var imageAnalysis: ImageAnalysis? = null

    private val recognitionResultsMutableFlow = MutableStateFlow<ResultOverlayView?>(null)


    init {
        setupImageAnalysis()
    }

    override suspend fun captureAndSaveImage(navController: NavController) {
        val name = SimpleDateFormat(
            "yyyy-MM-dd-HH-mm-ss-SSS",
            Locale.ENGLISH
        ).format(System.currentTimeMillis())

        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT > 28) {
                put(
                    MediaStore.MediaColumns.RELATIVE_PATH,
                    "Pictures/My-Camera-App-Images"
                )
            }
        }

        val outputOptions = ImageCapture.OutputFileOptions.Builder(
            context.contentResolver,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        ).build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    // Inicia un nuevo contexto de coroutine en el hilo de fondo

                    val savedUri = outputFileResults.savedUri
                    val encodedUrl = URLEncoder.encode(savedUri.toString(), "UTF-8")

                    navController.navigate(AppScreen.GalleryScreen.createRoute(encodedUrl))


                    Log.d("SavedImage" ,"${outputFileResults.savedUri}")

                    Toast.makeText(
                        context,
                        "Saved image: ${outputFileResults.savedUri}",
                        Toast.LENGTH_SHORT
                    ).show()
                }


                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(
                        context,
                        "Some error occurred: ${exception.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }




    override suspend fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner
    ) {
        preview.setSurfaceProvider(previewView.surfaceProvider)
        try {
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                preview,
                imageAnalysis,
                imageCapture
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun stopCameraPreview() {
        try {
            cameraProvider.unbindAll()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun recordVideo(navController: NavController) {
        TODO("Not yet implemented")
    }


    private fun setupImageAnalysis() {
        imageAnalysis = ImageAnalysis.Builder()
            .setTargetAspectRatio(AspectRatio.RATIO_4_3)
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
            .build()
    }

    @OptIn(ExperimentalGetImage::class)
    override fun startObjectDetection(): Flow<ResultOverlayView> {

        imageAnalysis?.setAnalyzer(Executors.newSingleThreadExecutor()) { imageProxy ->
            gestureRecognizerHelper.setListener(this)

            gestureRecognizerHelper.recognizeLiveStream(imageProxy)

            imageProxy.close()

        }
        return recognitionResultsMutableFlow.filterNotNull()

    }

    override fun onError(error: String, errorCode: Int) {
        Log.e("ErrorLandmarks", "Error En Deteccion de Landmarks")
    }

    override fun onResults(resultBundle: GestureRecognizerHelper.ResultBundle) {

        val results = resultBundle.results
        val inputImageHeight = resultBundle.inputImageHeight
        val inputImageWidth = resultBundle.inputImageWidth


        recognitionResultsMutableFlow.value =
            ResultOverlayView(results, inputImageHeight, inputImageWidth)

    }

}
