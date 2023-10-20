package com.example.comusenias.presentation.component.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.R
import com.example.comusenias.presentation.ui.theme.SIZE16
import com.example.comusenias.presentation.ui.theme.SIZE36
import com.example.comusenias.presentation.ui.theme.blackColorApp
import com.example.comusenias.presentation.ui.theme.iconColorProgress
@Preview(showBackground = true)

@Composable
fun TopBarHome(
    name: String = "",
    image: Int = R.drawable.profile_avatar,
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
        contentColor = Color.White,
        actions = {
            IconButton(
                onClick = { onClick() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.notifications),
                    contentDescription = "",
                    tint = iconColorProgress
                )
            }
        }
    )
}

@Composable
fun ContentHelloUser(
    name: String,
    image: Int
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.End),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier.size(SIZE36.dp),
            painter = painterResource(id = R.drawable.profile_avatar),
            contentDescription = "image description",
            contentScale = ContentScale.FillBounds
        )
        Text(
            text = "Hola Juan!",
            style = TextStyle(
                fontSize = SIZE16.sp,
                fontWeight = FontWeight.SemiBold,
                color = blackColorApp
            )
        )
    }
}