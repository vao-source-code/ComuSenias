package com.ars.comusenias.presentation.component.login

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.ars.comusenias.constants.PreferencesConstant
import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.domain.models.users.Rol
import com.ars.comusenias.presentation.activities.MainActivity
import com.ars.comusenias.presentation.activities.SpecialistActivity
import com.ars.comusenias.presentation.component.defaults.DefaultLoadingProgressIndicator
import com.ars.comusenias.presentation.component.defaults.ToastMake
import com.ars.comusenias.presentation.navigation.AppScreen
import com.ars.comusenias.presentation.ui.theme.LOGIN_ERROR
import com.ars.comusenias.presentation.view_model.LoginViewModel
import es.dmoral.toasty.Toasty

@Composable
fun ResponseStatusRol(
    viewModel: LoginViewModel
) {

    val context = LocalContext.current
    when (viewModel.userResponse) {
        Response.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                DefaultLoadingProgressIndicator()
            }
        }

        is Response.Success -> {
            Toasty.success(LocalContext.current, "Success!", Toast.LENGTH_SHORT, true).show();

            LaunchedEffect(Unit) {

                when (viewModel.dataRolStorageFactory.getRolValue(PreferencesConstant.PREFERENCE_ROL_CURRENT)) {
                    Rol.SPECIALIST.toString() -> {
                        val intent = Intent(context, SpecialistActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        context.startActivity(intent)
                        (context as? Activity)?.finish()
                    }

                    Rol.CHILDREN.toString() -> {
                        val intent = Intent(context, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        context.startActivity(intent)
                        (context as? Activity)?.finish()
                    }

                    else -> {
                    }
                }
            }
        }

        is Response.Error -> {
            Log.e(
                "Error",
                (viewModel.loginResponse as Response.Error).exception?.message.toString()
            )
            ToastMake.showError(
                LocalContext.current,
                LOGIN_ERROR
            )
        }

        else -> {}
    }
}