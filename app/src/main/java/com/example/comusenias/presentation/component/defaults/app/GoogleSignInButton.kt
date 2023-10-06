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
import com.example.comusenias.presentation.ui.theme.signInWithGoogle
import com.example.comusenias.presentation.ui.theme.size10
import com.example.comusenias.presentation.ui.theme.size17
import com.example.comusenias.presentation.ui.theme.size19
import com.example.comusenias.presentation.ui.theme.size20
import com.example.comusenias.presentation.ui.theme.size24
import com.example.comusenias.presentation.ui.theme.size3
import com.example.comusenias.presentation.ui.theme.SIZE50

@Preview(showBackground = true)
@Composable
fun GoogleSignInButton() {
    Button(
        onClick = {
        },
        modifier = Modifier
            .shadow(
                elevation = size3.dp,
                shape = RoundedCornerShape(size10.dp),
                spotColor = Color(0x33000000),
                ambientColor = Color(0x33000000)
            )
            .shadow(
                elevation = size3.dp,
                shape = RoundedCornerShape(size10.dp),
                spotColor = Color(0x21000000),
                ambientColor = Color(0x21000000)
            )
            .fillMaxWidth()
            .height(SIZE50.dp)
            .background(Color.White, shape = RoundedCornerShape(size10.dp))
            .padding(start = size19.dp, end = size20.dp),
        shape = RoundedCornerShape(size10.dp),
        colors = ButtonDefaults.buttonColors(
            Color.White
        )
    ) {
        Image(
            modifier = Modifier
                .width(size24.dp)
                .height(size24.dp),
            contentScale = ContentScale.Fit,
            painter = painterResource(id = R.drawable.buscar),
            contentDescription = signInWithGoogle
        )
        Spacer(modifier = Modifier.width(size24.dp))
        Text(
            text = signInWithGoogle,
            style = TextStyle(
                fontSize = size17.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xA3000000),
            )
        )
    }
}