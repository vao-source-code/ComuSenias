package com.ars.comusenias.core.di

import com.ars.comusenias.data.api.ApiService
import com.ars.comusenias.data.repositories.firebase.GalleryRepositoryImpl
import com.ars.comusenias.domain.repositories.GalleryRepository
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