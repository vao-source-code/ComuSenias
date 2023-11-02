package com.example.comusenias.presentation.component.addPatient

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.ui.theme.ICONAPP
import com.example.comusenias.presentation.ui.theme.SIZE12
import com.example.comusenias.presentation.ui.theme.SIZE24
import com.example.comusenias.presentation.ui.theme.iconColorProgress

@Composable
fun FieldWithIcon(icon: Painter, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(SIZE12.dp)
    ) {
        Icon(
            painter = icon,
            modifier = Modifier.size(SIZE24.dp),
            tint = iconColorProgress,
            contentDescription = ICONAPP,
        )
        Text(text = text)
    }
}