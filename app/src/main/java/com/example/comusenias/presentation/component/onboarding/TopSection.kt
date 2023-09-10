package com.example.comusenias.presentation.component.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.ui.theme.primaryColorApp

@Composable
@Preview(showBackground = true)
fun TopSection() {
    Box(
        modifier= Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ){

        IconButton(
            onClick = { },
            modifier=Modifier.align(Alignment.CenterStart)
        ) {
            Icon(Icons.Outlined.KeyboardArrowLeft,null)
        }

        TextButton(
            onClick = {},
            modifier=Modifier.align(Alignment.CenterEnd)
        ) {
            Text("Saltar",color= primaryColorApp)
        }

    }
}