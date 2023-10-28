package com.example.comusenias.core.di

import com.example.comusenias.data.api.ApiService
import com.example.comusenias.data.repositories.firebase.GalleryRepositoryImpl
import com.example.comusenias.domain.repositories.GalleryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object GalleryModule {
    @Provides
    fun provideGalleryRepository(apiService: ApiService): GalleryRepository =
        GalleryRepositoryImpl(apiService = apiService)
}