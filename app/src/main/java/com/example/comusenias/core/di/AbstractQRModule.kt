package com.example.comusenias.core.di

import com.example.comusenias.data.repositories.QRRepositoryImpl
import com.example.comusenias.domain.repositories.QRRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AbstractQRModule {

    @Binds
    @ViewModelScoped
    abstract fun bindQRRepository(repository: QRRepositoryImpl): QRRepository
}