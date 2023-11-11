package com.example.comusenias.presentation.component.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.component.defaults.app.ButtonApp
import com.example.comusenias.presentation.component.defaults.app.TextFieldApp
import com.example.comusenias.presentation.component.defaults.app.TextFieldAppPassword
import com.example.comusenias.presentation.navigation.AuthScreen
import com.example.comusenias.presentation.ui.theme.CONFIRM_PASS
import com.example.comusenias.presentation.ui.theme.EMAIL_TEXT
import com.example.comusenias.presentation.ui.theme.PASSWORD
import com.example.comusenias.presentation.ui.theme.REGISTER
import com.example.comusenias.presentation.ui.theme.SIZE10
import com.example.comusenias.presentation.ui.theme.SIZE2
import com.example.comusenias.presentation.view_model.RegisterViewModel

@Composable
fun RegisterForm(
    navController: NavHostController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val childFormScreen = AuthScreen.ChildFormScreen.route
    val specilaistFormScreen = AuthScreen.SpecialistFormScreen.route
    var route by remember { mutableStateOf(childFormScreen) }
    val currentRoute = remember { mutableStateOf(route) }


    Column(
        modifier = Modifier.fillMaxWidth(),
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
        TextFieldAppPassword(
            value = state.confirmPassword,
            onValueChange = { viewModel.onConfirmPasswordInput(it) },
            validateField = { viewModel.validateConfirmPassword() },
            label = CONFIRM_PASS,
            errorMsg = viewModel.errorConfirmPassword
        )
        SpecialistCheck { isCheckedValue ->
            currentRoute.value = if (isCheckedValue) specilaistFormScreen else childFormScreen
            viewModel.onSpecialistRoleInput(isCheckedValue)
        }
        Spacer(modifier = Modifier.height(SIZE10.dp))
        ButtonApp(
            titleButton = REGISTER,
            onClickButton = {
                viewModel.onRegister()
                navController.navigate(route = currentRoute.value)
            },
            enabledButton = viewModel.isRegisterEnabled
        )
    }
}
