package com.example.comusenias.presentation.component.specialist.profilePatient

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.comusenias.presentation.ui.theme.SELECTED_IMAGE
import com.example.comusenias.presentation.ui.theme.SIZE100

@Composable
fun ImageProfile(userImage: String?) {
    if (userImage != null) {
        AsyncImage(
            modifier = Modifier
                .size(SIZE100.dp)
                .clip(CircleShape),
            model = userImage,
            contentScale = ContentScale.Crop,
            contentDescription = SELECTED_IMAGE,
        )
    }
}