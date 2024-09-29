package com.ars.comusenias.data.repositories

import android.graphics.Bitmap
import android.util.Log
import com.ars.comusenias.domain.models.users.ChildrenModel
import com.ars.comusenias.domain.repositories.QRRepository
import com.ars.comusenias.presentation.ui.theme.ERROR_SCAN_QR
import com.ars.comusenias.presentation.ui.theme.SIZE400
import com.google.android.gms.common.moduleinstall.ModuleInstallClient
import com.google.android.gms.common.moduleinstall.ModuleInstallRequest
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.TaskCompletionSource
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
        // Instalamos el módulo en la inicialización
        installModule()
    }

    override fun startScanning(): Flow<String?> = callbackFlow {
        // Antes de empezar a escanear, verificamos que el módulo esté disponible
        installModule().addOnSuccessListener {
            // Si el módulo está instalado, comenzamos el escaneo
            scanner.startScan().addOnSuccessListener { barcode ->
                launch {
                    send(getDetails(barcode))
                }
            }.addOnFailureListener { exception ->
                exception.printStackTrace()
                close(exception) // Cierra el flujo si hay un error
            }
        }.addOnFailureListener {
            // Si falla la instalación del módulo, lo registramos en logs
            Log.e("QRRepository", "Error installing QRCodeScanner Module", it)
            close(it) // Cierra el flujo si no se puede instalar el módulo
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
            Barcode.TYPE_TEXT, Barcode.FORMAT_QR_CODE -> barcode.displayValue ?: ERROR_SCAN_QR
            else -> barcode.rawValue ?: ERROR_SCAN_QR
        }
    }

    /**
     * Comprueba si el módulo de escaneo de QR está instalado; si no, lo instala.
     */
    private fun installModule(): Task<Void> {
        val taskCompletionSource = TaskCompletionSource<Void>()
        playModule.areModulesAvailable(scanner).addOnSuccessListener { result ->
            if (!result.areModulesAvailable()) {
                val newRequest = ModuleInstallRequest.newBuilder().addApi(scanner).build()
                playModule.installModules(newRequest)
                    .addOnSuccessListener { taskCompletionSource.setResult(null) }
                    .addOnFailureListener { taskCompletionSource.setException(it) }
            } else {
                taskCompletionSource.setResult(null) // El módulo ya está instalado
            }
        }.addOnFailureListener {
            taskCompletionSource.setException(it)
        }

        return taskCompletionSource.task
    }
}
