package com.ars.comusenias.presentation.screen.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.ars.comusenias.R
import com.ars.comusenias.presentation.component.login.AuthenticationContent
import com.ars.comusenias.presentation.component.login.AuthenticationFooterContent
import com.ars.comusenias.presentation.component.register.RegisterForm
import com.ars.comusenias.presentation.navigation.AppScreen
import com.ars.comusenias.presentation.ui.theme.DO_YOU_ALREADY_HAVE_AN_ACCOUNT
import com.ars.comusenias.presentation.ui.theme.ENTER
import com.ars.comusenias.presentation.ui.theme.SIZE90

@Composable
fun RegisterScreen(modifier: Modifier, navController: NavHostController) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_login), // Reemplaza con tu VectorDrawable
            contentDescription = null, // Descripción accesible opcional
            modifier = Modifier.fillMaxSize(), // Ajusta el modificador según tu necesidad
            contentScale = ContentScale.Crop // Ajusta el escalado para adaptarse al fondo
        )

        AuthenticationContent(
            content = { RegisterForm(navController = navController) },
            footer = {
                AuthenticationFooterContent(
                    textOne = DO_YOU_ALREADY_HAVE_AN_ACCOUNT,
                    textTwo = ENTER,
                    onClickText = { navController.navigate(route = AppScreen.LoginScreen.route) },
                )
            },
            sizeImage = SIZE90,

        )
    }
}
