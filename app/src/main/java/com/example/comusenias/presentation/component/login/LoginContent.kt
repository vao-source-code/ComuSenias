package com.example.comusenias.presentation.component.login

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.comusenias.domain.models.Response
import com.example.comusenias.presentation.component.defaults.ButtonDefault
import com.example.comusenias.presentation.component.defaults.TextFieldDefault
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.ComuSeniasTheme
import com.example.comusenias.presentation.view_model.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContent(
    navController: NavHostController,
    modifier: Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier.padding(20.dp),
        horizontalAlignment = CenterHorizontally,
    ) {

        Text(
            text = "INICIAR SESION",
            fontSize = 30.sp,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.primary
        )
        CardFormLogin(navController = navController, viewModel = viewModel)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardFormLogin(navController: NavHostController, viewModel: LoginViewModel) {

    val loginFlow = viewModel.loginFlow.collectAsState()
    //HARDCORD

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
    ) {

        Column(modifier = Modifier.padding(PaddingValues(10.dp))) {
            Text(
                modifier = Modifier.padding(top = 25.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                text = "Login",
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Ingrese los datos para iniciar sesión",
                fontSize = 12.sp
            )

            TextFieldDefault(
                modifier = Modifier
                    .fillMaxWidth(),
                value = viewModel.email.value,
                onValueChange = { viewModel.email.value = it },
                validateField = { viewModel.validateEmail() },
                label = "Email",
                keyboardType = KeyboardType.Email,
                icon = Icons.Default.Email,
                errorMsg = viewModel.errorEmail.value
            )


            TextFieldDefault(
                modifier = Modifier
                    .fillMaxWidth(),
                value = viewModel.password.value,
                onValueChange = { it -> viewModel.password.value = it },
                validateField = { viewModel.validatePassword() },
                label = "Contraseña",
                icon = Icons.Default.Lock,
                keyboardType = KeyboardType.Password,
                hideText = true,
                errorMsg = viewModel.errorPassword.value,
            )


            ButtonDefault(
                text = "Iniciar sesión",
                icon = Icons.Default.ArrowForward,
                onClick = { viewModel.login() },
                errorMsg = viewModel.errorEmail.value,
                enabled = viewModel.isLoginEnabled
            )



        }

    }

    loginFlow.value.let { state ->
        when (state) {
            //Mostrar que se esta realizando la peticion
            Response.Loading -> {
                Box(contentAlignment = Center) {
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewBodyContent() {

    ComuSeniasTheme() {
        LoginContent(
            navController= NavHostController(LocalContext.current),
            modifier = Modifier.padding(PaddingValues(16.dp)),
            viewModel = hiltViewModel()
        )
    }


}