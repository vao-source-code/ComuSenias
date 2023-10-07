package com.example.comusenias.presentation.component.profile

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.comusenias.domain.models.Response
import com.example.comusenias.presentation.component.defaults.DefaultLoadingProgressIndicator
import com.example.comusenias.presentation.ui.theme.ERROR_UPDATED_DATA
import com.example.comusenias.presentation.ui.theme.UPDATED_DATA
import com.example.comusenias.presentation.view_model.ChangeProfileViewModel

@Composable
fun ResponseStatusProfile(viewModel: ChangeProfileViewModel = hiltViewModel()) {
    when (val updateResponse = viewModel.updateResponse) {
        Response.Loading -> {
            DefaultLoadingProgressIndicator()
        }

        is Response.Success -> {

            Toast.makeText(LocalContext.current, UPDATED_DATA, Toast.LENGTH_SHORT).show()
        }

        is Response.Error -> {
            Toast.makeText(LocalContext.current, ERROR_UPDATED_DATA, Toast.LENGTH_SHORT)
                .show()
        }

        else -> {}
    }
}