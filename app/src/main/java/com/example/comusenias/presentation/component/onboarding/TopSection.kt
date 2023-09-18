package com.example.comusenias.presentation.component.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.comusenias.presentation.ui.theme.SKIP
import com.example.comusenias.presentation.ui.theme.primaryColorApp
import com.example.comusenias.presentation.ui.theme.size12

@Composable
fun TopSection(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(size12.dp)
    ) {
        TextButton(
            onClick = {},
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Text(
                text = SKIP,
                color = primaryColorApp
            )
        }
    }
}