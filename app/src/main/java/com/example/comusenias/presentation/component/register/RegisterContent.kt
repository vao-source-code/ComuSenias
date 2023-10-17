package com.example.comusenias.presentation.component.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.comusenias.domain.models.state.RegisterState
import com.example.comusenias.presentation.component.defaults.ButtonDefault
import com.example.comusenias.presentation.component.defaults.TextFieldDefault
import com.example.comusenias.presentation.ui.theme.ADD_DATA_FOR_REGISTER
import com.example.comusenias.presentation.ui.theme.CHANGE_PROFILE_CONFIRM_PASSWORD
import com.example.comusenias.presentation.ui.theme.ComuSeniasTheme
import com.example.comusenias.presentation.ui.theme.EMAIL
import com.example.comusenias.presentation.ui.theme.PASSWORD
import com.example.comusenias.presentation.ui.theme.REGISTER
import com.example.comusenias.presentation.ui.theme.REGISTER_TWO
import com.example.comusenias.presentation.ui.theme.SIZE12
import com.example.comusenias.presentation.ui.theme.SIZE16
import com.example.comusenias.presentation.ui.theme.SIZE30
import com.example.comusenias.presentation.ui.theme.size10
import com.example.comusenias.presentation.ui.theme.size18
import com.example.comusenias.presentation.ui.theme.size20
import com.example.comusenias.presentation.ui.theme.size24
import com.example.comusenias.presentation.view_model.RegisterViewModel

@Composable
fun RegisterContent(
    navController: NavHostController,
    modifier: Modifier,
    viewModel: RegisterViewModel = hiltViewModel()
) {

    val state = viewModel.state
    Column(
        modifier = modifier.padding(size20.dp),
        horizontalAlignment = CenterHorizontally,
    ) {
        Text(
            text = REGISTER,
            fontSize = SIZE30.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        CardFormRegister(
            navController = navController,
            viewModel = viewModel,
            state = viewModel.state
        )
    }
}

@Composable
fun CardFormRegister(
    navController: NavHostController,
    viewModel: RegisterViewModel,
    state: RegisterState
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = size24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
    ) {

        Column(modifier = Modifier.padding(PaddingValues(size10.dp))) {
            Text(
                modifier = Modifier.padding(top = size24.dp),
                fontSize = size18.sp,
                fontWeight = FontWeight.Bold,
                text = REGISTER_TWO,
            )

            Spacer(modifier = Modifier.height(size10.dp))

            Text(
                text = ADD_DATA_FOR_REGISTER,
                fontSize = SIZE12.sp
            )


            TextFieldDefault(
                modifier = Modifier
                    .fillMaxWidth(),
                value = state.email,
                onValueChange = { viewModel.onEmailInput(it) },
                label = EMAIL,
                keyboardType = KeyboardType.Email,
                icon = Icons.Default.Email,
                errorMsg = viewModel.errorEmail,
                validateField = { viewModel.validateEmail() }
            )

            TextFieldDefault(
                modifier = Modifier
                    .fillMaxWidth(),
                value = state.password,
                onValueChange = { viewModel.onPasswordInput(it) },
                label = PASSWORD,
                icon = Icons.Default.Lock,
                keyboardType = KeyboardType.Password,
                hideText = true,
                errorMsg = viewModel.errorPassword,
                validateField = { viewModel.validatePassword() }
            )

            TextFieldDefault(
                modifier = Modifier
                    .fillMaxWidth(),
                value = state.confirmPassword,
                onValueChange = { viewModel.onConfirmPasswordInput(it) },
                label = CHANGE_PROFILE_CONFIRM_PASSWORD,
                icon = Icons.Outlined.Lock,
                keyboardType = KeyboardType.Password,
                hideText = true,
                errorMsg = viewModel.errorConfirmPassword,
                validateField = { viewModel.validateConfirmPassword() }
            )
            Spacer(modifier = Modifier.height(size10.dp))

            ButtonDefault(
                text = REGISTER,
                icon = Icons.Default.ArrowForward,
                onClick = { viewModel.onRegister() },
                enabled = viewModel.isRegisterEnabled
            )
        }
    }
    ResponseStatusRegister(navController = navController)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewBodyContent() {

    ComuSeniasTheme() {
        RegisterContent(
            navController = rememberNavController(),
            modifier = Modifier.padding(PaddingValues(SIZE16.dp))
        )
    }
}