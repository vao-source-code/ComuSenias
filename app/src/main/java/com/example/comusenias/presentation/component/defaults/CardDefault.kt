package com.example.comusenias.presentation.component.defaults

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.ui.theme.SIZE16
import com.example.comusenias.presentation.ui.theme.SIZE4
import com.example.comusenias.presentation.ui.theme.SIZE8

@Composable
fun CardInfoDefault(
    title: String,
    description: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = SIZE4.dp
    ) {
        Column(
            modifier = Modifier.padding(SIZE16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = Color.Gray
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(start = SIZE8.dp)
                )
            }
            Text(
                text = description,
                style = MaterialTheme.typography.body1
            )
        }
    }
}
