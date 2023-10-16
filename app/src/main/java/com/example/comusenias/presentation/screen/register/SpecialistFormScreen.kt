package com.example.comusenias.presentation.screen.register

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.component.defaults.app.AuthenticationContent
import com.example.comusenias.presentation.component.defaults.app.ButtonApp
import com.example.comusenias.presentation.component.register.especialistForm.EspecialistFormContent
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.CONTINUE

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SpecialistFormScreen(modifier: Modifier, navController: NavHostController) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        AuthenticationContent(
            content = {
                EspecialistFormContent()
            },
            footer = {
                ButtonApp(
                    titleButton = CONTINUE,
                    onClickButton = { navController.navigate(route = AppScreen.SpecialistScreen.route) }
                )
            }
        )
    }
}
