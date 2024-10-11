package com.ars.comusenias.presentation.component.defaults.app

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
fun AuthenticationHeaderContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderImage()
    }
}

@Composable
fun HeaderImage() {
    Image(
        painter = painterResource(id = R.drawable.icon_login_screen),
        contentDescription = ICONAPP,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .width(SIZE250.dp)
            .height(SIZE250.dp)
    )
}