package com.example.comusenias.presentation.screen.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.comusenias.constants.TestTag
import com.example.comusenias.presentation.component.defaults.app.AuthenticationContent
import com.example.comusenias.presentation.component.defaults.app.AuthenticationFooterContent
import com.example.comusenias.presentation.component.login.LoginForm
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.DONT_HAVE_ACCOUNT
import com.example.comusenias.presentation.ui.theme.REGISTER
import com.example.comusenias.presentation.view_model.BottomBarViewModel

@Composable
fun LoginScreen(
    navController: NavHostController,
    modifier: Modifier,
    bottomBarViewModel: BottomBarViewModel = hiltViewModel()
) {
    bottomBarViewModel.isBottomAppBarVisible.value = true

    Box(
        modifier = modifier
            .fillMaxSize()
            .testTag(TestTag.TAG_LOGIN_SCREEN),
    ) {
        AuthenticationContent(
            content = { LoginForm(navController = navController) },
            footer = {
                AuthenticationFooterContent(
                    textOne = DONT_HAVE_ACCOUNT,
                    textTwo = REGISTER,
                    onClickText = { navController.navigate(route = AppScreen.RegisterScreen.route) }
                )
            }
        )

    }
}