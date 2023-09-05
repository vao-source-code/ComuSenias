package com.example.comusenias.presentation.component.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.component.defaults.InputTextFieldDefault
import com.example.comusenias.presentation.view_model.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeProfilePasswordContent(
    navController: NavHostController? = null,
    modifier: Modifier? = Modifier,
    viewModel: ProfileViewModel? = null
) {


    Box(modifier = modifier!!.padding(20.dp)) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {



            Spacer(modifier = Modifier.height(55.dp))
            InputTextFieldDefault(
                modifier = Modifier
                    .fillMaxWidth(),
                label = "Nueva contraseña",
                value = "New Password",
                onValueChange = {},
                validateField = {},
                icon = Icons.Default.Lock,
                keyboardType = KeyboardType.Password,
                hideText = true,
            )
            Spacer(modifier = Modifier.height(20.dp))
            InputTextFieldDefault(
                modifier = Modifier
                    .fillMaxWidth(),
                label = "Repita contraseña",
                value = "New Password",
                onValueChange = {},
                validateField = {},
                icon = Icons.Default.Lock,
                keyboardType = KeyboardType.Password,
                hideText = false,
            )
            Spacer(modifier = Modifier.height(10.dp))

        }
    }
}