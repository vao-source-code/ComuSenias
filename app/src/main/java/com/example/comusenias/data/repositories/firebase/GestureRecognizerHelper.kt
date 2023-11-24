package com.example.comusenias.data.repositories.firebase

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.SystemClock
import androidx.annotation.VisibleForTesting
import androidx.camera.core.ImageProxy
import androidx.compose.runtime.remember
import com.example.comusenias.constants.PreferencesConstant.PREFERENCE_LEVEL
import com.example.comusenias.core.PreferenceManager
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.domain.models.recognizerSign.ResultBundle
import com.example.comusenias.presentation.ui.theme.DEFAULT_HAND_DETECTION_CONFIDENCE
import com.example.comusenias.presentation.ui.theme.DEFAULT_HAND_PRESENCE_CONFIDENCE
import com.example.comusenias.presentation.ui.theme.DEFAULT_HAND_TRACKING_CONFIDENCE
import com.example.comusenias.presentation.ui.theme.DELEGATE_CPU
import com.example.comusenias.presentation.ui.theme.DELEGATE_GPU
import com.example.comusenias.presentation.ui.theme.ERROR_NOT_RECOGNIZE_VIDEO_FILE
import com.example.comusenias.presentation.ui.theme.ERROR_NOT_RESPONSE_VIDEO
import com.example.comusenias.presentation.ui.theme.ERROR_NOT_USING_RUNNING_MODE_IMAGE
import com.example.comusenias.presentation.ui.theme.MP_RECOGNIZER_TASK
import com.example.comusenias.presentation.ui.theme.MP_RECOGNIZER_WORDS_TASK
import com.example.comusenias.presentation.ui.theme.OTHER_ERROR
import com.example.comusenias.presentation.ui.theme.UNKNOWN_ERROR
import com.example.comusenias.presentation.ui.theme.UNRECOGNIZED_DELEGATE
import com.google.mediapipe.framework.image.BitmapImageBuilder
import com.google.mediapipe.framework.image.MPImage
import com.google.mediapipe.tasks.core.BaseOptions
import com.google.mediapipe.tasks.core.Delegate
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.gesturerecognizer.GestureRecognizer
import com.google.mediapipe.tasks.vision.gesturerecognizer.GestureRecognizerResult

