package com.example.comusenias.core.di

import CameraRepositoryImpl
import android.app.Application
import android.content.Context
import android.view.Surface
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.navigation.NavController
import com.example.comusenias.domain.repositories.CameraRepository
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
    fun provideApplicationContext(application: Application): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideCameraSelector(): CameraSelector = CameraSelector.Builder().requireLensFacing(
        CameraSelector.LENS_FACING_FRONT
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

    @Provides
    fun provideCameraRepository(
        cameraProvider: ProcessCameraProvider,
        cameraSelector: CameraSelector,
        preview: Preview,
        imageCapture: ImageCapture,
        context: Context
    ): CameraRepository {
        return CameraRepositoryImpl(
            cameraProvider,
            cameraSelector,
            preview,
            imageCapture,
            context
        )
    }
}



