package com.example.comusenias.presentation.component.defaults

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.comusenias.R

@Preview(showBackground = false)
@Composable
fun GoogleSignInButton() {
    Button(
        onClick = {
        },
        modifier = Modifier
            .shadow(elevation = 3.dp, spotColor = Color(0x2B000000), ambientColor = Color(0x2B000000))
            .shadow(elevation = 3.dp, spotColor = Color(0x15000000), ambientColor = Color(0x15000000))
            .fillMaxWidth()
            .height(56.dp)
            .padding(start = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            Color.White
        )
    ) {
        Image(
            modifier = Modifier
                .height(24.dp)
                .width(24.dp),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.google_logo),
            contentDescription = ""
        )
        Text(
            color = Color.Black,
            text = "Sign in with Google",
            modifier = Modifier.padding(6.dp))
    }
}