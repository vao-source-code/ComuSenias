package com.example.comusenias.presentation.component.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.comusenias.domain.models.Response
import com.example.comusenias.presentation.component.defaults.DefaultLoadingProgressIndicator
import com.example.comusenias.presentation.component.defaults.app.ButtonApp
import com.example.comusenias.presentation.component.defaults.app.GoogleSignInButton
import com.example.comusenias.presentation.component.defaults.app.TextFieldApp
import com.example.comusenias.presentation.component.defaults.app.TextFieldAppPassword
import com.example.comusenias.presentation.component.defaults.app.showToast
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.emailText
import com.example.comusenias.presentation.ui.theme.logIn
import com.example.comusenias.presentation.ui.theme.loginError
import com.example.comusenias.presentation.ui.theme.loginSuccess
import com.example.comusenias.presentation.ui.theme.password
import com.example.comusenias.presentation.ui.theme.size10
import com.example.comusenias.presentation.ui.theme.size2
import com.example.comusenias.presentation.ui.theme.size50
import com.example.comusenias.presentation.view_model.LoginViewModel

@Composable
fun LoginForm(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val loginFlow = viewModel.loginFlow.collectAsState()
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(size50.dp)
    ) {
        loginFlow.value.let { state ->
            when (state) {
                Response.Loading -> {
                    Box(
                        contentAlignment = Alignment.Center,
                    ) {
                        DefaultLoadingProgressIndicator()
                    }
                }

                is Response.Success -> {
                    LaunchedEffect(Unit) {
                        navController.navigate(route = AppScreen.ProfileScreen.route) {
                            popUpTo(AppScreen.LoginScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                    showToast(context, text = loginSuccess, Toast.LENGTH_SHORT)
                }

                is Response.Error -> {
                    showToast(
                        context, text = state.exception?.message + loginError, Toast.LENGTH_SHORT
                    )
                }

                else -> {}
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(size2.dp)
        ) {
            TextFieldApp(
                value = viewModel.email.value,
                onValueChange = { viewModel.email.value = it },
                validateField = { viewModel.validateEmail() },
                label = emailText,
                keyboardType = KeyboardType.Email,
                icon = Icons.Default.Email,
                errorMsg = viewModel.errorEmail.value
            )
            TextFieldAppPassword(
                value = viewModel.password.value,
                onValueChange = { viewModel.password.value = it },
                validateField = { viewModel.validatePassword() },
                label = password,
                errorMsg = viewModel.errorPassword.value
            )
            RememberMeAndForgetMyPass()
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(size10.dp)
        ) {
            ButtonApp(
                titleButton = logIn,
                onClickButton = { viewModel.login() },
                enabledButton = viewModel.isLoginEnabled
            )
            LineDivisorLogin()
            GoogleSignInButton()
        }
    }
}





