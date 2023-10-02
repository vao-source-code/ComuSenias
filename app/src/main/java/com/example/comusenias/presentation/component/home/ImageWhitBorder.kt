package com.example.comusenias.presentation.component.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.ui.theme.SIZE64
import com.example.comusenias.presentation.ui.theme.size5

@Composable
fun ImageWhitBorder(
    image: Int,
    borderColor: State<Color>,
    border: Int = size5
) {
    Box(
        modifier = Modifier
            .size(SIZE64.dp)
            .clip(CircleShape)
            .border(border.dp, borderColor.value, CircleShape)
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(image),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop
        )
    }
}
