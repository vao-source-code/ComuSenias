package com.example.comusenias.presentation.component.defaults.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.R

@Preview(showBackground = true)
@Composable
fun GoogleSignInButton() {
    Button(
        onClick = {},
        modifier = Modifier
            .shadow(elevation = 3.dp, shape = RoundedCornerShape(10.dp),spotColor = Color(0x33000000), ambientColor = Color(0x33000000))
            .shadow(elevation = 3.dp, shape = RoundedCornerShape(10.dp), spotColor = Color(0x21000000), ambientColor = Color(0x21000000))
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .padding(start = 19.dp, end = 20.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            Color.White
        )
    ) {
        Image(
            modifier = Modifier
                .width(24.dp)
                .height(24.dp),
            contentScale = ContentScale.Fit,
            painter = painterResource(id = R.drawable.buscar),
            contentDescription = "sign in with Google"
        )
        Spacer(modifier = Modifier.width(24.dp))
        Text(
            text = "Continue with Google",
            style = TextStyle(
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xA3000000),
            )
        )
    }
}