package com.ars.comusenias.presentation.component.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ars.comusenias.R
import com.ars.comusenias.presentation.component.defaults.app.ButtonApp
import com.ars.comusenias.presentation.component.defaults.app.InputTextField
import com.ars.comusenias.presentation.component.defaults.app.TextViewField
import com.ars.comusenias.presentation.component.register.RegisterForm
import com.ars.comusenias.presentation.navigation.AppScreen
import com.ars.comusenias.presentation.ui.theme.BUTTON_RESET_PASSWORD
import com.ars.comusenias.presentation.ui.theme.DO_YOU_ALREADY_HAVE_AN_ACCOUNT
import com.ars.comusenias.presentation.ui.theme.EMAIL_TEXT
import com.ars.comusenias.presentation.ui.theme.ENTER
import com.ars.comusenias.presentation.ui.theme.SIZE12
import com.ars.comusenias.presentation.ui.theme.SIZE2
import com.ars.comusenias.presentation.ui.theme.SIZE20
import com.ars.comusenias.presentation.ui.theme.SIZE28
import com.ars.comusenias.presentation.ui.theme.SIZE30
import com.ars.comusenias.presentation.ui.theme.SIZE90
import com.ars.comusenias.presentation.ui.theme.TEXT_RESET_PASSWORD
import com.ars.comusenias.presentation.ui.theme.TEXT_TITLE_RESET_PASSWORD
import com.ars.comusenias.presentation.ui.theme.primaryColorApp
import com.ars.comusenias.presentation.view_model.LoginViewModel

@Composable
fun ResetPasswordScreen(
    navController: NavHostController,
    modifier: Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_login), // Reemplaza con tu VectorDrawable
            contentDescription = null, // Descripción accesible opcional
            modifier = modifier.fillMaxSize(), // Ajusta el modificador según tu necesidad
            contentScale = ContentScale.Crop // Ajusta el escalado para adaptarse al fondo
        )

        AuthenticationContent(
            content = { ResetPasswordForm(viewModel, modifier) },
            footer = {
                AuthenticationFooterContent(
                    textOne = DO_YOU_ALREADY_HAVE_AN_ACCOUNT,
                    textTwo = ENTER,
                    onClickText = { navController.navigate(route = AppScreen.LoginScreen.route) }
                )
            },
            sizeImage = SIZE90,

        )
    }

}

@Composable
fun ResetPasswordForm(
    viewModel: LoginViewModel,
    modifier: Modifier
) {

    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {

        TextViewField(
            modifier = modifier,
            label = TEXT_TITLE_RESET_PASSWORD,
            color = primaryColorApp,
            fontSize = SIZE28,
            fontWeight = FontWeight.Bold,
            textAlignment = TextAlign.Center
        )
        Spacer(modifier = Modifier.padding(5.dp))


        TextViewField(
            modifier = modifier,
            label = TEXT_RESET_PASSWORD,
            color = primaryColorApp,
            fontSize = SIZE12,
            textAlignment = TextAlign.Center
        )

        Spacer(modifier = Modifier.padding(20.dp))

        InputTextField(
            value = viewModel.state.email,
            onValueChange = { viewModel.onEmailInput(it) },
            validateField = { viewModel.validateEmail() },
            label = EMAIL_TEXT,
            keyboardType = KeyboardType.Email,
            errorMsg = viewModel.errorEmail,
            icon = null
        )

        Spacer(modifier = Modifier.padding(20.dp))

        ButtonApp(
            titleButton = BUTTON_RESET_PASSWORD,
            onClickButton = { viewModel.resetPassword() })

        ResponsePasswordReset(
            response = viewModel.loginReset,
        )

    }
}