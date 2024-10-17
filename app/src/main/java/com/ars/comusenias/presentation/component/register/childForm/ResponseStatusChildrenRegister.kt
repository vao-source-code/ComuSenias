package com.ars.comusenias.presentation.component.register.childForm

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.presentation.activities.MainActivity
import com.ars.comusenias.presentation.component.defaults.DefaultLoadingProgressIndicator
import com.ars.comusenias.presentation.component.defaults.ToastMake
import com.ars.comusenias.presentation.navigation.AppScreen
import com.ars.comusenias.presentation.ui.theme.REGISTER_ERROR
import com.ars.comusenias.presentation.view_model.ChildrenRegisterViewModel

@Composable
fun ResponseStatusChildrenRegister(
    navController: NavHostController,
    viewModel: ChildrenRegisterViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    when (val registerResponse = viewModel.registerResponse) {
        is Response.Loading -> {
            DefaultLoadingProgressIndicator()
        }

        is Response.Success -> {
            LaunchedEffect(Unit) {
                viewModel.createUser()

                val intent = Intent(context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                context.startActivity(intent)
                (context as? Activity)?.finish()
            }
        }

        is Response.Error -> {
            Log.e(
                "Error",
                (viewModel.registerResponse as Response.Error).exception?.message.toString()
            )
            ToastMake.showError(
                LocalContext.current,
                REGISTER_ERROR
            )
        }

        else -> {}
    }
}
