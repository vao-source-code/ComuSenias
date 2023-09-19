package com.example.comusenias.presentation.component.defaults

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp

@Composable
fun GetImage(
    painter: Int,
    contentDescription: String = "",
    width: Dp,
    height: Dp,
) {
    Image(
        painter = painterResource(id = painter),
        contentDescription = contentDescription,
        Modifier.size(width, height)
            .testTag("tagImage")
    )
}