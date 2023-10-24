package com.example.comusenias.core.di

import android.app.Application
import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import com.example.comusenias.data.api.ApiService
import com.example.comusenias.data.repositories.GalleryRepositoryImpl
import com.example.comusenias.domain.repositories.CameraRepository
import com.example.comusenias.domain.repositories.GalleryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object GalleryModule {

    @Provides
    fun provideGalleryRepository(apiService: ApiService): GalleryRepository =GalleryRepositoryImpl(apiService = apiService )


}