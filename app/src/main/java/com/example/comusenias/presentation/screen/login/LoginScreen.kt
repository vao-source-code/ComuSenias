package com.example.comusenias.presentation.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.component.defaults.AuthenticationFooterContent
import com.example.comusenias.presentation.component.defaults.AuthenticationHeaderContent
import com.example.comusenias.presentation.component.login.LoginForm
import com.example.comusenias.presentation.navigation.AppScreen

@Composable
fun LoginScreen(navController: NavHostController) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(start = 16.dp, top = 76.dp, end = 16.dp, bottom = 16.dp)
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
            ) {
            AuthenticationHeaderContent()
            LoginForm(navController = navController)
            AuthenticationFooterContent(
                onClickText = { navController.navigate(route = AppScreen.RegisterScreen.route) }
            )
        }

        }

}


        //Coordinator Layaout
//        Scaffold(
//            modifier = modifier!!,
//            topBar = {},
//            content = { padding ->
//                LoginContent(
//                    navController = navController, modifier = modifier
//                        .padding(padding)
//                        .fillMaxWidth()
//                )
//            },
//            bottomBar = {
//                AuthenticationFooterContent(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
//                    onClickText = { navController.navigate(route = AppScreen.RegisterScreen.route) })
//            }
//        )
//



