package com.ars.comusenias.presentation.component.register

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ars.comusenias.domain.library.LibraryDebugger
import com.ars.comusenias.presentation.component.defaults.app.ButtonApp
import com.ars.comusenias.presentation.component.defaults.app.InputTextField
import com.ars.comusenias.presentation.component.defaults.app.TextFieldAppPassword
import com.ars.comusenias.presentation.component.defaults.app.TextViewField
import com.ars.comusenias.presentation.navigation.AuthScreen
import com.ars.comusenias.presentation.ui.theme.COMPLETE_NAME
import com.ars.comusenias.presentation.ui.theme.CONFIRM_PASS
import com.ars.comusenias.presentation.ui.theme.CREATE_ACCOUNT
import com.ars.comusenias.presentation.ui.theme.EMAIL_TEXT
import com.ars.comusenias.presentation.ui.theme.PASSWORD
import com.ars.comusenias.presentation.ui.theme.REGISTER
import com.ars.comusenias.presentation.ui.theme.SIZE10
import com.ars.comusenias.presentation.ui.theme.SIZE2
import com.ars.comusenias.presentation.ui.theme.SIZE28
import com.ars.comusenias.presentation.ui.theme.TEXT_TITLE_RESET_PASSWORD
import com.ars.comusenias.presentation.ui.theme.backgroundColorTextField
import com.ars.comusenias.presentation.ui.theme.primaryColorApp
import com.ars.comusenias.presentation.view_model.RegisterViewModel

@Composable
fun RegisterForm(
    navController: NavHostController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    var state = viewModel.state
    val childFormScreen = AuthScreen.ChildFormScreen.route
    val specilaistFormScreen = AuthScreen.SpecialistFormScreen.route
    var route by remember { mutableStateOf(childFormScreen) }
    val currentRoute = remember { mutableStateOf(route) }
    var boolean = false
    val openLink =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {}


    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SIZE2.dp)
    ) {

        if(LibraryDebugger.appIsDebuggable() && boolean){

            viewModel.onEmailInput("email1@gmail.com")
            viewModel.onPasswordInput("Password2018")
            viewModel.onConfirmPasswordInput("Password2018")
            viewModel.onNameInputChanged("name")

            viewModel.isRegisterEnabled = true
        }


        TextViewField(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            label = CREATE_ACCOUNT,
            color = primaryColorApp,
            fontSize = SIZE28,
            fontWeight = FontWeight.Bold,
            textAlignment = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(SIZE10.dp))

        InputTextField(
            value = state.name,
            onValueChange = { viewModel.onNameInputChanged(it) },
            validateField = { viewModel.validateName() },
            label = COMPLETE_NAME,
            errorMsg = viewModel.errorName,
            icon =null
        )
        Spacer(modifier = Modifier.height(SIZE10.dp))


        InputTextField(
            value = state.email,
            onValueChange = { viewModel.onEmailInput(it) },
            validateField = { viewModel.validateEmail() },
            label = EMAIL_TEXT,
            keyboardType = KeyboardType.Email,
            errorMsg = viewModel.errorEmail,
            icon = Icons.Default.Email
        )
        Spacer(modifier = Modifier.height(SIZE10.dp))

        TextFieldAppPassword(
            value = state.password,
            onValueChange = { viewModel.onPasswordInput(it) },
            validateField = { viewModel.validatePassword() },
            label = PASSWORD,
            errorMsg = viewModel.errorPassword
        )
        Spacer(modifier = Modifier.height(SIZE10.dp))

        TextFieldAppPassword(
            value = state.confirmPassword,
            onValueChange = { viewModel.onConfirmPasswordInput(it) },
            validateField = { viewModel.validateConfirmPassword() },
            label = CONFIRM_PASS,
            errorMsg = viewModel.errorConfirmPassword
        )
        if(LibraryDebugger.appIsDebuggable()) {
            SpecialistCheck { isCheckedValue ->
                currentRoute.value = if (isCheckedValue) specilaistFormScreen else childFormScreen
                viewModel.onSpecialistRoleInput(isCheckedValue)
            }
        }

        Spacer(modifier = Modifier.height(SIZE10.dp))

        TermsAndConditions(
            onClickTerms = { viewModel.onClickTerms(openLink) },
            onClickConditions = { viewModel.onClickConditions(openLink) },
            onCheckChange = {
                viewModel.onCheckTermsAndConditions(it)
            })

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
