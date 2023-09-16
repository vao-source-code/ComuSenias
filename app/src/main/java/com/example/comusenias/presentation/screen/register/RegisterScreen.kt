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
import com.example.comusenias.presentation.component.defaults.app.AuthenticationFooterContent
import com.example.comusenias.presentation.component.defaults.app.AuthenticationHeaderContent
import com.example.comusenias.presentation.component.register.RegisterForm
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.doYouAlreadyHaveAnAccount
import com.example.comusenias.presentation.ui.theme.enter
import com.example.comusenias.presentation.ui.theme.size30

@Composable
fun RegisterScreen(modifier: Modifier, navController: NavHostController) {
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
            RegisterForm(navController = navController)
            AuthenticationFooterContent(
                textOne = doYouAlreadyHaveAnAccount,
                textTwo = enter,
                onClickText = { navController.navigate(route = AppScreen.LoginScreen.route) }
            )
        }
    }
}