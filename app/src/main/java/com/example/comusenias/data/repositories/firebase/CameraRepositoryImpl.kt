package com.example.comusenias.data.repositories.firebase

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.view.Surface
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
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.domain.models.overlayView.ResultOverlayView
import com.example.comusenias.domain.models.recognizerSign.ResultBundle
import com.example.comusenias.domain.repositories.CameraRepository
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.DATE_CAPTURE_FORMAT
import com.example.comusenias.presentation.ui.theme.ERROR_BINDING_CAMERA
import com.example.comusenias.presentation.ui.theme.ERROR_STOPPING_CAMERA
import com.example.comusenias.presentation.ui.theme.ERROR_TAKE_PICTURE
import com.example.comusenias.presentation.ui.theme.IMAGE_JPEG
import com.example.comusenias.presentation.ui.theme.PICTURE_CAMERA_IMAGES
import com.example.comusenias.presentation.ui.theme.SHOW_CAMERA_PREVIEW
import com.example.comusenias.presentation.ui.theme.UTF_8
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.withContext
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
    private val gestureRecognizerHelper: GestureRecognizerHelper
) : CameraRepository, GestureRecognizerHelper.GestureRecognizerListener {

    private var imageAnalysis: ImageAnalysis? = null
    private val recognitionResultsMutableFlow = MutableStateFlow<ResultOverlayView?>(null)

    init {
        setupImageAnalysis()
        gestureRecognizerHelper.setListener(this)
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
        val contentValues = getContentValues(getFormatDate())
        val outputOptions = saveImage(contentValues)

        imageCapture.targetRotation = Surface.ROTATION_0
        takeAPicture(outputOptions, navController)
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
                    context.let {
                        Toast.makeText(
                            it,
                            ERROR_TAKE_PICTURE,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        )
    }

    /**
     * Función para guardar una imagen capturada.
     *
     * Esta función crea un objeto ImageCapture.OutputFileOptions, que se utiliza para configurar
     * dónde se almacenará la imagen capturada. En este caso, la imagen se almacenará en el
     * almacenamiento externo del dispositivo.
     *
     * @param contentValues Conjunto de pares clave-valor que se utilizarán para crear la imagen.
     *                     Estos valores se aplicarán al archivo de imagen a través del ContentResolver.
     * @return ImageCapture.OutputFileOptions Un objeto que contiene las opciones de salida del archivo
     *                                        de imagen, configurado para almacenar la imagen en el
     *                                        almacenamiento externo con los valores proporcionados.
     */
    private fun saveImage(contentValues: ContentValues) =
        ImageCapture.OutputFileOptions.Builder(
            context.contentResolver, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues
        ).build()

    /**
     * Crea un objeto ContentValues con los valores que se utilizarán para crear una imagen.
     *
     * @param name El nombre que se le dará a la imagen capturada.
     * @return ContentValues objeto con los valores de la imagen.
     */
    private fun getContentValues(name: String?) = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, name)
        put(MediaStore.MediaColumns.MIME_TYPE, IMAGE_JPEG)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            put(MediaStore.MediaColumns.RELATIVE_PATH, PICTURE_CAMERA_IMAGES)
        }
    }

    /**
     * Devuelve la fecha y hora actual formateada según el formato DATE_CAPTURE_FORMAT.
     *
     * @return String La fecha y hora actuales en formato DATE_CAPTURE_FORMAT.
     */
    private fun getFormatDate() = SimpleDateFormat(DATE_CAPTURE_FORMAT, Locale.ENGLISH)
        .format(System.currentTimeMillis())


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
        withContext(Dispatchers.Main) {
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
                Log.e(SHOW_CAMERA_PREVIEW, ERROR_BINDING_CAMERA, e)
                Response.Error(e)
            }
        }
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
     * Configura el análisis de imágenes para la captura de imágenes.
     *
     * Establece el objetivo de la relación de aspecto a 4:3, la estrategia de contrapresión para mantener solo la última imagen
     * y el formato de salida de la imagen a RGBA_8888.
     */
    private fun setupImageAnalysis() {
        imageAnalysis = ImageAnalysis.Builder()
            .setTargetAspectRatio(AspectRatio.RATIO_4_3)
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
            .build()
    }

    /**
     * Inicia la detección de objetos en las imágenes capturadas por el análisis de imágenes.
     *
     * @return Flow<ResultOverlayView> Un flujo de objetos ResultOverlayView que representan los resultados del reconocimiento de objetos.
     */
    @OptIn(ExperimentalGetImage::class)
    override fun startObjectDetection(): Flow<ResultOverlayView> {
        imageAnalysis?.setAnalyzer(
            Executors.newSingleThreadExecutor()
        ) { imageProxy ->
            gestureRecognizerHelper.recognizeLiveStream(imageProxy)
            imageProxy.close()
        }
        return recognitionResultsMutableFlow.filterNotNull()
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
