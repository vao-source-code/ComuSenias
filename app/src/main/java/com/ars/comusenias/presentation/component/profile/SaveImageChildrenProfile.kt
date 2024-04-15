package com.ars.comusenias.presentation.component.profile

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.presentation.component.defaults.DefaultLoadingProgressIndicator
import com.ars.comusenias.presentation.component.defaults.ToastMake
import com.ars.comusenias.presentation.ui.theme.ERROR_UNKNOWN
import com.ars.comusenias.presentation.view_model.ChildrenProfileViewModel

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
            Log.e("Error", (response as Response.Error).exception?.message.toString())
            ToastMake.showError(
                LocalContext.current,
                ERROR_UNKNOWN
            )
        }

        else -> {}
    }

}