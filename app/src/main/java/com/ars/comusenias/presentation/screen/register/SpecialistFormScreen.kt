package com.ars.comusenias.presentation.screen.register

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.ars.comusenias.presentation.component.defaults.app.AuthenticationContent
import com.ars.comusenias.presentation.component.register.especialistForm.EspecialistFormContent

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SpecialistFormScreen(modifier: Modifier, navController: NavHostController) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        AuthenticationContent(
            content = {
                EspecialistFormContent(navController = navController)
            }
        )
    }
}
