package com.example.comusenias.domain.repositories

import android.graphics.Bitmap
import com.example.comusenias.domain.models.users.ChildrenModel
import kotlinx.coroutines.flow.Flow

interface QRRepository {

    fun startScanning(): Flow<String?>

    fun starGenerate(children: ChildrenModel): Flow<Bitmap>
}