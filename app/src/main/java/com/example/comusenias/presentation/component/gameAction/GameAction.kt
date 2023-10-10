package com.example.comusenias.presentation.component.gameAction

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.ui.theme.size08
import com.example.comusenias.presentation.ui.theme.size30

@Composable
fun GameAction(
    imageSign: Int,
    letterSign: String = "",
    title: String,
    titleButton: String,
    clickButton: () -> Unit,
    enabledButton: Boolean = true,
    currentSteps: Int,
    navController: NavHostController,
    content: @Composable () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(size30.dp)
    ) {
        TopSectionGameAction(
            letterSign =letterSign,
            title = title,
            image = imageSign,
            currentStep = currentSteps,
            navController = navController
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(size08),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
        BottomSectionGameAction(
            titleButton = titleButton,
            enabled = enabledButton,
            clickButton = { clickButton() }
        )
    }
}


