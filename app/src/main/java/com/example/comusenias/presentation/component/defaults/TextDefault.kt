package com.example.comusenias.presentation.component.defaults

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.presentation.ui.theme.primaryColorApp
import com.example.comusenias.presentation.ui.theme.size20
import com.example.comusenias.presentation.ui.theme.size30
import com.example.comusenias.presentation.ui.theme.textApp

@Composable
fun GetTextDefault(
    text: String,
    textSize: Int = 10,
    paddingTop: Int = 10,
    color: Color = Color.Black,
    fontWeight: FontWeight = FontWeight.Normal,
) {
    Text(
        text = text,
        Modifier.padding(top = paddingTop.dp),
        fontSize = textSize.sp,
        color = color,
        fontWeight = fontWeight
    )
}

@Composable
fun GetTextSplash() {
    Text(
        text = textApp,
        Modifier
            .padding(top = size20.dp)
            .testTag(textApp),
        fontSize = size30.sp,
        color = primaryColorApp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 3.sp
    )
}