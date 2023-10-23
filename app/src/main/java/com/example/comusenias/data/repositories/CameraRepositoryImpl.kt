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
import com.example.comusenias.core.di.RetrofitModule
import com.example.comusenias.core.di.SPManager
import com.example.comusenias.data.api.ApiService
import com.example.comusenias.data.repositories.GestureRecognizerHelper
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.ResultOverlayView
import com.example.comusenias.domain.repositories.CameraRepository
import com.google.mediapipe.tasks.vision.core.RunningMode
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.Executors
import javax.inject.Inject

class CameraRepositoryImpl @Inject constructor(
    private val cameraProvider: ProcessCameraProvider,
    private val cameraSelector: CameraSelector,
    private val preview: Preview,
    private val imageCapture: ImageCapture,
    private val context: Context,
) : CameraRepository, GestureRecognizerHelper.GestureRecognizerListener {


    val minHandDetectionConfidence = 0.5f // Cambia esto a la confianza mínima deseada
    val minHandTrackingConfidence = 0.5f // Cambia esto a la confianza mínima deseada
    val minHandPresenceConfidence = 0.5f // Cambia esto a la confianza mínima deseada
    val currentDelegate =
        GestureRecognizerHelper.DELEGATE_CPU // Cambia esto según tu preferencia de delegado
    val runningMode = RunningMode.LIVE_STREAM // Cambia esto según tu modo de funcionamiento deseado

    val gestureRecognizerHelper = GestureRecognizerHelper(
        minHandDetectionConfidence,
        minHandTrackingConfidence,
        minHandPresenceConfidence,
        currentDelegate,
        runningMode,
        context
    )

    private var imageAnalysis: ImageAnalysis? = null


    // Create a MutableStateFlow to emit recognition results
    private val recognitionResultsMutableFlow = MutableStateFlow<ResultOverlayView?>(null)

    private lateinit var spManager: SPManager

    init {
        setupImageAnalysis()
        gestureRecognizerHelper.setListener(this)
        spManager = SPManager(context)
    }


    override suspend fun captureAndSaveImage(context: Context): Response<Boolean> {
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

        val capture = object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                Toast.makeText(
                    context,
                    "Saved image: ${outputFileResults.savedUri}",
                    //TODO armar post
                    Toast.LENGTH_SHORT
                ).show()

                val savedUri = outputFileResults.savedUri
                val uriString = savedUri?.path
                val encodedUrl = URLEncoder.encode(savedUri.toString(), "UTF-8")

                GlobalScope.launch {
                    sendImageToServer(outputFileResults.savedUri!!)
                }
            }

            override fun onError(exception: ImageCaptureException) {
                Toast.makeText(
                    context,
                    "Some error occurred: ${exception.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(context),
            capture
        )
        return Response.Success(true)
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

            gestureRecognizerHelper.recognizeLiveStream(imageProxy)

            // Cierra el ImageProxy
            imageProxy.close()

        }
        return recognitionResultsMutableFlow.filterNotNull()

    }

    override suspend fun sendImageToServer(imageUri: Uri) {

        val savedUri = imageUri
        val uriString = savedUri?.path
        val encodedUrl = URLEncoder.encode(savedUri.toString(), "UTF-8")

        val file = File(savedUri.path.toString())
        val requestFile = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
        val retrofit = RetrofitModule.provideApiService()
        val service = retrofit!!.create(ApiService::class.java)
        val call = service.uploadFile(body)


    }

    override fun onError(error: String, errorCode: Int) {
        Log.e("ErrorLandmarks", "Error En Deteccion de Landmarks")
    }

    override fun onResults(resultBundle: GestureRecognizerHelper.ResultBundle) {

        // Aquí obtienes los resultados del GestureRecognizer
        val results = resultBundle.results
        val inputImageHeight = resultBundle.inputImageHeight
        val inputImageWidth = resultBundle.inputImageWidth

        // Agregar el nuevo resultado a la lista existente
        /*val currentResults = recognitionResultsMutableFlow.value
        val updatedResults = currentResults + ResultOverlayView(results, inputImageHeight, inputImageWidth)
        recognitionResultsMutableFlow.value = updatedResults*/

        recognitionResultsMutableFlow.value =
            ResultOverlayView(results, inputImageHeight, inputImageWidth)

    }


}