package com.ars.comusenias.presentation.splashScreen

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ars.comusenias.R
import com.ars.comusenias.constants.PreferencesConstant
import com.ars.comusenias.constants.TestTag.Companion.TAG_BOX_SPLASH_SCREEN
import com.ars.comusenias.constants.TestTag.Companion.TAG_COLUMN_SPLASH_SCREEN
import com.ars.comusenias.domain.models.users.Rol
import com.ars.comusenias.presentation.activities.AuthActivity
import com.ars.comusenias.presentation.activities.MainActivity
import com.ars.comusenias.presentation.activities.SpecialistActivity
import com.ars.comusenias.presentation.component.defaults.GetImage
import com.ars.comusenias.presentation.navigation.AppScreen
import com.ars.comusenias.presentation.ui.theme.logoApp
import com.ars.comusenias.presentation.ui.theme.size150
import com.ars.comusenias.presentation.view_model.LoginViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    SplashScreenContent()

    LaunchedEffect(key1 = true) {
        delay(2000)
    }

    val context = LocalContext.current

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
                val intent = Intent(context, AuthActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                context.startActivity(intent)
                (context as? Activity)?.finish()
            }

        }
    }
}


@Composable
fun SplashScreenContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .testTag(TAG_BOX_SPLASH_SCREEN)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag(TAG_COLUMN_SPLASH_SCREEN),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SetImageSplash()
    }
}

@Composable
private fun SetImageSplash() {
    GetImage(
        painter = R.drawable.comu_senias_with_text,
        contentDescription = logoApp,
        width = size150.dp,
        height = size150.dp
    )
}