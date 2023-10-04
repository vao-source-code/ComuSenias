package com.example.comusenias.presentation.component.gameAction

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.constants.TestTag
import com.example.comusenias.presentation.ui.theme.primaryColorApp

@Composable
fun BottomSectionGameAction(
    titleButton: String,
    buttonVisible: Boolean,
    onNextClicked: () -> Unit
    ) {
    val isButtonVisible by remember { mutableStateOf(buttonVisible) }

    if (isButtonVisible) {
        Button(
            colors = ButtonDefaults.buttonColors(primaryColorApp),
            shape = RoundedCornerShape(10.dp),
            onClick = onNextClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding()
                .height(50.dp)
                .testTag(TestTag.TAG_BUTTON_APP)
        ) {
            Text(
                color = Color.White,
                text = titleButton,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
