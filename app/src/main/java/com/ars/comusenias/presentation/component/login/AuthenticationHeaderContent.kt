package com.ars.comusenias.presentation.component.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ars.comusenias.R
import com.ars.comusenias.presentation.ui.theme.ICONAPP
import com.ars.comusenias.presentation.ui.theme.SIZE250

@Preview(showBackground = true)
@Composable
fun AuthenticationHeaderContent( widthImage : Int = SIZE250,  heightImage : Int = SIZE250) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderImage( widthImage = widthImage, heightImage = heightImage)
    }
}

@Composable
fun HeaderImage(widthImage: Int, heightImage: Int) {
    Image(
        painter = painterResource(id = R.drawable.icon_login_screen),
        contentDescription = ICONAPP,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .width(widthImage.dp)
            .height(heightImage.dp)
    )
}