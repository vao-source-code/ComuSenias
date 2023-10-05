package com.example.comusenias.presentation.component.gameAction

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.presentation.component.home.ProgressBar
import com.example.comusenias.presentation.ui.theme.CLOSE
import com.example.comusenias.presentation.ui.theme.SIZE12
import com.example.comusenias.presentation.ui.theme.SIZE2
import com.example.comusenias.presentation.ui.theme.SIZE220
import com.example.comusenias.presentation.ui.theme.blackColorApp
import com.example.comusenias.presentation.ui.theme.primaryColorApp
import com.example.comusenias.presentation.ui.theme.size20
import com.example.comusenias.presentation.ui.theme.size24
import com.example.comusenias.presentation.ui.theme.size50

@Composable
fun TopSectionGameAction(
    title: String,
    image: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(size20.dp)
    ) {
        CloseAndProgressBar()
        TitleGameAction(title = title)
        ContentImageGame(image = image)
    }
}

@Composable
fun CloseAndProgressBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(size50.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = CLOSE,
            modifier = Modifier
                .size(size24.dp),
        )
        ProgressBar(progress = 1f)
    }
}

@Composable
fun TitleGameAction(title: String) {
    Text(
        text = title,
        style = TextStyle(
            fontSize = size20.sp,
            fontWeight = FontWeight.SemiBold,
            color = blackColorApp,
            textAlign = TextAlign.Center,
        )
    )
}

@Composable
fun ContentImageGame(image: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(SIZE220.dp)
            .border(
                width = SIZE2.dp,
                color = primaryColorApp,
                shape = RoundedCornerShape(size = SIZE12.dp)
            )
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = image),
            contentDescription = "image description",
            contentScale = ContentScale.Fit
        )
    }
}