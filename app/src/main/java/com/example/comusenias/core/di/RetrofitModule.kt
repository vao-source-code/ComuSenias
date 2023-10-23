package com.example.comusenias.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RetrofitModule {
    @Provides
    @Singleton
    fun provideApiService(): Retrofit? {
        return Retrofit.Builder()
            .baseUrl("http://192.168.0.95:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}