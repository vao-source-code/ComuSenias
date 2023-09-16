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
import com.example.comusenias.presentation.ui.theme.size30

@Composable
fun LoginScreen(navController: NavHostController, modifier: Modifier) {
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
            LoginForm(navController = navController)
            AuthenticationFooterContent(
                textOne = dontHaveAccount,
                textTwo = register,
                onClickText = { navController.navigate(route = AppScreen.RegisterScreen.route) }
            )
        }
    }
}