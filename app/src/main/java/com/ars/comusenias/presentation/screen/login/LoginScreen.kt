package com.ars.comusenias.presentation.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ars.comusenias.R
import com.ars.comusenias.constants.TestTag
import com.ars.comusenias.presentation.component.defaults.app.AuthenticationContent
import com.ars.comusenias.presentation.component.defaults.app.AuthenticationFooterContent
import com.ars.comusenias.presentation.component.login.LoginForm
import com.ars.comusenias.presentation.component.login.ResponseStatusLogin
import com.ars.comusenias.presentation.component.login.ResponseStatusRol
import com.ars.comusenias.presentation.navigation.AppScreen
import com.ars.comusenias.presentation.ui.theme.DONT_HAVE_ACCOUNT
import com.ars.comusenias.presentation.ui.theme.REGISTER
import com.ars.comusenias.presentation.view_model.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavHostController,
    modifier: Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .testTag(TestTag.TAG_LOGIN_SCREEN),
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_login), // Reemplaza con tu VectorDrawable
            contentDescription = null, // Descripción accesible opcional
            modifier = Modifier.fillMaxSize(), // Ajusta el modificador según tu necesidad
            contentScale = ContentScale.Crop // Ajusta el escalado para adaptarse al fondo
        )

        AuthenticationContent(
            content = {
                LoginForm(
                    navController = navController,
                    viewModel = viewModel
                )
            },
            footer = {
                AuthenticationFooterContent(
                    textOne = DONT_HAVE_ACCOUNT,
                    textTwo = REGISTER,
                    onClickText = { navController.navigate(route = AppScreen.RegisterScreen.route) }
                )
            }
        )

        ResponseStatusLogin(
            navController = navController,
            viewModel = viewModel
        )

        ResponseStatusRol(
            viewModel = viewModel
        )

    }
}