package com.example.comusenias.presentation.component.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.ui.theme.SIZE80
import com.example.comusenias.presentation.ui.theme.size5

@Composable
fun ImageWhitBorder(
    image: Int,
    borderColor: Color
) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .border(size5.dp, borderColor, CircleShape)
            .size(SIZE80.dp)
            .padding(size5.dp),
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}