package com.example.comusenias.presentation.component.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.comusenias.R
import com.example.comusenias.presentation.ui.theme.PROFILE_USER
import com.example.comusenias.presentation.ui.theme.SIZE10
import com.example.comusenias.presentation.ui.theme.SIZE16
import com.example.comusenias.presentation.ui.theme.SIZE36
import com.example.comusenias.presentation.ui.theme.blackColorApp

@Composable
fun TopBarHome(
    name: String = "",
    image: String? = "",
    onClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            ContentHelloUser(
                name = name,
                image = image
            )
        },
        backgroundColor = Color.White,
        contentColor = Color.White
    )
}

@Composable
fun ContentHelloUser(
    name: String,
    image: String?
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            SIZE10.dp,
            Alignment.End
        ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (image != null) {
            AsyncImage(
                modifier = Modifier
                    .size(SIZE36.dp)
                    .clip(CircleShape),
                model = image,
                contentDescription = PROFILE_USER,
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.profile_avatar)
            )
        }
        Text(
            text = name,
            style = TextStyle(
                fontSize = SIZE16.sp,
                fontWeight = FontWeight.SemiBold,
                color = blackColorApp
            )
        )
    }
}