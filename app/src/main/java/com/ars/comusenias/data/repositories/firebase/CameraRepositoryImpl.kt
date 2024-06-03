package com.ars.comusenias.data.repositories.firebase

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.OutputFileOptions
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
import com.ars.comusenias.R
import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.domain.models.overlayView.ResultOverlayView
import com.ars.comusenias.domain.models.recognizerSign.ResultBundle
import com.ars.comusenias.domain.repositories.CameraRepository
import com.ars.comusenias.presentation.activities.MainActivity.Companion.getLevelViewModel
import com.ars.comusenias.presentation.navigation.AppScreen
import com.ars.comusenias.presentation.ui.theme.ERROR_BINDING_CAMERA
import com.ars.comusenias.presentation.ui.theme.ERROR_TAKE_PICTURE
import com.ars.comusenias.presentation.ui.theme.SHOW_CAMERA_PREVIEW
import com.ars.comusenias.presentation.ui.theme.UTF_8
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import java.net.URLEncoder
import java.util.concurrent.Executors
import javax.inject.Inject

class CameraRepositoryImpl @Inject constructor(
    private val processCameraProvider: ProcessCameraProvider,
    private val preview: Preview,
    private val cameraSelector: CameraSelector,
    private var imageCapture: ImageCapture,
    private val videoCapture: VideoCapture<Recorder>,
    private val context: Context,
    private val gestureRecognizerHelper: GestureRecognizerHelper,
    private val mediaStoreOutputOptionsForImage:OutputFileOptions,
    private val mediaStoreOutputOptionsForVideo:MediaStoreOutputOptions,
    private val imageAnalysis:ImageAnalysis
) : CameraRepository, GestureRecognizerHelper.GestureRecognizerListener {

    private  var recording: Recording? = null

    private lateinit var lifecycleOwner: LifecycleOwner

    private val recognitionResultsMutableFlow = MutableStateFlow<ResultOverlayView?>(null)

    init {
        gestureRecognizerHelper.setListener(this)
    }


    /*
     * Toma una foto y guarda la imagen en un archivo, luego navega a la pantalla InterpretationStatusScreen con la URL de la imagen.
     *
     * @param outputOptions Las opciones de salida del archivo de imagen que se capturará.
     * @param navController Controlador de navegación para manejar la navegación entre las pantallas de la aplicación.
     */
    override suspend fun captureAndSaveImage(navController: NavController) {
           try {
            imageCapture.takePicture(
                mediaStoreOutputOptionsForImage,
                ContextCompat.getMainExecutor(context),
                object : ImageCapture.OnImageSavedCallback {
                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        takePhotoAndSave(navController,outputFileResults)
                    }

                    override fun onError(exception: ImageCaptureException) {
                        Toast.makeText(context, ERROR_TAKE_PICTURE,Toast.LENGTH_SHORT).show()
                        Log.e(TAG, "ImageCapture Error: ${exception.message}", exception)
                    }
                }
            )
        } catch (e: Exception) {
            Log.e(TAG, "Exception in captureAndSaveImage: ${e.message}", e)
        }
    }

    private fun takePhotoAndSave(navController: NavController, outputFileResults: ImageCapture.OutputFileResults) {
        val savedUri = outputFileResults.savedUri ?: Uri.EMPTY
        val encodedUrl = URLEncoder.encode(savedUri.toString(), UTF_8)
        val route = AppScreen.InterpretationStatusScreen.createRoute(encodedUrl)
        navController.navigate(route)
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
        this.lifecycleOwner = lifecycleOwner
        preview.setSurfaceProvider(previewView.surfaceProvider)
        bindCameraToLifecycle(lifecycleOwner)

    }

    @SuppressLint("RestrictedApi")
    private fun bindCameraToLifecycle(lifecycleOwner: LifecycleOwner) {
        try {
            // Desenlaza todos los casos de uso anteriores
            processCameraProvider.unbindAll()


            processCameraProvider.bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                imageAnalysis,
                preview,
                imageCapture
            )


            //Falta el VideoCapture

        } catch (e: Exception) {
            Log.e(SHOW_CAMERA_PREVIEW, ERROR_BINDING_CAMERA, e)
            Response.Error(e)
        }
    }




    /**
     * Inicia la detección  en las imágenes capturadas por el análisis de imágenes.
     *
     * @return Flow<ResultOverlayView> Un flujo de objetos ResultOverlayView que representan los resultados del reconocimiento de objetos.
     */
    @OptIn(ExperimentalGetImage::class)
    override suspend fun startDetection(): Flow<ResultOverlayView> {

        imageAnalysis.setAnalyzer(
           Executors.newSingleThreadExecutor()
        ) { imageProxy ->
            gestureRecognizerHelper.recognizeLiveStream(imageProxy)
            imageProxy.close()
        }
        return recognitionResultsMutableFlow.filterNotNull()
    }




    @SuppressLint("MissingPermission")
    override suspend fun recordVideo(navController: NavController) {
            if (recording != null) {
                recording?.stop()
                recording?.close()
                recording = null
            }

            val recordingListener = Consumer<VideoRecordEvent> { event ->
                when (event) {
                    is VideoRecordEvent.Start -> {
                        Toast.makeText(
                            context,
                            context.getText(R.string.recordStartCamera),
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    is VideoRecordEvent.Finalize -> {
                        if (event.hasError()) {
                            Toast.makeText(
                                context,
                                context.getText(R.string.recordFailedCamera),
                                Toast.LENGTH_LONG
                            ).show()

                            Log.e(TAG, "Video capture ends with error: ${event.error}")
                            recording?.close()
                            recording = null
                        } else {
                            val videoUri = event.outputResults.outputUri
                            getLevelViewModel.pathVideo = videoUri.toString()
                            navController.navigate(AppScreen.InterpretationStatusScreen.route)
                            Toast.makeText(
                                context,
                                context.getText(R.string.recordSuccessCamera),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }

            // Prepare the video capture use case
            recording =
                videoCapture.output.prepareRecording(context, mediaStoreOutputOptionsForVideo)
                    .withAudioEnabled()
                    .start(ContextCompat.getMainExecutor(context), recordingListener)


    }

    override suspend fun stopRecording() {
        recording.let {
            it?.stop()
            it?.close()
        }
    }

    override fun onError(error: String, errorCode: Int) {
        Response.Error(Exception(error))
    }

    /**
     * Esta función se llama cuando se obtienen los resultados de la identificación.
     * Establece el valor de recognitionResultsMutableFlow con una instancia de ResultOverlayView que representa
     * visualmente los resultados en la imagen de entrada.
     *
     * @param resultBundle El paquete de resultados que contiene los resultados de la identificación,
     * la altura y la anchura de la imagen de entrada.
     */
    override fun onResults(resultBundle: ResultBundle) {
        val results = resultBundle.results
        val inputImageHeight = resultBundle.inputImageHeight
        val inputImageWidth = resultBundle.inputImageWidth
        recognitionResultsMutableFlow.value =
            ResultOverlayView(results, inputImageHeight, inputImageWidth)
    }
}
