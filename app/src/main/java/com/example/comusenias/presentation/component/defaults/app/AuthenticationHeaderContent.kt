package com.example.comusenias.presentation.component.defaults.app

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
import com.example.comusenias.R
import com.example.comusenias.presentation.ui.theme.iconApp
import com.example.comusenias.presentation.ui.theme.size130

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
        painter = painterResource(id = R.drawable.comu_senias_with_text),
        contentDescription = iconApp,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .width(size130.dp)
            .height(size130.dp)
    )
}