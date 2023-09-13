package com.example.comusenias.presentation.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.component.defaults.app.AuthenticationFooterContent
import com.example.comusenias.presentation.component.defaults.app.AuthenticationHeaderContent
import com.example.comusenias.presentation.component.login.LoginForm
import com.example.comusenias.presentation.navigation.AppScreen

@Composable
fun LoginScreen(navController: NavHostController, modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            AuthenticationHeaderContent()
            LoginForm(navController = navController)
            AuthenticationFooterContent(
                text1 = "No tienes una cuenta",
                text2 = "Regístrate",
                onClickText = { navController.navigate(route = AppScreen.RegisterScreen.route) }
            )
        }
    }
}