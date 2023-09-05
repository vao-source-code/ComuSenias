package com.example.comusenias.presentation.component.defaults

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun TextErrorDefault(errorMsg: String) {

    Text(
        text = errorMsg,
        fontSize = 11.sp,
        color = MaterialTheme.colorScheme.error
    )
}