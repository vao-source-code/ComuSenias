package com.example.comusenias.presentation.screen.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.component.register.choseYourProfile.ChoseYourProfileContent
import com.example.comusenias.presentation.component.defaults.app.AuthenticationHeaderContent
import com.example.comusenias.presentation.ui.theme.size100
import com.example.comusenias.presentation.ui.theme.size30

@Composable
fun ChoseYourProfileScreen(modifier: Modifier, navController: NavHostController) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = size30.dp, end = size30.dp, top = size30.dp),
            verticalArrangement = Arrangement.spacedBy(size100.dp)
        ) {
            AuthenticationHeaderContent()
            ChoseYourProfileContent(navController = navController)
        }
    }
}