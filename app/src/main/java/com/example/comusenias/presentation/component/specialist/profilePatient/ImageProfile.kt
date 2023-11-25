package com.example.comusenias.presentation.component.specialist.profilePatient

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.comusenias.R.drawable.profile_avatar
import com.example.comusenias.presentation.ui.theme.SELECTED_IMAGE
import com.example.comusenias.presentation.ui.theme.SIZE64

@Composable
fun ImageProfile(userImage: String?) {
        AsyncImage(
            modifier = Modifier
                .size(SIZE64.dp)
                .clip(CircleShape),
            model = userImage,
            contentScale = Crop,
            contentDescription = SELECTED_IMAGE,
            error = painterResource(id = profile_avatar)
        )
}