package com.example.comusenias.presentation.component.defaults


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.presentation.ui.theme.primaryColorApp

@Composable
fun ButtonApp(
    text: String,
    onClick: () -> Unit = {},
    enabled: Boolean = true
) {
    Button(
        shape = RoundedCornerShape(10.dp),
        enabled = enabled,
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(primaryColorApp, shape = RoundedCornerShape(10.dp))
    ) {
        Text(
            color = Color.White,
            text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
