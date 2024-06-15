package com.example.comusenias.data.repositories.firebase

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.MediaStoreOutputOptions
import androidx.camera.video.Recorder
import androidx.camera.video.Recording
import androidx.camera.video.VideoCapture
import androidx.camera.video.VideoRecordEvent
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.core.util.Consumer
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.example.comusenias.domain.library.toast
import com.example.comusenias.domain.models.mediapipe.DetectionFace
import com.example.comusenias.domain.models.mediapipe.DetectionHand
import com.example.comusenias.domain.models.mediapipe.DetectionPose
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.domain.repositories.CameraRepository
import com.example.comusenias.presentation.activities.MainActivity.Companion.getLevelViewModel
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.ERROR_BINDING_CAMERA
import com.example.comusenias.presentation.ui.theme.ERROR_STOPPING_CAMERA
import com.example.comusenias.presentation.ui.theme.ERROR_TAKE_PICTURE
import com.example.comusenias.presentation.ui.theme.SHOW_CAMERA_PREVIEW
import com.example.comusenias.presentation.ui.theme.UTF_8
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URLEncoder
import java.util.concurrent.Executors
import javax.inject.Inject

class CameraRepositoryImpl @Inject constructor(
    private val cameraProvider: ProcessCameraProvider,
    private val preview:Preview,
    private val cameraSelector:CameraSelector,
    private val imageCapture:ImageCapture,
    private var videoCapture:VideoCapture<Recorder>,
    private val recorder: Recorder,
    private val context:Context,
    private val gestureRecognizerHelper:GestureRecognizerHelper,
    private val faceLandmarkerHelper: FaceLandmarkerHelper,
    private val poseLandmarkerHelper: PoseLandmarkerHelper,
    private val mediaStoreOutputOptionsForImage:ImageCapture.OutputFileOptions,
    private val mediaStoreOutputOptionsForVideo:MediaStoreOutputOptions,
    private val imageAnalysis:ImageAnalysis,
) : CameraRepository, GestureRecognizerHelper.GestureRecognizerListener , FaceLandmarkerHelper.FaceLandmarkerListener , PoseLandmarkerHelper.LandmarkerListener{
    private lateinit var lifecycleOwner: LifecycleOwner

    private var recording: Recording? = null


    private val recognitionHandsResults = MutableStateFlow<DetectionHand?>(null)
    private val recognitionFaceResults = MutableStateFlow<DetectionFace?>(null)
    private val recognitionPoseResults = MutableStateFlow<DetectionPose?>(null)

    init {
        gestureRecognizerHelper.setListener(this)
        poseLandmarkerHelper.setListener(this)
        faceLandmarkerHelper.setListener(this)
    }


    /**
     * Captura una imagen utilizando la cámara y la guarda en el almacenamiento del dispositivo.
     *
     * @param navController NavController utilizado para navegar a una nueva pantalla después de que la imagen se ha guardado con éxito.
     *
     * La función configura las opciones de salida para la captura de la imagen, incluyendo el nombre del archivo,
     * el tipo de archivo, y la ruta del archivo. Luego, captura la imagen y la guarda en el almacenamiento del dispositivo.
     *
     * Si la imagen se guarda con éxito, la función navega a la pantalla `InterpretationStatusScreen` con la URI de la imagen guardada.
     * Si ocurre un error durante la captura de la imagen, la función muestra un mensaje de error.
     */
    override suspend fun captureAndSaveImage(navController: NavController) {
        takeAPicture(mediaStoreOutputOptionsForImage, navController)
    }

    /**
     * Toma una foto y guarda la imagen en un archivo, luego navega a la pantalla InterpretationStatusScreen con la URL de la imagen.
     *
     * @param outputOptions Las opciones de salida del archivo de imagen que se capturará.
     * @param navController Controlador de navegación para manejar la navegación entre las pantallas de la aplicación.
     */
    private fun takeAPicture(
        outputOptions: ImageCapture.OutputFileOptions,
        navController: NavController
    ) {
        imageCapture.takePicture(
                outputOptions,
                ContextCompat.getMainExecutor(context),
                object : ImageCapture.OnImageSavedCallback {
                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {

                        val savedUri = outputFileResults.savedUri ?: Uri.EMPTY
                        val encodedUrl = URLEncoder.encode(savedUri.toString(), UTF_8)
                        val route = AppScreen.InterpretationStatusScreen.createRoute(encodedUrl)

                        navController.navigate(route)
                    }

                    override fun onError(exception: ImageCaptureException) {
                        context.toast(ERROR_TAKE_PICTURE)
                    }
                }
        )

    }

    /**
     * Muestra una vista previa de la cámara en la interfaz de usuario y vincula los casos de uso de la cámara al ciclo de vida del propietario del ciclo de vida.
     *
     * @param previewView La vista en la que se muestra la vista previa de la cámara.
     * @param lifecycleOwner El propietario del ciclo de vida al que se vincularán los casos de uso de la cámara.
     */
    override suspend fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner
    ) {
        lifecycleOwner.let {
            this.lifecycleOwner = it
        }
        withContext(Dispatchers.Main) {
            preview.setSurfaceProvider(previewView.surfaceProvider)
            bindCameraToLifecycle(lifecycleOwner)
        }
    }

    private fun bindCameraToLifecycle(
        lifecycleOwner: LifecycleOwner
    ) = try {

        videoCapture = VideoCapture.withOutput(recorder)

        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            lifecycleOwner,
            cameraSelector,
            preview,
            imageAnalysis,
            imageCapture,
            videoCapture
        )

    } catch (e: Exception) {
        Log.e(SHOW_CAMERA_PREVIEW, ERROR_BINDING_CAMERA, e)
        Response.Error(e)
    }

    /**
     * Detiene la vista previa de la cámara desvinculando todos los casos de uso de la cámara del proveedor de la cámara.
     */
    override suspend fun stopCameraPreview() {
        withContext(Dispatchers.Main) {
            try {
                cameraProvider.unbindAll()
            } catch (e: Exception) {
                Log.e("stopCameraPreview", ERROR_STOPPING_CAMERA, e)
                Response.Error(e)
            }
        }
    }




    /**
     * Inicia la detección de objetos en las imágenes capturadas por el análisis de imágenes.
     *
     * @return Flow<ResultOverlayView> Un flujo de objetos ResultOverlayView que representan los resultados del reconocimiento de objetos.
     */
    @OptIn(ExperimentalGetImage::class)
    override suspend fun startDetection() {

        imageAnalysis.setAnalyzer(
            Executors.newSingleThreadExecutor()
        ) { imageProxy ->

            gestureRecognizerHelper.recognizeLiveStream(imageProxy)
            poseLandmarkerHelper.detectLiveStream(imageProxy,true)
            faceLandmarkerHelper.detectLiveStream(imageProxy,true)

            imageProxy.close()
        }
    }

    override suspend fun resultHands(): Flow<DetectionHand>  = recognitionHandsResults.filterNotNull()
    override suspend fun resultFace(): Flow<DetectionFace>  = recognitionFaceResults.filterNotNull()
    override suspend fun resultPose(): Flow<DetectionPose> = recognitionPoseResults.filterNotNull()


    @SuppressLint("MissingPermission")
    override suspend fun recordVideo(navController: NavController) {

        val videoFolder = File(context.filesDir, "CameraXVideos")

        if (!videoFolder.exists()) {
            videoFolder.mkdirs()
        }

        val recordingListener = Consumer<VideoRecordEvent> { event ->
            when (event) {
                is VideoRecordEvent.Start -> {
                    context.toast("Start Camera")
                }

                is VideoRecordEvent.Finalize -> {
                    if (event.hasError()) {

                        context.toast("Failed Camera")

                        "Video capture ends with error: ${event.error}"

                    } else {

                        val videoUri = event.outputResults.outputUri
                        getLevelViewModel.pathVideo = videoUri.toString()

                        navController.navigate(AppScreen.InterpretationStatusScreen.route)


                    }
                }
            }
        }

        recording = videoCapture.output
            .prepareRecording(context, mediaStoreOutputOptionsForVideo)
            .start(ContextCompat.getMainExecutor(context), recordingListener)

    }

    override suspend fun stopRecording() {
        recording?.let {
            it.stop()
            it.close()
            recording = null
        }
    }


    /**
     * Esta función se llama cuando se obtienen los resultados de la identificación.
     * Establece el valor de recognitionResultsMutableFlow con una instancia de ResultOverlayView que representa
     * visualmente los resultados en la imagen de entrada.
     *
     * @param resultBundle El paquete de resultados que contiene los resultados de la identificación,
     * la altura y la anchura de la imagen de entrada.
     */

    override fun onErrorHand(error: String, errorCode: Int) {
        Response.Error(Exception(error))
    }

    override fun onResultsHand(resultBundle: DetectionHand) {
        val results = resultBundle.results
        val inputImageHeight = resultBundle.inputImageHeight
        val inputImageWidth = resultBundle.inputImageWidth


        recognitionHandsResults.value = DetectionHand(results,inputImageHeight,inputImageWidth)
    }

    override fun onErrorPose(error: String, errorCode: Int) {
        Response.Error(Exception(error))
    }

    override fun onResultsPose(detectionPose: DetectionPose) {
        val results = detectionPose.results
        val inputImageHeight = detectionPose.inputImageHeight
        val inputImageWidth = detectionPose.inputImageWidth

        recognitionPoseResults.value = DetectionPose(results,inputImageHeight,inputImageWidth)
    }

    override fun onErrorFace(error: String, errorCode: Int) {
        Response.Error(Exception(error))
    }

    override fun onResultsFace(detectionFace: DetectionFace) {
        val results = detectionFace.result
        val inputImageHeight = detectionFace.inputImageHeight
        val inputImageWidth = detectionFace.inputImageWidth

        recognitionFaceResults.value = DetectionFace(results,inputImageHeight,inputImageWidth)
    }


}
