package com.example.comusenias.presentation.component.specialist.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.comusenias.domain.models.users.ChildrenModel
import com.example.comusenias.presentation.ui.theme.PROFILE_USER
import com.example.comusenias.presentation.ui.theme.SIZE1
import com.example.comusenias.presentation.ui.theme.SIZE10
import com.example.comusenias.presentation.ui.theme.SIZE16
import com.example.comusenias.presentation.ui.theme.SIZE36
import com.example.comusenias.presentation.ui.theme.SIZE50
import com.example.comusenias.presentation.ui.theme.blackColorApp
import com.example.comusenias.presentation.ui.theme.line_divisor

@Composable
fun CardProfileUser(user: ChildrenModel, onClickCard: () -> Unit) {
    val image = user.image
    val name = remember { user.name}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClickCard() },
        verticalArrangement = Arrangement.spacedBy(SIZE10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(SIZE36.dp)
        ) {
            if (image != null) {
                AsyncImage(
                    model = image,
                    contentDescription = PROFILE_USER,
                    modifier = Modifier
                        .size(SIZE50.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,

                    )
            }
            Text(
                text = name,
                style = TextStyle(
                    fontSize = SIZE16.sp,
                    fontWeight = FontWeight.Normal,
                    color = blackColorApp
                )
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(SIZE1.dp),
            color = line_divisor
        )
    }
}

