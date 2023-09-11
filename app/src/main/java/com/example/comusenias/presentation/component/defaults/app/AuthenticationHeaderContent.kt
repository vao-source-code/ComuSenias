package com.example.comusenias.presentation.component.defaults.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.R
import com.example.comusenias.presentation.ui.theme.primaryColorApp
@Preview(showBackground = true)
@Composable
fun AuthenticationHeaderContent(){
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        HeaderImage()
        NameApp()
    }
}

@Composable
fun HeaderImage(){
    Image(
        painter = painterResource(id = R.drawable.lenguaje),
        contentDescription = "icon",
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .width(128.dp)
            .height(129.dp)
    )
}

@Composable
fun NameApp(){
    Text(
        text = "ComuSeñas",
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            color = primaryColorApp,
        )
    )
}