package com.example.comusenias.presentation.component.profile

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.presentation.component.defaults.DefaultLoadingProgressIndicator
import com.example.comusenias.presentation.ui.theme.ERROR_UNKNOWN
import com.example.comusenias.presentation.view_model.ChildrenProfileViewModel

@Composable
fun SaveImageChildrenProfile(viewModel: ChildrenProfileViewModel = hiltViewModel()) {
    when (val response = viewModel.saveImageResponse) {
        Response.Loading -> {
            DefaultLoadingProgressIndicator()
        }

        is Response.Success -> {
            viewModel.onUpdate(response.data)
        }

        is Response.Error -> {
            Toast.makeText(
                LocalContext.current,
                response.exception?.message ?: ERROR_UNKNOWN,
                Toast.LENGTH_LONG
            ).show()
        }

        else -> {}
    }

}