package com.ars.comusenias.presentation.component.defaults.app

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ars.comusenias.constants.TestTag
import com.ars.comusenias.presentation.ui.theme.primaryColorApp


@Composable
fun ButtonAppIcon(
    titleButton: String,
    enabledButton: Boolean = true,
    modifier: Modifier = Modifier,
    onClickButton: () -> Unit = {},
    icon: ImageVector? = null // Agregar parámetro de icono opcional
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
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 8.dp)
                    .testTag("buttonIcon"),
                tint = Color.White
            )
        }
        Text(
            modifier = Modifier.testTag("titleButton"),
            color = Color.White,
            text = titleButton,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
