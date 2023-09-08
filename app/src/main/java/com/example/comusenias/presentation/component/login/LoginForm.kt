package com.example.comusenias.presentation.component.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.CircularProgressIndicator
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
import com.example.comusenias.presentation.component.defaults.app.ButtonApp
import com.example.comusenias.presentation.component.defaults.app.GoogleSignInButton
import com.example.comusenias.presentation.component.defaults.app.TextFieldApp
import com.example.comusenias.presentation.component.defaults.app.TextFieldAppPassword
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.view_model.LoginViewModel

@Composable
fun LoginForm(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val loginFlow = viewModel.loginFlow.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(50.dp)
    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            TextFieldApp(
                value = viewModel.email.value,
                onValueChange = { viewModel.email.value = it },
                validateField = { viewModel.validateEmail() },
                label = "Correo electrónico",
                keyboardType = KeyboardType.Email,
                icon = Icons.Default.Email,
                errorMsg = viewModel.errorEmail.value
            )
            TextFieldAppPassword(
                value = viewModel.password.value,
                onValueChange = { viewModel.password.value = it },
                validateField = { viewModel.validatePassword() },
                label = "Contraseña"
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




    loginFlow.value.let { state ->
        when (state) {
            //Mostrar que se esta realizando la peticion
            Response.Loading -> {
                Box(contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()

                }
            }

            is Response.Success -> {
                LaunchedEffect(Unit) {
                    navController.navigate(route = AppScreen.ProfileScreen.route){
                        popUpTo(AppScreen.LoginScreen.route) {
                            inclusive = true
                        }
                    }

                }
                Toast.makeText(LocalContext.current, "Login Success", Toast.LENGTH_SHORT).show()
            }

            is Response.Error -> {
                Toast.makeText(
                    LocalContext.current,
                    state.exception?.message + "Login Error",
                    Toast.LENGTH_SHORT
                ).show()

            }

            else -> {}
        }
    }
}



