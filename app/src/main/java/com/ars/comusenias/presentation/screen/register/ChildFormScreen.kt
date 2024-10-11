package com.ars.comusenias.presentation.screen.register

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.ars.comusenias.presentation.component.login.AuthenticationContent
import com.ars.comusenias.presentation.component.register.childForm.ChildFormContent

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChildFormScreen(modifier: Modifier, navController: NavHostController) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        AuthenticationContent(
            content = { ChildFormContent(navController = navController) }
        )
    }
}