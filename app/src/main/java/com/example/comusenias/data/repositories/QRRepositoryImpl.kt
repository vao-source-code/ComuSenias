package com.example.comusenias.data.repositories

import android.graphics.Bitmap
import com.example.comusenias.domain.models.users.ChildrenModel
import com.example.comusenias.domain.repositories.QRRepository
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class QRRepositoryImpl @Inject constructor(
    private val scanner: GmsBarcodeScanner,
    private val barcode: BarcodeEncoder,
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

    override fun starGenerate(children: ChildrenModel): Flow<Bitmap> = callbackFlow {
        launch {
            val bitmap = barcode.encodeBitmap(children.toJson(), BarcodeFormat.QR_CODE, 400, 400)
            send(bitmap)
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