class GestureRecognizerHelper(
    private var minHandDetectionConfidence: Float = DEFAULT_HAND_DETECTION_CONFIDENCE,
    private var minHandTrackingConfidence: Float = DEFAULT_HAND_TRACKING_CONFIDENCE,
    private var minHandPresenceConfidence: Float = DEFAULT_HAND_PRESENCE_CONFIDENCE,
    private var currentDelegate: Int = DELEGATE_CPU,
    private var runningMode: RunningMode = RunningMode.LIVE_STREAM,
    private val context: Context,
    private var gestureRecognizerListener: GestureRecognizerListener? = null
) {
    private var gestureRecognizer: GestureRecognizer? = null

    init {
        setupGestureRecognizer()
    }

    fun clearGestureRecognizer() {
        gestureRecognizer?.close()
        gestureRecognizer = null
    }

    /**
     * Esta función establece un objeto GestureRecognizer para el reconocimiento de gestos con las manos.
     *
     * Primero, se crea un objeto baseOptions de tipo BaseOptions.builder, que se utilizará para configurar el reconocedor de gestos.
     * El delegate (CPU o GPU) se establece en baseOptions dependiendo del valor de currentDelegate.
     * Después, se define el modelo de reconocimiento de gestos con setModelAssetPath(MP_RECOGNIZER_TASK).
     *
     * Luego, construye las opciones base y crea un objeto GestureRecognizerOptions.builder para configurar opciones adicionales para
     * el reconocedor de gestos, como la confianza mínima para la detección de manos (minHandDetectionConfidence), la confianza mínima
     * para el seguimiento de manos (minHandTrackingConfidence), y la confianza mínima para la presencia de manos (minHandPresenceConfidence).
     * También establece el modo de funcionamiento (runningMode).
     *
     * Si el modo de funcionamiento es RunningMode.LIVE_STREAM, se configuran un ResultListener y un ErrorListener.
     *
     * Por último, intenta construir las opciones del reconocedor de gestos y crear el reconocedor de gestos.
     * Si ocurre una excepción durante este proceso, devuelve un objeto Response.Error.
     *
     * @throws IllegalStateException si hay un problema al crear el GestureRecognizer o si currentDelegate no es ni DELEGATE_CPU ni DELEGATE_GPU.
     */
    private fun setupGestureRecognizer() {
        try {
            var recognizerTask : String = if(PreferenceManager(context).getInt(PREFERENCE_LEVEL , 1) == 1 )
                MP_RECOGNIZER_TASK
            else
                MP_RECOGNIZER_WORDS_TASK

            val baseOptions = BaseOptions.builder()
                .setDelegate(
                    when (currentDelegate) {
                        DELEGATE_CPU -> Delegate.CPU
                        DELEGATE_GPU -> Delegate.GPU
                        else -> throw IllegalStateException(UNRECOGNIZED_DELEGATE + currentDelegate)
                    }
                )
                .setModelAssetPath(recognizerTask)
                .build()

            val optionsBuilder = GestureRecognizer.GestureRecognizerOptions.builder()
                .setBaseOptions(baseOptions)
                .setMinHandDetectionConfidence(minHandDetectionConfidence)
                .setMinTrackingConfidence(minHandTrackingConfidence)
                .setMinHandPresenceConfidence(minHandPresenceConfidence)
                .setRunningMode(runningMode)

            if (runningMode == RunningMode.LIVE_STREAM) {
                optionsBuilder
                    .setResultListener(this::returnLivestreamResult)
                    .setErrorListener(this::returnLivestreamError)
            }

            gestureRecognizer = GestureRecognizer.createFromOptions(context, optionsBuilder.build())
        } catch (e: IllegalStateException) {
            Response.Error(e)
        }
    }

    /**
     * Esta función reconoce una secuencia de transmisión en vivo.
     *
     * Primero, obtiene el tiempo del fotograma actual con `SystemClock.uptimeMillis()`.
     *
     * Luego, crea un búfer de mapa de bits con las mismas dimensiones que la imagen de entrada.
     * Copia los píxeles de la imagen de entrada en el búfer de mapa de bits.
     *
     * A continuación, crea una matriz de transformación para rotar y escalar la imagen.
     * Rota la imagen de acuerdo con la orientación de la imagen de entrada y escala la imagen de -1 en la dirección x y 1 en la dirección y.
     *
     * Después, aplica la matriz de transformación al mapa de bits para obtener un mapa de bits rotado.
     *
     * Finalmente, construye un objeto `MpImage` a partir del mapa de bits rotado y lo pasa junto con el tiempo del fotograma a la función `recognizeAsync()`.
     *
     * @param imageProxy La imagen que se va a reconocer, que se supone que proviene de una secuencia de transmisión en vivo.
     */
    fun recognizeLiveStream(imageProxy: ImageProxy) {
        val frameTime = SystemClock.uptimeMillis()

        val bitmapBuffer = Bitmap.createBitmap(
            imageProxy.width, imageProxy.height, Bitmap.Config.ARGB_8888
        )

        val buffer = imageProxy.planes[0].buffer
        bitmapBuffer.copyPixelsFromBuffer(buffer)

        imageProxy.close()

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

        recognizeAsync(mpImage, frameTime)
    }

    /**
     * Esta función se utiliza para reconocer gestos en una imagen de forma asíncrona.
     * Utiliza un objeto `gestureRecognizer` para realizar el reconocimiento.
     *
     * @VisibleForTesting es una anotación que indica que la función es visible solo con fines de pruebas.
     *
     * @param mpImage es una instancia de MPImage que representa la imagen en la que se reconocerán los gestos.
     * @param frameTime es un Long que representa el tiempo del cuadro en el que se capturó la imagen.
     *
     * @return Esta función no devuelve ningún valor. El resultado del reconocimiento de gestos se maneja en el objeto `gestureRecognizer`.
     */
    @VisibleForTesting
    fun recognizeAsync(mpImage: MPImage, frameTime: Long) {
        gestureRecognizer?.recognizeAsync(mpImage, frameTime)
    }


    /**
     * Esta función se utiliza para reconocer gestos en un archivo de video.
     * Utiliza un objeto `gestureRecognizer` para realizar el reconocimiento.
     *
     * @param videoUri URI del video en el que se reconocerán los gestos.
     * @param inferenceIntervalMs Intervalo de inferencia (en milisegundos) para el reconocimiento de gestos.
     *
     * @return Un objeto ResultBundle que contiene los resultados del reconocimiento de gestos,
     *         o null si ocurrió un error durante el reconocimiento.
     */
    fun recognizeVideoFile(videoUri: Uri, inferenceIntervalMs: Long): ResultBundle? {
        val startTime = SystemClock.uptimeMillis()
        val retriever = MediaMetadataRetriever().apply { setDataSource(context, videoUri) }
        val videoLengthMs =
            retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)?.toLong()

        val firstFrame = retriever.getFrameAtTime(0)
        val width = firstFrame?.width
        val height = firstFrame?.height

        // Si no se pueden obtener los datos necesarios del video, devolver null
        if (videoLengthMs == null || width == null || height == null) return null

        val resultList = mutableListOf<GestureRecognizerResult>()
        val numberOfFrameToRead = videoLengthMs / inferenceIntervalMs

        processFramesFromAVideo(numberOfFrameToRead, inferenceIntervalMs, retriever, resultList)

        retriever.release()

        val inferenceTimePerFrameMs = (SystemClock.uptimeMillis() - startTime) / numberOfFrameToRead

        return ResultBundle(
            resultList,
            inferenceTimePerFrameMs,
            height,
            width
        )
    }

    /**
     * Esta función se utiliza para extraer y procesar marcos de un video para el reconocimiento de gestos.
     *
     * @param numberOfFrameToRead Long que representa el número de marcos a leer del video.
     * @param inferenceIntervalMs Long que representa el intervalo de inferencia (en milisegundos) para el reconocimiento de gestos.
     * @param retriever MediaMetadataRetriever utilizado para extraer los marcos del video.
     * @param resultList MutableList de GestureRecognizerResult para almacenar los resultados del reconocimiento de gestos.
     *
     * La función itera sobre el número total de marcos a leer. Para cada marco, extrae el marco en el tiempo
     * especificado por el producto de la iteración actual y el intervalo de inferencia. Convierte el marco extraído
     * a un formato compatible con el reconocedor de gestos y luego invoca al reconocedor de gestos.
     *
     * Si el reconocedor de gestos puede procesar el marco, el resultado se agrega a la lista de resultados.
     * Si no puede procesar el marco, se lanza una excepción personalizada.
     *
     * Si el marco no se puede extraer en el tiempo especificado, también se lanza una excepción personalizada.
     */
    private fun processFramesFromAVideo(
        numberOfFrameToRead: Long,
        inferenceIntervalMs: Long,
        retriever: MediaMetadataRetriever,
        resultList: MutableList<GestureRecognizerResult>
    ) {
        for (i in 0..numberOfFrameToRead) {
            val timestampMs = i * inferenceIntervalMs

            retriever.getFrameAtTime(timestampMs * 1000, MediaMetadataRetriever.OPTION_CLOSEST)
                ?.let { frame ->
                    val argb8888Frame = frame.run {
                        if (config == Bitmap.Config.ARGB_8888) this else copy(
                            Bitmap.Config.ARGB_8888,
                            false
                        )
                    }
                    val mpImage = BitmapImageBuilder(argb8888Frame).build()

                    gestureRecognizer?.recognizeForVideo(mpImage, timestampMs)?.let(resultList::add)
                        ?: Response.CustomError(ERROR_NOT_RECOGNIZE_VIDEO_FILE)
                }
                ?: Response.CustomError(ERROR_NOT_RESPONSE_VIDEO)
        }
    }

    /**
     * Función para reconocer gestos en una imagen.
     *
     * @param image La imagen en formato Bitmap en la que se reconocerán los gestos.
     *
     * @return Un ResultBundle que contiene los resultados del reconocimiento de gestos.
     *
     * @throws IllegalArgumentException Si el modo de ejecución actual no es RunningMode.IMAGE.
     *
     * La función realiza un reconocimiento de gestos en la imagen proporcionada.
     * Antes de realizar el reconocimiento, verifica si el modo de ejecución actual es RunningMode.IMAGE.
     * Si no es así, lanza una excepción IllegalArgumentException.
     */
    fun recognizeImage(image: Bitmap): ResultBundle? {
        if (runningMode != RunningMode.IMAGE) {
            Response.CustomError(ERROR_NOT_USING_RUNNING_MODE_IMAGE)
        }

        val startTime = SystemClock.uptimeMillis()
        val mpImage = BitmapImageBuilder(image).build()

        gestureRecognizer?.recognize(mpImage)?.also { recognizerResult ->
            val inferenceTimeMs = SystemClock.uptimeMillis() - startTime
            return ResultBundle(
                listOf(recognizerResult),
                inferenceTimeMs,
                image.height,
                image.width
            )
        }
        return null
    }

    fun isClosed(): Boolean {
        return gestureRecognizer == null
    }

    /**
     * Esta función se utiliza para retornar los resultados del reconocimiento de gestos en una transmisión en vivo.
     *
     * @param result GestureRecognizerResult que contiene los resultados del reconocimiento de gestos.
     * @param input MPImage que es la imagen de entrada utilizada para el reconocimiento de gestos.
     *
     * La función calcula el tiempo de inferencia restando el tiempo de registro del resultado del tiempo actual.
     * Luego, crea un objeto ResultBundle con los resultados del reconocimiento, el tiempo de inferencia y las dimensiones de la imagen de entrada.
     * Finalmente, notifica a gestureRecognizerListener con el objeto ResultBundle creado.
     */
    private fun returnLivestreamResult(result: GestureRecognizerResult, input: MPImage) {
        val finishTimeMs = SystemClock.uptimeMillis()
        val inferenceTime = finishTimeMs - result.timestampMs()

        gestureRecognizerListener?.onResults(
            ResultBundle(
                listOf(result), inferenceTime, input.height, input.width
            )
        )
    }

    private fun returnLivestreamError(error: RuntimeException) {
        Response.CustomError(UNKNOWN_ERROR)
    }

    interface GestureRecognizerListener {
        fun onError(error: String, errorCode: Int = OTHER_ERROR)
        fun onResults(resultBundle: ResultBundle)
    }

    fun setListener(listener: GestureRecognizerListener) {
        gestureRecognizerListener = listener
    }
}