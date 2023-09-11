package com.example.comusenias.presentation.component.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.component.defaults.app.ButtonApp
import com.example.comusenias.presentation.component.defaults.app.GoogleSignInButton
import com.example.comusenias.presentation.component.defaults.app.TextFieldApp
import com.example.comusenias.presentation.component.defaults.app.TextFieldAppPassword
import com.example.comusenias.presentation.view_model.LoginViewModel

@Composable
fun LoginForm(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(50.dp)
    ) {

        ResponseStatusLogin(navController =navController)

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            TextFieldApp(
                value = state.email,
                onValueChange = {viewModel.onEmailInput(it) },
                validateField = { viewModel.validateEmail() },
                label = "Correo electrónico",
                keyboardType = KeyboardType.Email,
                icon = Icons.Default.Email,
                errorMsg = viewModel.errorEmail
            )
            TextFieldAppPassword(
                value = state.password,
                onValueChange = { viewModel.onPasswordInput(it) },
                validateField = { viewModel.validatePassword() },
                label = "Contraseña",
                errorMsg = viewModel.errorPassword
            )
            RememberMeAndForgetMyPass()
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ButtonApp(
                titleButton = "Iniciar sesión",
                onClickButton = { viewModel.login() },
                enabledButton = viewModel.isLoginEnabled
            )
            LineDivisorLogin()
            GoogleSignInButton()
        }


    }
}





