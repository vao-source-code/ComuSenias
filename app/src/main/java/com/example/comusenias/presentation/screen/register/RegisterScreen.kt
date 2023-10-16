package com.example.comusenias.presentation.screen.register

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.component.defaults.app.AuthenticationContent
import com.example.comusenias.presentation.component.defaults.app.AuthenticationFooterContent
import com.example.comusenias.presentation.component.register.RegisterForm
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.DO_YOU_ALREADY_HAVE_AN_ACCOUNT
import com.example.comusenias.presentation.ui.theme.ENTER

@Composable
fun RegisterScreen(modifier: Modifier, navController: NavHostController) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        AuthenticationContent(
            content = { RegisterForm(navController = navController) },
            footer = {
                AuthenticationFooterContent(
                    textOne = DO_YOU_ALREADY_HAVE_AN_ACCOUNT,
                    textTwo = ENTER,
                    onClickText = { navController.navigate(route = AppScreen.LoginScreen.route) }
                )
            }
        )
    }
}
