package com.example.comusenias.presentation.screen.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.component.defaults.app.AuthenticationContent
import com.example.comusenias.presentation.component.defaults.app.AuthenticationFooterContent
import com.example.comusenias.presentation.component.defaults.app.ButtonApp
import com.example.comusenias.presentation.component.register.RegisterForm
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.DO_YOU_ALREADY_HAVE_AN_ACCOUNT
import com.example.comusenias.presentation.ui.theme.ENTER
import com.example.comusenias.presentation.ui.theme.REGISTER
import com.example.comusenias.presentation.ui.theme.size10

@Composable
fun RegisterScreen(modifier: Modifier, navController: NavHostController) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        AuthenticationContent(
            content = { RegisterForm(navController = navController) },
            footer = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(size10.dp)
                ) {
                    ButtonApp(
                        titleButton = REGISTER,
                        onClickButton = {  navController.navigate(route = AppScreen.EspecialistFormScreen.route) },
                        enabledButton = true
                    )
                    AuthenticationFooterContent(
                        textOne = DO_YOU_ALREADY_HAVE_AN_ACCOUNT,
                        textTwo = ENTER,
                        onClickText = { navController.navigate(route = AppScreen.LoginScreen.route) }
                    )
                }
            }
        )
    }
}
