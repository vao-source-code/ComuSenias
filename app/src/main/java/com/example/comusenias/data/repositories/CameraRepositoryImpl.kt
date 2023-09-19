import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
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
import com.example.comusenias.ml.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.ByteArrayOutputStream
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

    private val objectDetectionResultFlow = MutableStateFlow<List<ObjectDetectionResult>>(emptyList())

    private var imageAnalysis: ImageAnalysis? = null

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
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
    }

     var detectionResults: ArrayList<ObjectDetectionResult> =  ArrayList()
    @OptIn(ExperimentalGetImage::class)
    override suspend fun startObjectDetection(): Flow<List<ObjectDetectionResult>> {
        imageAnalysis?.setAnalyzer(Executors.newSingleThreadExecutor()) { imageProxy ->
            // Convert the Image to a Bitmap
            val bitmap = convertImageToBitmap(imageProxy.image!!)

            // Ensure the bitmap is not null
            if (bitmap != null) {
                // Scale the bitmap to match the model's input shape (96x96)
                val scaledBitmap = Bitmap.createScaledBitmap(bitmap, 96, 96, true)

                // Convert the Bitmap to a TensorImage
                val image = TensorImage(DataType.FLOAT32)
                image.load(scaledBitmap)
                val byteBuffer = image.buffer

                // Calculate the expected buffer size
                val expectedBufferSize = 1 * 96 * 96 * 3 * 4 // 1 (batch) * 96 * 96 (image dimensions) * 3 (channels) * 4 (bytes for FLOAT32)

                if (byteBuffer.capacity() != expectedBufferSize) {
                    throw IllegalArgumentException("Input buffer size does not match the expected size.")
                }

                // Load the buffer into the TensorBuffer
                val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 96, 96, 3), DataType.FLOAT32)
                inputFeature0.loadBuffer(byteBuffer)

                // Load your model and run inference
                val model = A.newInstance(context)
                val outputs = model.process(inputFeature0)
                Log.d("TAG", "startObjectDetection: " + outputs.outputFeature0AsTensorBuffer.floatArray.contentToString())
                val outputFeature0 = outputs.outputFeature0AsTensorBuffer

                objectDetectionResultFlow.value = processOutput(outputFeature0)

                // Release model resources if no longer used.
                model.close()
            }

            // Close the image proxy
            imageProxy.close()
        }

        return objectDetectionResultFlow
    }


    private fun processOutput( outputFeature0: TensorBuffer): List<ObjectDetectionResult> {
        // Placeholder logic to process the model output and create ObjectDetectionResult list
        // Replace this with your actual processing logic based on your model output format

        val shape = outputFeature0.shape
        if (shape.isEmpty() || shape.size < 2) {
            throw IllegalStateException("Invalid shape for outputFeature0")
        }

        val numResults = shape[1] / 6 // Each detection has 6 values (label, confidence, bounding box)


        for (i in 0 until numResults) {
            val label = outputFeature0.getFloatValue(i * 6)
            val confidence = outputFeature0.getFloatValue(i * 6 + 1)
            val left = outputFeature0.getFloatValue(i * 6 + 2)
            val top = outputFeature0.getFloatValue(i * 6 + 3)
            val right = outputFeature0.getFloatValue(i * 6 + 4)
            val bottom = outputFeature0.getFloatValue(i * 6 + 5)

            val boundingBox = RectF(left, top, right, bottom)

            val detectionResult = ObjectDetectionResult(label.toString(), confidence, boundingBox)
            detectionResults.add(detectionResult)
        }

        return detectionResults
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

        // Crea un bitmap con los datos de nv21
        val yuvImage = YuvImage(nv21, ImageFormat.NV21, image.width, image.height, null)
        val outputStream = ByteArrayOutputStream()
        if (!yuvImage.compressToJpeg(Rect(0, 0, image.width, image.height), 100, outputStream)) {
            return null
        }
        val jpegBytes = outputStream.toByteArray()

        return BitmapFactory.decodeByteArray(jpegBytes, 0, jpegBytes.size)
    }
}
