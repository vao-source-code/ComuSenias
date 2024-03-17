package com.example.comusenias.data.repositories.firebase

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.os.SystemClock
import androidx.camera.core.ImageProxy
import com.example.comusenias.domain.models.mediapipe.DetectionFace
import com.example.comusenias.domain.models.mediapipe.DetectionHand
import com.example.comusenias.domain.models.recognizerSign.DetectionPose
import com.example.comusenias.presentation.ui.theme.MP_RECOGNIZER_FACE
import com.example.comusenias.presentation.ui.theme.MP_RECOGNIZER_HAND
import com.example.comusenias.presentation.ui.theme.MP_RECOGNIZER_POSE
import com.google.mediapipe.framework.image.BitmapImageBuilder
import com.google.mediapipe.framework.image.MPImage
import com.google.mediapipe.tasks.core.BaseOptions
import com.google.mediapipe.tasks.core.Delegate
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.facelandmarker.FaceLandmarker
import com.google.mediapipe.tasks.vision.facelandmarker.FaceLandmarkerResult
import com.google.mediapipe.tasks.vision.gesturerecognizer.GestureRecognizer
import com.google.mediapipe.tasks.vision.gesturerecognizer.GestureRecognizerResult
import com.google.mediapipe.tasks.vision.poselandmarker.PoseLandmarker
import com.google.mediapipe.tasks.vision.poselandmarker.PoseLandmarkerResult

