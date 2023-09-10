package com.example.comusenias.presentation.screen.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.component.defaults.DefaultTopBar
import com.example.comusenias.presentation.component.profile.ProfileContent
import com.example.comusenias.presentation.component.profile.ProfileFooterContent
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.view_model.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavHostController,
    modifier: Modifier?,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    Scaffold(topBar = { DefaultTopBar(title = "Perfil" , true , navController ) }, content = { it ->
        it.calculateBottomPadding()
        ProfileContent(
            navController = navController,
            modifier = modifier,
            viewModel = viewModel
        )
    }, bottomBar = {

        ProfileFooterContent {
            navController?.navigate(route = AppScreen.ChangeProfileScreen.route) {
                popUpTo(AppScreen.ProfileScreen.route) {}
            }
        }


    })

}


@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = NavHostController(LocalContext.current), modifier = Modifier.fillMaxSize() , viewModel = hiltViewModel())
}