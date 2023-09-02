package com.example.comusenias.presentation.component.register

import android.widget.Toast
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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
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
import androidx.navigation.compose.rememberNavController
import com.example.comusenias.domain.models.Response
import com.example.comusenias.presentation.component.defaults.ButtonDefault
import com.example.comusenias.presentation.component.defaults.DefaultLoadingProgressIndicator
import com.example.comusenias.presentation.component.defaults.TextFieldDefault
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.ComuSeniasTheme
import com.example.comusenias.presentation.view_model.RegisterViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterContent(navController : NavHostController , modifier: Modifier , viewModel: RegisterViewModel = hiltViewModel()) {


    Column(
        modifier = modifier.padding(20.dp),
        horizontalAlignment = CenterHorizontally,
    ) {

        Text(
            text = "REGISTRARSE",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        CardFormRegister(navController = navController, viewModel = viewModel)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardFormRegister(navController : NavHostController, viewModel: RegisterViewModel) {

    val singFlow = viewModel.registerFlow.collectAsState()

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
                text = "Registro",
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Ingrese los datos para registrarse",
                fontSize = 12.sp
            )

            TextFieldDefault(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                value = viewModel.userName.value,
                onValueChange = { it -> viewModel.userName.value = it },
                label = "Nombre de usuario",
                keyboardType = KeyboardType.Text,
                icon = Icons.Default.Person,
                errorMsg = viewModel.errorUserName.value,
                validateField = {viewModel.validateUserName()}

                )

            TextFieldDefault(
                modifier = Modifier
                    .fillMaxWidth(),
                value = viewModel.email.value,
                onValueChange = { it -> viewModel.email.value = it },
                label = "Email",
                keyboardType = KeyboardType.Email,
                icon = Icons.Default.Email,
                errorMsg = viewModel.errorEmail.value,
                validateField = {viewModel.validateEmail()}

                )


            TextFieldDefault(
                modifier = Modifier
                    .fillMaxWidth(),
                value = viewModel.password.value,
                onValueChange = { it -> viewModel.password.value = it },
                label = "Contraseña",
                icon = Icons.Default.Lock,
                keyboardType = KeyboardType.Password,
                hideText = true,
                errorMsg = viewModel.errorPassword.value,
                validateField = {viewModel.validatePassword()}
            )

            TextFieldDefault(
                modifier = Modifier
                    .fillMaxWidth(),
                value = viewModel.confirmPassword.value,
                onValueChange = { it -> viewModel.confirmPassword.value = it },
                label = "Confirmar contraseña",
                icon = Icons.Outlined.Lock,
                keyboardType = KeyboardType.Password,
                hideText = true,
                errorMsg = viewModel.errorConfirmPassword.value,
                validateField = {viewModel.validateConfirmPassword()}
            )
            Spacer(modifier = Modifier.height(10.dp))

            ButtonDefault(
                text = "Registrarse",
                icon = Icons.Default.ArrowForward,
                onClick = { viewModel.onRegister() },
                enabled = viewModel.isRegisterEnabled
            )


        }

    }

    singFlow.value.let {
        when(it){
            is Response.Loading -> {
                DefaultLoadingProgressIndicator()
            }
            is Response.Success -> {
                LaunchedEffect(Unit) {
                    //Elimino asi el total del historial de atras
                    navController.popBackStack(AppScreen.LoginScreen.route, inclusive = true)
                    navController.navigate(route = AppScreen.ProfileScreen.route)
                }
            }
            is Response.Error -> {
                Toast.makeText(LocalContext.current, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }



}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewBodyContent() {

    ComuSeniasTheme() {
        RegisterContent(navController = rememberNavController(), modifier = Modifier.padding(PaddingValues(16.dp)))
    }


}