package com.example.comusenias.core.di

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.Surface
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import com.example.comusenias.data.repositories.CustomCameraRepositoryImpl
import com.example.comusenias.data.repositories.ObjectDetectionRepositoryImpl
import com.example.comusenias.domain.repositories.CustomCameraRepository
import com.example.comusenias.domain.repositories.ObjectDetectionRepository
import com.example.comusenias.domain.use_cases.camera.DetectObjectsUseCase
import com.example.comusenias.presentation.view_model.ObjectDectectionViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.Executors
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object CameraModule {
    @Singleton
    @Provides
    fun provideCameraSelector(): CameraSelector = CameraSelector.Builder().requireLensFacing(
        CameraSelector.LENS_FACING_BACK
    ).build()

    @Singleton
    @Provides
    fun provideCameraProvider(application: Application): ProcessCameraProvider =
        ProcessCameraProvider.getInstance(application).get()

    @Singleton
    @Provides
    fun provideCameraPreview(): Preview = Preview.Builder().build()

    @Singleton
    @Provides
    fun provideImageCapture(): ImageCapture =
        ImageCapture.Builder().setFlashMode(ImageCapture.FLASH_MODE_ON).setTargetRotation(
            Surface.ROTATION_90
        ).build()

    @Singleton
    @Provides
    fun provideCustomCameraRepo(
        cameraProvides: ProcessCameraProvider,
        selector: CameraSelector,
        imageCapture: ImageCapture,
        imageAnalysis: ImageAnalysis,
        preview: Preview
    ): CustomCameraRepository {
        return CustomCameraRepositoryImpl(
            cameraProvides,
            selector,
            preview,
            imageAnalysis,
            imageCapture
        )
    }



    @Provides
    fun provideObjectDetectionRepository(
        @ApplicationContext context: Context,
    ): ObjectDetectionRepository {
        return ObjectDetectionRepositoryImpl(context)
    }

    @Singleton
    @Provides
    fun provideDetectObjectUseCase(
        objectDetectionRepository: ObjectDetectionRepository
    ): DetectObjectsUseCase {
        return DetectObjectsUseCase(objectDetectionRepository)
    }

    @Singleton
    @Provides
    fun provideObjectDetectionViewModel(
        detectObjectUseCase: DetectObjectsUseCase
    ): ObjectDectectionViewModel {
        return ObjectDectectionViewModel(detectObjectUseCase)
    }

    @Singleton
    @Provides
    @androidx.annotation.OptIn(androidx.camera.core.ExperimentalGetImage::class)
    fun  provideImageAnalysis(objectDetectionViewModel: ObjectDectectionViewModel): ImageAnalysis {
        return ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .setOutputImageFormat(OUTPUT_IMAGE_FORMAT_RGBA_8888)
            .build()
            .apply {
                setAnalyzer(Executors.newSingleThreadExecutor()) { imageProxy ->

                    // Process the bitmap or pass it to your object detection function
                    objectDetectionViewModel.detectObject(imageProxy)


                    // Important: Close the ImageProxy to free up resources
                    //imageProxy.close()
                }
            }
    }



}