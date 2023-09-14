package com.example.comusenias.data.repositories

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Rect
import android.media.Image
import android.util.Log
import androidx.camera.core.ImageProxy
import com.example.comusenias.domain.models.ObjectDetectionResult
import com.example.comusenias.domain.repositories.ObjectDetectionRepository
import com.example.comusenias.ml.ModelFp16Meta
import com.example.comusenias.presentation.screen.camera.ObjectDetectorHelper
import com.example.comusenias.presentation.screen.camera.ObjectDetectorHelper.Companion.DELEGATE_CPU
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.Tensor
import java.io.File
import java.nio.ByteBuffer
import java.nio.ByteOrder
import javax.inject.Inject

class ObjectDetectionRepositoryImpl @Inject constructor(
    context: Context,
    private val delegateType: Int = DELEGATE_CPU,
    private val numThreads: Int = 2
) : ObjectDetectionRepository {

    private var interpreter: Interpreter? = null
    private val IMAGE_MEAN = 128.0f
    private val IMAGE_STD = 128.0f
    private val objectDetectorHelper: ObjectDetectorHelper

    init {
        try {
            val modelInputStream = context.assets.open("model_fp16_meta.tflite")
            val modelFile = File.createTempFile("model_fp16_meta", "tflite")
            modelFile.deleteOnExit()
            modelInputStream.copyTo(modelFile.outputStream())
            if(interpreter ==null){
                interpreter = Interpreter(modelFile)
            }
            else{
                Log.e("Interpreter", " No se puede instancia")
            }

        } catch (e: Exception) {
            Log.e(TAG, "Error initializing model: ${e.message}")
        }

        objectDetectorHelper = ObjectDetectorHelper(
            context = context,
            objectDetectorListener = null,
            threshold = 0.5f,
            numThreads = numThreads,
            currentDelegate = delegateType
        )
    }
    @androidx.annotation.OptIn(androidx.camera.core.ExperimentalGetImage::class)
    override suspend fun  detectObjects(imageProxy: ImageProxy): List<ObjectDetectionResult> {
        val results = mutableListOf<ObjectDetectionResult>()

        try {
            val inputImage = imageToBitmap(imageProxy.image ?: return emptyList())
            val inputTensor = interpreter!!.getInputTensor(0)
            val imageBuffer = convertBitmapToByteBuffer(inputImage, inputTensor)

            val outputLocations = Array(1) { Array(10) { FloatArray(4) } }
            val outputClasses = Array(1) { FloatArray(10) }
            val outputScores = Array(1) { FloatArray(10) }
            val outputNumDetections = FloatArray(1)

            interpreter!!.runForMultipleInputsOutputs(arrayOf(imageBuffer), mapOf(
                0 to outputLocations,
                1 to outputClasses,
                2 to outputScores,
                3 to outputNumDetections
            ))

            val detectionThreshold = 0.5f

            val numClasses = outputClasses[0].size

            for (i in 0 until outputNumDetections[0].toInt()) {
                val score = outputScores[0][i]
                val label = "Class Detected" // Replace with your logic to get the correct label

                val boundingBox = Rect(
                    (outputLocations[0][i][1] * inputImage.width).toInt(),
                    (outputLocations[0][i][0] * inputImage.height).toInt(),
                    (outputLocations[0][i][3] * inputImage.width).toInt(),
                    (outputLocations[0][i][2] * inputImage.height).toInt()
                )

                if (score >= detectionThreshold) {
                    results.add(ObjectDetectionResult(label, score, boundingBox))
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error detecting objects: ${e.message}")
        }

        return results
    }

    fun imageToBitmap(image: Image): Bitmap {
        val planes = image.planes
        val buffer: ByteBuffer = planes[0].buffer
        val width = image.width
        val height = image.height
        val pixelStride = planes[0].pixelStride
        val rowStride = planes[0].rowStride
        val rowPadding = rowStride - pixelStride * width

        val bitmap = Bitmap.createBitmap(
            width + rowPadding / pixelStride, height, Bitmap.Config.ARGB_8888
        )

        buffer.rewind()
        bitmap.copyPixelsFromBuffer(buffer)

        return bitmap
    }
    fun convertBitmapToByteBuffer(bitmap: Bitmap, tensor: Tensor): ByteBuffer {
        val inputShape = tensor.shape()
        val batchSize = inputShape[0]
        val inputSize = inputShape[1]
        val byteBuffer = ByteBuffer.allocateDirect(batchSize * inputSize * inputSize * 3 * 4)
        byteBuffer.order(ByteOrder.nativeOrder())

        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, inputSize, inputSize, true)
        val intValues = IntArray(inputSize * inputSize)
        scaledBitmap.getPixels(intValues, 0, scaledBitmap.width, 0, 0, scaledBitmap.width, scaledBitmap.height)

        val inputBuffer = FloatArray(batchSize * inputSize * inputSize * 3)
        var pixel = 0
        for (i in 0 until inputSize) {
            for (j in 0 until inputSize) {
                val pixelValue = intValues[pixel++]
                inputBuffer[(i * inputSize + j) * 3 + 0] = (Color.red(pixelValue) - IMAGE_MEAN) / IMAGE_STD
                inputBuffer[(i * inputSize + j) * 3 + 1] = (Color.green(pixelValue) - IMAGE_MEAN) / IMAGE_STD
                inputBuffer[(i * inputSize + j) * 3 + 2] = (Color.blue(pixelValue) - IMAGE_MEAN) / IMAGE_STD
            }
        }

        for (value in inputBuffer) {
            byteBuffer.putFloat(value)
        }

        return byteBuffer
    }

    // Rest of your code...

    companion object {
        private const val TAG = "ObjectDetectionRepo"
    }
}
