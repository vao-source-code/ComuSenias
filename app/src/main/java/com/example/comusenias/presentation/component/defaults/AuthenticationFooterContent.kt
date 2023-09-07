package com.example.comusenias.presentation.component.defaults

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.presentation.ui.theme.blackColorApp
import com.example.comusenias.presentation.ui.theme.primaryColorApp

@Composable
fun AuthenticationFooterContent(
    text1: String = "¿No tienes una cuenta?",
    text2: String = "Regístrate",
    onClickText: () -> Unit = {}
){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = text1,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = blackColorApp
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                modifier = Modifier.testTag("text click action")
                    .clickable { onClickText() },
                text = text2,
                color = primaryColorApp,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
}

@Preview(showBackground = true)
@Composable
fun PreviewAuthenticationFooterContent() {
        AuthenticationFooterContent()
}
