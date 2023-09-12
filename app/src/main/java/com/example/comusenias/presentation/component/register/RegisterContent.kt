package com.example.comusenias.presentation.component.register

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.comusenias.domain.models.Response
import com.example.comusenias.presentation.component.defaults.DefaultLoadingProgressIndicator
import com.example.comusenias.presentation.component.defaults.app.ButtonApp
import com.example.comusenias.presentation.component.defaults.app.TextFieldApp
import com.example.comusenias.presentation.component.defaults.app.TextFieldAppPassword
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.size40
import com.example.comusenias.presentation.view_model.RegisterViewModel

@Composable
fun RegisterContent(navController : NavHostController, viewModel: RegisterViewModel = hiltViewModel()) {
    val singFlow = viewModel.registerFlow.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        TextFieldApp(
            value = viewModel.userName.value,
            onValueChange = { viewModel.userName.value = it },
            label = "Nombre",
            keyboardType = KeyboardType.Text,
            icon = Icons.Default.Person,
            errorMsg = viewModel.errorUserName.value,
            validateField = {viewModel.validateUserName()}
        )
        TextFieldApp(
            value = viewModel.email.value,
            onValueChange = {viewModel.email.value = it },
            label = "Correo electrónico",
            keyboardType = KeyboardType.Email,
            icon = Icons.Default.Email,
            errorMsg = viewModel.errorEmail.value,
            validateField = {viewModel.validateEmail()}
        )
        TextFieldAppPassword(
            value = viewModel.password.value,
            onValueChange = { viewModel.password.value = it },
            label = "Contraseña",
            errorMsg = viewModel.errorPassword.value,
            validateField = {viewModel.validatePassword()}
        )
        TextFieldAppPassword(
            value = viewModel.confirmPassword.value,
            onValueChange = { it -> viewModel.confirmPassword.value = it },
            label = "Confirmar contraseña",
            errorMsg = viewModel.errorConfirmPassword.value,
            validateField = {viewModel.validateConfirmPassword()}
        )
        TermsAndConditionsContent()
        Spacer(modifier = Modifier.height(size40.dp))
        ButtonApp(
            titleButton = "Registrarse",
            onClickButton = { viewModel.onRegister() },
            enabledButton = viewModel.isRegisterEnabled
        )

        singFlow.value.let {
            when (it) {
                is Response.Loading -> {
                    DefaultLoadingProgressIndicator()
                }
                is Response.Success -> {
                    LaunchedEffect(Unit) {
                        //Elimino asi el total del historial de atras
                        navController.popBackStack(AppScreen.LoginScreen.route, inclusive = true)
//                    navController.navigate(route = AppScreen.ProfileScreen.route)
                    }
                }
                is Response.Error -> {
                    Toast.makeText(LocalContext.current, it.exception!!.message, Toast.LENGTH_SHORT)
                        .show()
                }
                else -> {}
            }
        }
    }
}