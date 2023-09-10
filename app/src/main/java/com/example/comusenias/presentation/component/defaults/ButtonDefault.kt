package com.example.comusenias.presentation.component.defaults

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.component.defaults.app.TextErrorDefault

@Composable
fun ButtonDefault(
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector?,
    onClick: () -> Unit,
    errorMsg: String = "",
    enabled : Boolean = true
) {


    Column() {
        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .testTag("ttt"),
            shape = RoundedCornerShape(10.dp),
            enabled = enabled,
            onClick = { onClick() }) {
            Text(text = text)
            Spacer(modifier = Modifier.width(10.dp))
            if (icon != null) {
                Icon(imageVector = icon, contentDescription = "Icons button")
            }
        }
        TextErrorDefault(errorMsg = errorMsg)

    }

}