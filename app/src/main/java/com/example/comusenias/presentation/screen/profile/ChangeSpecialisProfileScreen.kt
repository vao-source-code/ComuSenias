package com.example.comusenias.presentation.screen.profile

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.presentation.component.defaults.DefaultLoadingProgressIndicator
import com.example.comusenias.presentation.component.defaults.DefaultTopBar
import com.example.comusenias.presentation.component.defaults.ToastMake
import com.example.comusenias.presentation.component.defaults.app.ButtonApp
import com.example.comusenias.presentation.component.profile.SpecialistChangeProfileContent
import com.example.comusenias.presentation.ui.theme.ERROR_UPDATED_DATA
import com.example.comusenias.presentation.ui.theme.SIZE20
import com.example.comusenias.presentation.ui.theme.UPDATED_DATA
import com.example.comusenias.presentation.view_model.specialist.ChangeSpecialistProfileViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChangeSpecialistProfileScreen(
    navController: NavHostController,
    modifier: Modifier,
    specialistProfile: String,
    viewModel: ChangeSpecialistProfileViewModel = hiltViewModel()
) {
    Log.d("ProfileEditScreen", "Usuario: $specialistProfile")

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
        bottomBar = {
            Box(Modifier.padding(SIZE20.dp)) {
                ButtonApp(titleButton = "Actualizar Datos", onClickButton = {
                    viewModel.save()
                })

            }
        }
    )
    SaveImage()
    ProfileUpdate()
}

@Composable
fun SaveImage(viewModel: ChangeSpecialistProfileViewModel = hiltViewModel()) {

    when (val response = viewModel.saveImageResponse) {
        Response.Loading -> {
            DefaultLoadingProgressIndicator()
        }

        is Response.Success -> {
            viewModel.onUpdate(response.data)
        }

        is Response.Error -> {
            Log.e("Error", (response as Response.Error).exception?.message.toString())

            Toast.makeText(
                LocalContext.current,
                response.exception?.message ?: "Error desconcido",
                Toast.LENGTH_LONG
            ).show()
        }

        else -> {}
    }

}

@Composable
fun ProfileUpdate(viewModel: ChangeSpecialistProfileViewModel = hiltViewModel()) {

    when (val updateResponse = viewModel.updateResponse) {
        Response.Loading -> {
            DefaultLoadingProgressIndicator()
        }

        is Response.Success -> {
            ToastMake.showSuccess(
                LocalContext.current,
                UPDATED_DATA,
            )
        }

        is Response.Error -> {
            ToastMake.showError(
                LocalContext.current,
                ERROR_UPDATED_DATA,
            )
        }

        else -> {}
    }

}