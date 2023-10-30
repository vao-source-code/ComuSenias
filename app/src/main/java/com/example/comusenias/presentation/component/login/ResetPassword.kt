package com.example.comusenias.presentation.component.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.component.defaults.app.AuthenticationHeaderContent
import com.example.comusenias.presentation.component.defaults.app.ButtonApp
import com.example.comusenias.presentation.component.defaults.app.TextFieldApp
import com.example.comusenias.presentation.ui.theme.EMAIL_TEXT
import com.example.comusenias.presentation.ui.theme.SIZE20
import com.example.comusenias.presentation.ui.theme.SIZE30
import com.example.comusenias.presentation.ui.theme.SIZE90
import com.example.comusenias.presentation.view_model.LoginViewModel

@Composable
fun ResetPasswordScreen(
    navController: NavHostController,
    modifier: Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {

    ResponsePasswordReset(
        response = viewModel.loginReset,
        navController = navController,
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = SIZE30.dp, end = SIZE30.dp, top = SIZE90.dp, bottom = SIZE20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        AuthenticationHeaderContent()
        TextFieldApp(
            value = viewModel.state.email,
            onValueChange = { viewModel.onEmailInput(it) },
            validateField = { viewModel.validateEmail() },
            label = EMAIL_TEXT,
            keyboardType = KeyboardType.Email,
            errorMsg = viewModel.errorEmail,
            icon = Icons.Default.Email
        )
        ButtonApp(
            titleButton = "Resetear Contraseña",
            onClickButton = { viewModel.resetPassword() })
    }
}