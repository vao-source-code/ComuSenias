package com.example.comusenias.domain.models.state

import android.graphics.Bitmap
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING

data class QRState(
    val details: String = EMPTY_STRING,
    val bitmap: Bitmap? = null
)