package com.example.comusenias.presentation.component.statitics.pieChart

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier


@Composable
fun SliceThicknessRow(sliceThickness: Float, onValueUpdated: (Float) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(),
        verticalAlignment = CenterVertically
    ) {
        Slider(
            value = sliceThickness,
            onValueChange = { onValueUpdated(it) },
            valueRange = 10f.rangeTo(100f)
        )
    }
}