package com.example.comusenias.presentation.component.defaults

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.presentation.ui.theme.primaryColorApp
@Composable
fun ButtonApp(
    titleButton: String,
    onClickButton: () -> Unit = {},
    enabledButton: Boolean = true
) {
    Button(
        colors = ButtonDefaults.buttonColors(primaryColorApp),
        shape = RoundedCornerShape(10.dp),
        enabled = enabledButton,
        onClick = { onClickButton },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Text(
            color = Color.White,
            text = titleButton,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview
@Composable
fun PreviewButton(){
    ButtonApp(titleButton = "Iniciar sesión")
}