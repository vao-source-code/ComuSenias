package com.ars.comusenias.core.di

import com.ars.comusenias.data.repositories.firebase.CameraRepositoryImpl
import android.app.Application
import android.content.Context
import android.view.Surface
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import com.ars.comusenias.data.repositories.firebase.GestureRecognizerHelper
import com.ars.comusenias.domain.repositories.CameraRepository
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
    fun provideApplicationContext(application: Application): Context =
        application.applicationContext

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
    fun provideGestureRecognizerHelper(
        context: Context
    ): GestureRecognizerHelper =
        GestureRecognizerHelper(context = context)

    @Provides
    fun provideCameraRepository(
        cameraProvider: ProcessCameraProvider,
        cameraSelector: CameraSelector,
        preview: Preview,
        imageCapture: ImageCapture,
        context: Context,
        gestureRecognizerHelper: GestureRecognizerHelper
    ): CameraRepository =
        CameraRepositoryImpl(
            cameraProvider,
            cameraSelector,
            preview,
            imageCapture,
            context,
            gestureRecognizerHelper
        )
}