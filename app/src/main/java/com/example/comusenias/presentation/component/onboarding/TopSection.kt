package com.example.comusenias.presentation.component.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.comusenias.R
import com.example.comusenias.presentation.ui.theme.primaryColorApp

@Composable
fun TopSection(navController: NavController) {
    Box(
        modifier= Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ){
        TextButton(
            onClick = {},
            modifier=Modifier.align(Alignment.CenterEnd)
        ) {
            Text(
                text = stringResource(R.string.titleSkipButton),
                color= primaryColorApp
            )
        }
    }
}

