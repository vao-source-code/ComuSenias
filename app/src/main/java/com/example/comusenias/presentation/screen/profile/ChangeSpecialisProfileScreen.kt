package com.example.comusenias.presentation.screen.profile

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.component.defaults.DefaultTopBar
import com.example.comusenias.presentation.component.profile.SpecialistChangeProfileContent

@Composable
fun ChangeSpecialistProfileScreen(
    navController: NavHostController,
    modifier: Modifier,
    user: String
) {
    Log.d("ProfileEditScreen", "Usuario: $user")

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Editar usuario",
                upAvailable = true,
                navHostController = navController
            )
        },
        content = { it ->
            SpecialistChangeProfileContent(
                navController = navController,
                modifier = modifier.padding(it)
            )
        },
        bottomBar = {}
    )
    //SaveImage()
    //ProfileUpdate()
}