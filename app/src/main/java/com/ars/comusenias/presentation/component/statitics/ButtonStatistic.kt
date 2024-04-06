package com.ars.comusenias.presentation.component.statitics

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ars.comusenias.presentation.ui.theme.SIZE10
import com.ars.comusenias.presentation.ui.theme.SIZE14
import com.ars.comusenias.presentation.ui.theme.SIZE50

@Composable
fun ButtonStatistic(
    titleButton: String,
    color: Color,
    isButtonPressed: Boolean = false,
    onClickButton: () -> Unit = {}
) {

    Button(
        colors = ButtonDefaults.buttonColors(if (isButtonPressed) color else White),
        shape = RoundedCornerShape(SIZE10.dp),
        onClick = { onClickButton() },
        modifier = Modifier
            .height(SIZE50.dp)
            .border(
                width = 1.dp,
                color = if (isButtonPressed) White else color,
                shape = RoundedCornerShape(SIZE10.dp)
            )
    ) {
        Text(
            color = if (isButtonPressed) White else color,
            text = titleButton,
            fontSize = SIZE14.sp,
            fontWeight = SemiBold
        )
    }
}
