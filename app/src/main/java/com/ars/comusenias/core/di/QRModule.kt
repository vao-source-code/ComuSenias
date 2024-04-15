package com.ars.comusenias.core.di

import android.content.Context
import com.ars.comusenias.data.repositories.QRRepositoryImpl
import com.ars.comusenias.domain.repositories.QRRepository
import com.google.android.gms.common.moduleinstall.ModuleInstall
import com.google.android.gms.common.moduleinstall.ModuleInstallClient
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import com.journeyapps.barcodescanner.BarcodeEncoder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object QRModule {


    @Singleton
    @Provides
    fun provideOptions(): GmsBarcodeScannerOptions {
        return GmsBarcodeScannerOptions.Builder().setBarcodeFormats(
            Barcode.FORMAT_QR_CODE
        ).build()
    }

    @Provides
    fun provideBarcodeEncoder(): BarcodeEncoder {
        return BarcodeEncoder()
    }


    @Singleton
    @Provides
    fun provideScanner(context: Context, options: GmsBarcodeScannerOptions): GmsBarcodeScanner {
        return GmsBarcodeScanning.getClient(context, options)
    }


    @Singleton
    @Provides
    fun bindQRRepository(repository: QRRepositoryImpl): QRRepository {
        return repository
    }

    @Provides
    fun provideGooglePlayModule(context: Context): ModuleInstallClient {
        return ModuleInstall.getClient(context)
    }
}