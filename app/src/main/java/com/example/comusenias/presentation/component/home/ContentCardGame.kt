package com.example.comusenias.presentation.component.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.R
import com.example.comusenias.presentation.ui.theme.SIZE12
import com.example.comusenias.presentation.ui.theme.SIZE16
import com.example.comusenias.presentation.ui.theme.SIZE24
import com.example.comusenias.presentation.ui.theme.SIZE27
import com.example.comusenias.presentation.ui.theme.SIZE28
import com.example.comusenias.presentation.ui.theme.SIZE36
import com.example.comusenias.presentation.ui.theme.SIZE80
import com.example.comusenias.presentation.ui.theme.SIZE90
import com.example.comusenias.presentation.ui.theme.blackColorApp
import com.example.comusenias.presentation.ui.theme.cardGray
import com.example.comusenias.presentation.ui.theme.cardInProgress
import com.example.comusenias.presentation.ui.theme.iconColorProgress
import com.example.comusenias.presentation.ui.theme.size15
import com.example.comusenias.presentation.ui.theme.size19
import com.example.comusenias.presentation.ui.theme.size5
import com.example.comusenias.presentation.ui.theme.size50

@Preview(showBackground = true)
@Composable
fun ContentCardGame() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .blur(radius = 0.dp)
    ) {
        CardGame()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = size50.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(size19.dp)
                    .width(size5.dp)
                    .background(color = cardGray, shape = RoundedCornerShape(12.dp))
            )
        }
    }
}

@Composable
fun CardGame() {
    Box (
        modifier = Modifier
            .background(cardInProgress, shape = RoundedCornerShape(SIZE12.dp))
            .fillMaxWidth()
            .height(SIZE90.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = size15.dp, end = SIZE27.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(SIZE28.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .border(size5.dp, cardGray, CircleShape)
                    .size(SIZE80.dp)
                    .padding(size5.dp),
            ) {
                Image(
                    painter = painterResource(R.drawable.vowels),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Vocales",
                    style = TextStyle(
                        fontSize = SIZE16.sp,
                        lineHeight = SIZE24.sp,
                        fontWeight = FontWeight(500),
                        color = blackColorApp,
                        letterSpacing = 0.15.sp,
                    )
                )
                Icon(
                    modifier = Modifier
                        .size(SIZE36.dp),
                    painter = painterResource(id = R.drawable.time_icon),
                    contentDescription = "",
                    tint = iconColorProgress
                )
            }
        }
    }
}