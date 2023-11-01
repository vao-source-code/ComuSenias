package com.example.comusenias.domain.repositories

import kotlinx.coroutines.flow.Flow

interface QRRepository {

    fun startScanning(): Flow<String?>


}