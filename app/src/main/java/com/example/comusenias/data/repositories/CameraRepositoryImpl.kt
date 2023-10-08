import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ImageFormat
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.YuvImage
import android.media.Image
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.annotation.OptIn
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
import com.example.comusenias.domain.models.ObjectDetectionResult
import com.example.comusenias.domain.repositories.CameraRepository
import com.example.comusenias.ml.Detect
import com.example.comusenias.ml.SignLanguage
import com.example.comusenias.ml.SsdMobilenetV11Metadata11
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.common.FileUtil
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.Executors
import javax.inject.Inject

class CameraRepositoryImpl @Inject constructor(
    private val cameraProvider: ProcessCameraProvider,
    private val cameraSelector: CameraSelector,
    private val preview: Preview,
    private val imageCapture: ImageCapture,
    private val context: Context
) : CameraRepository {

    private val objectDetectionResultFlow =
        MutableStateFlow<List<ObjectDetectionResult>>(emptyList())

    private var imageAnalysis: ImageAnalysis? = null

    var labels:List<String> = FileUtil.loadLabels(context, "labels.txt")
    var colors = listOf<Int>(
        Color.BLUE, Color.GREEN, Color.RED, Color.CYAN, Color.GRAY, Color.BLACK,
        Color.DKGRAY, Color.MAGENTA, Color.YELLOW, Color.RED)
    val paint = Paint()

    init {
        setupImageAnalysis()
    }

    override suspend fun captureAndSaveImage(context: Context) {
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

    private fun setupImageAnalysis() {
        imageAnalysis = ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_BLOCK_PRODUCER)
            .build()
    }
    @OptIn(ExperimentalGetImage::class)
    override suspend fun startObjectDetection(): Flow<List<ObjectDetectionResult>> {
        imageAnalysis?.setAnalyzer(Executors.newSingleThreadExecutor()) { imageProxy ->
            try {
                val model = SsdMobilenetV11Metadata11.newInstance(context)

                // Convierte la Image en un Bitmap
                val bitmap = convertImageToBitmap(imageProxy.image!!)

                // Crea una imagen de TensorFlow Lite a partir del bitmap
                val image = TensorImage.fromBitmap(bitmap)

                // Ejecuta la inferencia del modelo y obtiene los resultados
                val outputs = model.process(image)
                val locations = outputs.locationsAsTensorBuffer
                val classes = outputs.classesAsTensorBuffer
                val scores = outputs.scoresAsTensorBuffer
                val numberOfDetections = outputs.numberOfDetectionsAsTensorBuffer

                val detectionResults = mutableListOf<ObjectDetectionResult>()

                for (i in 0 until numberOfDetections.getIntValue(0)) {
                    val labelName = labels[outputs.classesAsTensorBuffer.getIntValue(i)] ?: "Etiqueta Desconocida"
                    val confidence = scores.getFloatValue(i)
                    val left = locations.getFloatValue(i * 4)
                    val top = locations.getFloatValue(i * 4 + 1)
                    val right = locations.getFloatValue(i * 4 + 2)
                    val bottom = locations.getFloatValue(i * 4 + 3)

                    val boundingBox = RectF(left, top, right, bottom)

                    // Crea una instancia de ObjectDetectionResult y agrégala a la lista
                    val objectDetectionResult = ObjectDetectionResult(labelName, confidence, boundingBox)
                    detectionResults.add(objectDetectionResult)

                    // Actualiza el MutableStateFlow con el resultado actual
                    objectDetectionResultFlow.value = listOf(objectDetectionResult)
                }

                // Cierra los recursos del modelo
                model.close()

                // Libera la ImageProxy
                imageProxy.close()

            } catch (e: Exception) {
                Log.e("ObjectDetection", "Error durante la detección de objetos: ${e.message}")
            }
        }

        return objectDetectionResultFlow
    }


    private fun convertImageToBitmap(image: Image): Bitmap? {
        val planes = image.planes
        val yBuffer = planes[0].buffer
        val uBuffer = planes[1].buffer
        val vBuffer = planes[2].buffer
        val ySize = yBuffer.remaining()
        val uSize = uBuffer.remaining()
        val vSize = vBuffer.remaining()
        val nv21 = ByteArray(ySize + uSize + vSize)

        // Copia los datos de los planos YUV a nv21
        yBuffer.get(nv21, 0, ySize)
        vBuffer.get(nv21, ySize, vSize)
        uBuffer.get(nv21, ySize + vSize, uSize)

        val yuvImage = YuvImage(nv21, ImageFormat.NV21, image.width, image.height, null)
        val outputStream = ByteArrayOutputStream()
        yuvImage.compressToJpeg(Rect(0, 0, image.width, image.height), 100, outputStream)
        val jpegBytes = outputStream.toByteArray()

        return BitmapFactory.decodeByteArray(jpegBytes, 0, jpegBytes.size)
    }

}
