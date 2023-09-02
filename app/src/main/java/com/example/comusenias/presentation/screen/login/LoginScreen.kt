package com.example.comusenias.presentation.screen.login

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.component.login.FooterContent
import com.example.comusenias.presentation.component.login.LoginContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(modifier: Modifier?, navController: NavHostController) {

    //Coordinator Layaout
    Scaffold(
        modifier = modifier!!,
        topBar = {},
        content = { padding ->
            LoginContent(
                navController = navController, modifier = modifier
                    .padding(padding)
                    .fillMaxWidth()
            )
        },
        bottomBar = {
            FooterContent(
                navController = navController, modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            )
        }
    )


}

