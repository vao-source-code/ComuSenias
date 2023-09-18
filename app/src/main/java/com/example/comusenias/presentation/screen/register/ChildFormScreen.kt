package com.example.comusenias.presentation.screen.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.component.defaults.app.AuthenticationHeaderContent
import com.example.comusenias.presentation.component.defaults.app.ButtonApp
import com.example.comusenias.presentation.component.register.childForm.ChildFormContent
import com.example.comusenias.presentation.ui.theme.CONTINUE
import com.example.comusenias.presentation.ui.theme.size30
@Composable
fun ChildFormScreen(modifier: Modifier, navController: NavHostController) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = size30.dp, end = size30.dp, top = size30.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            AuthenticationHeaderContent()
            ChildFormContent()
            ButtonApp(
                titleButton = CONTINUE,
                onClickButton = { }
            )
        }
    }
}