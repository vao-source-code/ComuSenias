package com.example.comusenias.presentation.component.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.presentation.ui.theme.blackColorApp

@Preview(showBackground = true)
@Composable
fun LineDivisorLogin() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
        ) {
        Divider(
            modifier = Modifier.width(48.dp),
            color = blackColorApp,
            thickness = 1.dp
        )
        Text(
            text = " o inicie sesión con ",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = blackColorApp
        )
        Divider(
            modifier = Modifier.width(48.dp),
            color = blackColorApp,
            thickness = 1.dp
        )

    }
}