package com.example.comusenias.presentation.component.defaults.app

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.constants.TestTag
import com.example.comusenias.presentation.ui.theme.primaryColorApp

@Composable
fun ButtonApp(
    titleButton: String,
    onClickButton: () -> Unit = {},
    enabledButton: Boolean = true,
    icon: ImageVector? = null,
    modifier: Modifier = Modifier
) {
    Button(
        colors = ButtonDefaults.buttonColors(primaryColorApp),
        shape = RoundedCornerShape(10.dp),
        enabled = enabledButton,
        onClick = { onClickButton() },
        modifier = modifier
            .fillMaxWidth()
            .padding()
            .height(50.dp)
            .testTag(TestTag.TAG_BUTTON_APP)
    ) {
        Text(
            modifier = Modifier.testTag("titleButton"),
            color = Color.White,
            text = titleButton,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.width(10.dp))
        if (icon != null) {
            Icon(imageVector = icon, contentDescription = "Icons button")
        }
    }
}

@Preview
@Composable
fun PreviewButton() {
    ButtonApp(titleButton = "Iniciar sesión")
}