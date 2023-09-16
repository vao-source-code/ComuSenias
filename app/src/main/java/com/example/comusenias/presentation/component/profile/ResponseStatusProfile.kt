package com.example.comusenias.presentation.component.profile

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.comusenias.domain.models.Response
import com.example.comusenias.presentation.component.defaults.DefaultLoadingProgressIndicator
import com.example.comusenias.presentation.view_model.ChangeProfileViewModel

@Composable
fun ResponseStatusProfile(viewModel: ChangeProfileViewModel = hiltViewModel()) {

    when (val updateResponse  = viewModel.updateResponse) {
        Response.Loading -> {
            DefaultLoadingProgressIndicator()
        }
        is Response.Success -> {

            Toast.makeText(LocalContext.current, "Datos Actualizados", Toast.LENGTH_SHORT).show()
        }
        is Response.Error -> {
            Toast.makeText(LocalContext.current, "Error al actualizar datos", Toast.LENGTH_SHORT).show()
        }

        else -> {}
    }
}