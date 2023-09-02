package com.example.comusenias.presentation.screen.register

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.component.register.RegisterContent
import com.example.comusenias.presentation.component.register.RegisterFooterContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(modifier: Modifier?, navController: NavHostController) {


    //Coordinator Layaout
    Scaffold(
        modifier = modifier!!,

        content = { padding ->
            RegisterContent(
                navController = navController,
                modifier = modifier
                    .padding(padding)
                    .fillMaxWidth()
            )
        },
        bottomBar = {
            RegisterFooterContent(
                navController = navController,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            )
        }
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewRegisterScreen() {
    RegisterScreen(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp), navController = NavHostController(LocalContext.current)
    )
}