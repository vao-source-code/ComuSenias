package com.example.comusenias.presentation.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.component.defaults.app.AuthenticationFooterContent
import com.example.comusenias.presentation.component.defaults.app.AuthenticationHeaderContent
import com.example.comusenias.presentation.component.login.LoginForm
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.dontHaveAccount
import com.example.comusenias.presentation.ui.theme.register
import com.example.comusenias.presentation.ui.theme.size16
import com.example.comusenias.presentation.ui.theme.size20
import com.example.comusenias.presentation.ui.theme.size50

@Composable
fun LoginScreen(navController: NavHostController) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(
                start = size16.dp,
                top = size50.dp,
                end = size16.dp,
                bottom = size20.dp
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            AuthenticationHeaderContent()
            LoginForm(navController = navController)
            AuthenticationFooterContent(
                textOne = dontHaveAccount,
                textTwo = register,
                onClickText = { navController.navigate(route = AppScreen.RegisterScreen.route) }
            )
        }
    }
}