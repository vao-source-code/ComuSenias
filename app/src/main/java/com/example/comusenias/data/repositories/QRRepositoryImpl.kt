package com.example.comusenias.data.repositories

import android.graphics.Bitmap
import android.util.Log
import com.example.comusenias.domain.models.users.ChildrenModel
import com.example.comusenias.domain.repositories.QRRepository
import com.example.comusenias.presentation.ui.theme.ERROR_SCAN_QR
import com.example.comusenias.presentation.ui.theme.SIZE400
import com.google.android.gms.common.moduleinstall.ModuleInstallClient
import com.google.android.gms.common.moduleinstall.ModuleInstallRequest
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
    private val playModule: ModuleInstallClient,
) : QRRepository {

    init {
        installModule()
    }

    override fun startScanning(): Flow<String?> = callbackFlow {
        installModule()
        scanner.startScan().addOnSuccessListener { barcode ->
            launch {
                send(getDetails(barcode))
            }
        }.addOnFailureListener {
            it.printStackTrace()
        }
        awaitClose {}
    }

    override fun starGenerate(children: ChildrenModel): Flow<Bitmap> = callbackFlow {
        launch {
            val bitmap = barcode.encodeBitmap(children.toJson(), BarcodeFormat.QR_CODE, SIZE400, SIZE400)
            send(bitmap)
        }
        awaitClose {}
    }

    private fun getDetails(barcode: Barcode): String {
        return when (barcode.valueType) {
            Barcode.TYPE_TEXT -> {
                barcode.displayValue.toString()
            }

            Barcode.FORMAT_QR_CODE -> {
                barcode.displayValue.toString()
            }

            else -> {
                barcode.rawValue ?: ERROR_SCAN_QR
            }
        }
    }

    /** Checking does ScannerModule is installed on the device otherwise,
    install using ModuleClientAPI, for more visit
    https://developers.google.com/android/guides/module-install-apis **/
    private fun installModule() {
        playModule.areModulesAvailable(scanner).addOnSuccessListener {
            if (!it.areModulesAvailable()) {
                val newRequest = ModuleInstallRequest.newBuilder().addApi(scanner).build()
                playModule.installModules(newRequest)
            }
        }.addOnFailureListener {
            Log.d("QRRepository", "Failed to install QRCodeScanner Module")
        }
    }
}