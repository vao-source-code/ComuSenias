package com.ars.comusenias.presentation.component.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ars.comusenias.presentation.component.defaults.app.ButtonApp
import com.ars.comusenias.presentation.component.defaults.app.InputTextField
import com.ars.comusenias.presentation.component.defaults.app.TextFieldAppPassword
import com.ars.comusenias.presentation.ui.theme.EMAIL_TEXT
import com.ars.comusenias.presentation.ui.theme.LOGIN
import com.ars.comusenias.presentation.ui.theme.PASSWORD
import com.ars.comusenias.presentation.ui.theme.SIZE10
import com.ars.comusenias.presentation.ui.theme.SIZE2
import com.ars.comusenias.presentation.view_model.LoginViewModel

@Composable
fun LoginForm(
    viewModel: LoginViewModel,
    navController: NavHostController
) {
    val state = viewModel.state


    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SIZE2.dp)
    ) {
        InputTextField(
            value = state.email,
            onValueChange = { viewModel.onEmailInput(it) },
            validateField = { viewModel.validateEmail() },
            label = EMAIL_TEXT,
            keyboardType = KeyboardType.Email,
            errorMsg = viewModel.errorEmail,
            icon = null
        )
        TextFieldAppPassword(
            value = state.password,
            onValueChange = { viewModel.onPasswordInput(it) },
            validateField = { viewModel.validatePassword() },
            label = PASSWORD,
            errorMsg = viewModel.errorPassword
        )
        RememberMeAndForgetMyPass(viewModel = viewModel, navController = navController)
        Spacer(modifier = Modifier.height(SIZE10.dp))
        ButtonApp(
            titleButton = LOGIN,
            enabledButton = viewModel.isLoginEnabled,
            onClickButton = { viewModel.login() },
        )
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewLoginForm() {
    // Simulación del NavHostController
    val navController = rememberNavController()

    // Simulación del ViewModel con datos de prueba
    val mockViewModel : LoginViewModel = hiltViewModel()

    // Llamada al Composable a probar
    LoginForm(
        viewModel = mockViewModel,
        navController = navController
    )
}
