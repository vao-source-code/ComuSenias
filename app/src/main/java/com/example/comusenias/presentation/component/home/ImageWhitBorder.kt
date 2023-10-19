package com.example.comusenias.presentation.component.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.comusenias.presentation.ui.theme.AVATAR
import com.example.comusenias.presentation.ui.theme.SIZE64
import com.example.comusenias.presentation.ui.theme.size08
import com.example.comusenias.presentation.ui.theme.size5

@Composable
fun ImageWhitBorder(
    image: String,
    borderColor: State<Color>,
    border: Int = size5,
    ) {
    Box(
        modifier = Modifier
            .size(SIZE64.dp)
            .border(
                width = border.dp,
                color = borderColor.value,
                shape = RoundedCornerShape(size08.dp)
            )
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(size08.dp)),
            model = image,
            contentScale = ContentScale.Crop,
            contentDescription = AVATAR
        )
    }
}