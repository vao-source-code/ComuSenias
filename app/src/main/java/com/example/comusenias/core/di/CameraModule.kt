package com.example.comusenias.core.di

import android.app.Application
import android.view.Surface
import androidx.camera.core.CameraProvider
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import com.example.comusenias.data.repositories.CustomCameraRepositoryImpl
import com.example.comusenias.domain.repositories.CustomCameraRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CameraModule {
    @Singleton
    @Provides
    fun provideCameraSelector(): CameraSelector = CameraSelector.Builder().requireLensFacing(
        CameraSelector.LENS_FACING_FRONT).build()
    @Singleton
    @Provides
    fun provideCameraProvider(application: Application): ProcessCameraProvider = ProcessCameraProvider.getInstance(application).get()
    @Singleton
    @Provides
    fun provideCameraPreview(): Preview = Preview.Builder().build()
    @Singleton
    @Provides
    fun provideImageCapture(): ImageCapture =  ImageCapture.Builder().setFlashMode(ImageCapture.FLASH_MODE_ON).setTargetRotation(
        Surface.ROTATION_90).build()
    @Singleton
    @Provides
    fun provideImageAnalysys(): ImageAnalysis = ImageAnalysis.Builder().setBackpressureStrategy(
        ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
    ).build()

    @Singleton
    @Provides
    fun provideCustomCameraRepo(
        cameraProvides: ProcessCameraProvider,
        selector:CameraSelector,
        imageCapture: ImageCapture,
        imageAnalysis: ImageAnalysis,
        preview: Preview):CustomCameraRepository{
        return CustomCameraRepositoryImpl(cameraProvides,selector,preview,imageAnalysis,imageCapture)
    }
}