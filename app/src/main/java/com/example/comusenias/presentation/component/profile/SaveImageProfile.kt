package com.example.comusenias.presentation.component.profile

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.comusenias.domain.models.Response
import com.example.comusenias.presentation.component.defaults.DefaultLoadingProgressIndicator
import com.example.comusenias.presentation.view_model.ChangeProfileViewModel

@Composable

fun SaveImageProfile(viewModel: ChangeProfileViewModel = hiltViewModel()) {
    when (val response = viewModel.saveImageResponse) {
        is Response.Loading -> {
            DefaultLoadingProgressIndicator()
        }

        is Response.Success -> {
            viewModel.onUpdate(response.data)
        }

        is Response.Error -> {
            Toast.makeText(
                LocalContext.current,
                response?.exception?.message.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }

        else -> {}
    }
}