package com.ars.comusenias.presentation.component.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ars.comusenias.R.drawable.diagnostic_category
import com.ars.comusenias.presentation.ui.theme.AVATAR
import com.ars.comusenias.presentation.ui.theme.SIZE3
import com.ars.comusenias.presentation.ui.theme.SIZE64
import com.ars.comusenias.presentation.ui.theme.SIZE8

@Composable
fun ImageWhitBorder(
    image: String,
    borderColor: State<Color>,
    border: Int = SIZE3,
) {
    Box(
        modifier = Modifier
            .size(SIZE64.dp)
            .border(
                width = border.dp,
                color = borderColor.value,
                shape = RoundedCornerShape(SIZE8.dp)
            )
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(SIZE8.dp)),
            model = image,
            contentScale = Crop,
            contentDescription = AVATAR,
            error = painterResource(id = diagnostic_category)
        )
    }
}