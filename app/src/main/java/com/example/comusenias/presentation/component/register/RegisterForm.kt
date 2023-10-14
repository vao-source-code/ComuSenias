package com.example.comusenias.presentation.component.register

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
import com.example.comusenias.presentation.component.defaults.app.TextFieldApp
import com.example.comusenias.presentation.component.defaults.app.TextFieldAppPassword
import com.example.comusenias.presentation.component.login.ResponseStatusLogin
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.PASSWORD
import com.example.comusenias.presentation.ui.theme.REGISTER
import com.example.comusenias.presentation.ui.theme.SIZE10
import com.example.comusenias.presentation.ui.theme.SIZE2
import com.example.comusenias.presentation.ui.theme.SIZE50
import com.example.comusenias.presentation.ui.theme.confirmPassword
import com.example.comusenias.presentation.ui.theme.emailText
import com.example.comusenias.presentation.view_model.RegisterViewModel

@Composable
fun RegisterForm(
    navController : NavHostController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val state = viewModel.state
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SIZE50.dp)
    ) {

        ResponseStatusLogin(navController =navController)

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(SIZE2.dp)
        ) {
            TextFieldApp(
                value = state.email,
                onValueChange = {viewModel.onEmailInput(it) },
                validateField = { viewModel.validateEmail() },
                label = emailText,
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
            TextFieldAppPassword(
                value = state.confirmPassword,
                onValueChange = { viewModel.onConfirmPasswordInput(it) },
                validateField = { viewModel.validatePassword() },
                label = confirmPassword,
                errorMsg = viewModel.errorConfirmPassword
            )
            TermsAndConditions()
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(SIZE10.dp)
        ) {
            ButtonApp(
                titleButton = REGISTER,
                onClickButton = {  navController.navigate(route = AppScreen.ChoseYourProfileScreen.route) },
                enabledButton = true
            )
        }
    }
}