package com.example.comusenias.data.repositories

import com.example.comusenias.domain.repositories.QRRepository
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class QRRepositoryImpl @Inject constructor(
    private val scanner: GmsBarcodeScanner
) : QRRepository {
    override fun startScanning(): Flow<String?> = callbackFlow {
        scanner.startScan()
            .addOnSuccessListener { barcode ->
                launch {
                    barcode.rawValue
                    send(getDetalis(barcode))
                }
            }
            .addOnFailureListener {
                it.printStackTrace()
            }


        awaitClose {}

    }


    private fun getDetalis(barcode: Barcode): String {
        return when (barcode.valueType) {
            Barcode.TYPE_TEXT -> {
                barcode.displayValue.toString()
            }

            else -> {
                barcode.rawValue ?: "Error al querer leer el codigo QR"
            }
        }


    }
}