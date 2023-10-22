package com.example.comusenias.presentation.component.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.component.defaults.app.ButtonApp
import com.example.comusenias.presentation.component.defaults.app.TextFieldApp
import com.example.comusenias.presentation.component.defaults.app.TextFieldAppPassword
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.EMAIL_TEXT
import com.example.comusenias.presentation.ui.theme.LOGIN
import com.example.comusenias.presentation.ui.theme.PASSWORD
import com.example.comusenias.presentation.ui.theme.SIZE2
import com.example.comusenias.presentation.ui.theme.SIZE10
import com.example.comusenias.presentation.view_model.LoginViewModel

@Composable
fun LoginForm(
    viewModel: LoginViewModel,
    navController: NavHostController
) {
    val state = viewModel.state
    ResponseStatus(
        navController = navController,
        response = viewModel.loginResponse
        )

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SIZE2.dp)
    ) {
        TextFieldApp(
            value = state.email,
            onValueChange = { viewModel.onEmailInput(it) },
            validateField = { viewModel.validateEmail() },
            label = EMAIL_TEXT,
            keyboardType = KeyboardType.Email,
            errorMsg = viewModel.errorEmail,
            icon = Icons.Default.Email
        )
        TextFieldAppPassword(
            value = state.password,
            onValueChange = { viewModel.onPasswordInput(it) },
            validateField = { viewModel.validatePassword() },
            label = PASSWORD,
            errorMsg = viewModel.errorPassword
        )
        RememberMeAndForgetMyPass()
        Spacer(modifier = Modifier.height(SIZE10.dp))
        ButtonApp(
            titleButton = LOGIN,
            enabledButton = viewModel.isLoginEnabled,
            onClickButton = { navController.navigate(route = AppScreen.HomeScreen.route) },
        )
    }
}