package com.ars.comusenias.core.di

import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.provider.MediaStore
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.FallbackStrategy
import androidx.camera.video.MediaStoreOutputOptions
import androidx.camera.video.Quality
import androidx.camera.video.QualitySelector
import androidx.camera.video.Recorder
import androidx.camera.video.VideoCapture
import com.ars.comusenias.data.repositories.firebase.CameraRepositoryImpl
import com.ars.comusenias.data.repositories.firebase.FaceLandmarkerHelper
import com.ars.comusenias.data.repositories.firebase.GestureRecognizerHelper
import com.ars.comusenias.data.repositories.firebase.PoseLandmarkerHelper
import com.ars.comusenias.domain.repositories.CameraRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CameraModule {

    // Proporciona el contexto de la aplicación
    @Singleton
    @Provides
    fun provideApplicationContext(application: Application): Context =
        application.applicationContext

    // Proporciona un selector de cámara que selecciona la cámara frontal
    @Singleton
    @Provides
    fun provideCameraSelector(): CameraSelector = CameraSelector.Builder().requireLensFacing(
        CameraSelector.LENS_FACING_FRONT
    ).build()

    // Proporciona una instancia de ProcessCameraProvider
    @Singleton
    @Provides
    fun provideCameraProvider(application: Application): ProcessCameraProvider =
        ProcessCameraProvider.getInstance(application).get()

    // Proporciona una instancia de Preview para mostrar la vista previa de la cámara
    @Singleton
    @Provides
    fun provideCameraPreview(): Preview = Preview.Builder().build()

    // Proporciona una instancia de ImageCapture configurada con el modo de flash y la rotación del objetivo
    @Singleton
    @Provides
    fun provideImageCapture(): ImageCapture =
        ImageCapture.Builder().build()

    // Proporciona un conjunto de valores de contenido para la imagen (nombre del archivo, tipo MIME, ruta relativa)
    @Provides
    fun provideContentValues(): ContentValues =
        ContentValues().apply {
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/CameraImages")
            }
        }

    // Proporciona opciones de salida de archivo configuradas con contentValues
    @Singleton
    @Provides
    fun provideOutputFileOptions(context: Context, contentValues: ContentValues): ImageCapture.OutputFileOptions =
        ImageCapture.OutputFileOptions.Builder(
            context.contentResolver, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues
        ).build()

    // Proporciona opciones de salida para MediaStore para videos
    @Singleton
    @Provides
    fun provideMediaStoreOutputOptionsForVideo(context: Context): MediaStoreOutputOptions {
        val videoFolder = File(context.filesDir, "CameraXVideos")
        if (!videoFolder.exists()) {
            videoFolder.mkdirs()
        }

        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, "video_${System.currentTimeMillis()}")
            put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.MediaColumns.RELATIVE_PATH, "Movies/CameraXVideos")
            }
        }

        return MediaStoreOutputOptions
            .Builder(context.contentResolver, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
            .setContentValues(contentValues)
            .build()
    }

    // Proporciona una instancia de Recorder configurada para la calidad de video
    @Singleton
    @Provides
    fun provideRecorder(): Recorder {
        val selector = QualitySelector
            .from(
                Quality.UHD,
                FallbackStrategy.higherQualityOrLowerThan(Quality.SD)
            )

        return Recorder.Builder()
            .setQualitySelector(selector)
            .build()
    }

    // Proporciona una instancia de VideoCapture configurada con el grabador
    @Singleton
    @Provides
    fun provideVideoCapture(recorder: Recorder): VideoCapture<Recorder> =
        VideoCapture.withOutput(recorder)

    // Proporciona una instancia de ImageAnalysis configurada para el análisis de imágenes en tiempo real
    @Singleton
    @Provides
    fun provideImageAnalysis(): ImageAnalysis {
        return ImageAnalysis.Builder()
            .setTargetAspectRatio(AspectRatio.RATIO_4_3)
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
            .build()
    }




    // Proporciona una instancia de GestureRecognizerHelper para reconocer gestos
    @Provides
    fun provideGestureRecognizerHelper(
        context: Context
    ): GestureRecognizerHelper =
        GestureRecognizerHelper(context = context)

    // Proporciona una instancia de PoseLandmark para reconocer la Posicion del Cuerpo

    @Provides
    fun providePoseLandmarkerHelper(
        context: Context
    ): PoseLandmarkerHelper =
        PoseLandmarkerHelper(context = context)

    // Proporciona una instancia de FaceLandmark para reconocer la cara

    @Provides
    fun provideFaceLandmarkerHelper(
        context: Context
    ): FaceLandmarkerHelper =
        FaceLandmarkerHelper(context = context)


    // Proporciona una instancia de CameraRepository configurada con todos los componentes necesarios
    @Provides
    fun provideCameraRepository(
        processCameraProvider: ProcessCameraProvider,
        preview: Preview,
        cameraSelector: CameraSelector,
        imageCapture: ImageCapture,
        videoCapture:VideoCapture<Recorder>,
        recorder:Recorder,
        context: Context,
        gestureRecognizerHelper: GestureRecognizerHelper,
        faceLandmarkerHelper: FaceLandmarkerHelper,
        poseLandmarkerHelper: PoseLandmarkerHelper,
        mediaStoreOutputOptionsForImage: ImageCapture.OutputFileOptions,
        mediaStoreOutputOptionsForVideo: MediaStoreOutputOptions,
        imageAnalysis: ImageAnalysis,
    ): CameraRepository =
        CameraRepositoryImpl(
            processCameraProvider,
            preview,
            cameraSelector,
            imageCapture,
            videoCapture,
            recorder,
            context,
            gestureRecognizerHelper,
            faceLandmarkerHelper,
            poseLandmarkerHelper,
            mediaStoreOutputOptionsForImage,
            mediaStoreOutputOptionsForVideo,
            imageAnalysis,
        )

}