class MLHelper(
    private val context: Context,
    private var faceLandmarkerListener: FaceLandmarkerListener? = null,
    private var gestureRecognizerListener: GestureRecognizerListener? = null,
    private var poseLandmarkerListener:PoseLandmarkerListener?=null

) {

    private var faceLandmarker: FaceLandmarker? = null
    private var gestureRecognizer: GestureRecognizer? = null
    private var poseLandmarker: PoseLandmarker? = null


    init {
        setupFaceLandmarker()
        setupGestureRecognizer()
        setupPoseLandmarker()
    }

    private fun setupPoseLandmarker() {
        val baseOptions = BaseOptions.builder()
            .setDelegate(Delegate.CPU)
            .setModelAssetPath(MP_RECOGNIZER_POSE)
            .build()

        val optionsBuilder = PoseLandmarker.PoseLandmarkerOptions.builder()
            .setBaseOptions(baseOptions)
            .setMinTrackingConfidence(0.5f)
            .setMinPoseDetectionConfidence(0.5f)
            .setMinPosePresenceConfidence(0.5f)
            .setOutputSegmentationMasks(false)
            .setRunningMode(RunningMode.LIVE_STREAM)
            .setResultListener(this::returnLiveStreamPoseLandmarkResult)
            .setErrorListener(this::returnLiveStreamPoseLandmarkError)

        poseLandmarker = PoseLandmarker.createFromOptions(context, optionsBuilder.build())
    }


    // Métodos públicos para establecer los listeners
    fun setFaceLandmarkerListener(listener: FaceLandmarkerListener) {
        this.faceLandmarkerListener = listener
    }

    fun setPoseLandmarkerListener(listener: PoseLandmarkerListener) {
        this.poseLandmarkerListener = listener
    }

    fun setGestureRecognizerListener(listener: GestureRecognizerListener) {
        this.gestureRecognizerListener = listener
    }

    // FaceLandmarker initialization

    private fun setupFaceLandmarker() {
        val baseOptions = BaseOptions.builder()
            .setDelegate(Delegate.CPU)
            .setModelAssetPath(MP_RECOGNIZER_FACE)
            .build()

        val optionsBuilder = FaceLandmarker.FaceLandmarkerOptions.builder()
            .setBaseOptions(baseOptions)
            .setMinFaceDetectionConfidence(0.5f)
            .setMinTrackingConfidence(0.5f)
            .setNumFaces(1)
            .setRunningMode(RunningMode.LIVE_STREAM)
            .setResultListener(this::returnLiveStreamFaceLandmarkResult)
            .setErrorListener(this::returnLiveStreamFaceLandmarkError)

        faceLandmarker = FaceLandmarker.createFromOptions(context, optionsBuilder.build())
    }

    fun detect(imageProxy: ImageProxy) {
        val frameTime = SystemClock.uptimeMillis()

        val bitmapBuffer = Bitmap.createBitmap(
            imageProxy.width, imageProxy.height, Bitmap.Config.ARGB_8888
        )

        val buffer = imageProxy.planes[0].buffer
        bitmapBuffer.copyPixelsFromBuffer(buffer)


        val matrix = Matrix().apply {
            postRotate(imageProxy.imageInfo.rotationDegrees.toFloat())
            postScale(-1f, 1f, imageProxy.width / 2f, imageProxy.height / 2f)
        }

        val rotatedBitmap = Bitmap.createBitmap(
            bitmapBuffer,
            0,
            0,
            bitmapBuffer.width,
            bitmapBuffer.height,
            matrix,
            true
        )

        val mpImage = BitmapImageBuilder(rotatedBitmap).build()


        gestureRecognizer?.recognizeAsync(mpImage, frameTime)
        faceLandmarker?.detectAsync(mpImage, frameTime)
        poseLandmarker?.detectAsync(mpImage, frameTime)


        // Cierra la imagen después de que hayas terminado de usarla
        imageProxy.close()
    }

    // GestureRecognizer initialization

    private fun setupGestureRecognizer() {
        val baseOptions = BaseOptions.builder()
            .setDelegate(Delegate.CPU)
            .setModelAssetPath(MP_RECOGNIZER_HAND)
            .build()

        val optionsBuilder = GestureRecognizer.GestureRecognizerOptions.builder()
            .setBaseOptions(baseOptions)
            .setMinHandDetectionConfidence(0.5f)
            .setMinTrackingConfidence(0.5f)
            .setNumHands(2)
            .setRunningMode(RunningMode.LIVE_STREAM)
            .setResultListener(this::returnLiveStreamGestureRecognizerResult)
            .setErrorListener(this::returnLiveStreamGestureRecognizerError)

        gestureRecognizer = GestureRecognizer.createFromOptions(context, optionsBuilder.build())
    }



    // Common methods

    fun clear() {
        clearFaceLandmarker()
        clearGestureRecognizer()
        clearPoseLandmarker()
    }

    private fun clearFaceLandmarker() {
        faceLandmarker?.close()
        faceLandmarker = null
    }

    private fun clearGestureRecognizer() {
        gestureRecognizer?.close()
        gestureRecognizer = null
    }

    private fun clearPoseLandmarker() {
        poseLandmarker?.close()
        poseLandmarker = null
    }
    private fun returnLiveStreamFaceLandmarkResult(result: FaceLandmarkerResult, input: MPImage) {
        faceLandmarkerListener?.onFaceLandmarkDetected(DetectionFace(result = result, inputImageHeight = input.height, inputImageWidth = input.width))
    }

    private fun returnLiveStreamFaceLandmarkError(error: RuntimeException) {
        faceLandmarkerListener?.onFaceLandmarkError(error.message ?: "Unknown error")
    }

    private fun returnLiveStreamGestureRecognizerResult(result: GestureRecognizerResult, input: MPImage) {
        gestureRecognizerListener?.onGestureRecognized(DetectionHand(results = listOf(result), inputImageHeight = input.height, inputImageWidth = input.height))
    }

    private fun returnLiveStreamGestureRecognizerError(error: RuntimeException) {
        gestureRecognizerListener?.onGestureRecognitionError(error.message ?: "Unknown error")
    }


    private fun returnLiveStreamPoseLandmarkResult(result: PoseLandmarkerResult, input: MPImage) {
        poseLandmarkerListener?.onPoseLandmarkDetected(DetectionPose(results = result, inputImageHeight = input.height, inputImageWidth = input.width))
    }

    private fun returnLiveStreamPoseLandmarkError(error: RuntimeException) {
        poseLandmarkerListener?.onPoseLandmarkError(error.message ?: "Unknown error")
    }


    interface FaceLandmarkerListener {
        fun onFaceLandmarkDetected(detectionFace: DetectionFace)
        fun onFaceLandmarkError(error: String)
    }

    interface GestureRecognizerListener {
        fun onGestureRecognized(detectionHand: DetectionHand)
        fun onGestureRecognitionError(error: String)
    }

    interface PoseLandmarkerListener{
        fun onPoseLandmarkDetected(detectionPose: DetectionPose)
        fun onPoseLandmarkError(error:String)
    }








}
