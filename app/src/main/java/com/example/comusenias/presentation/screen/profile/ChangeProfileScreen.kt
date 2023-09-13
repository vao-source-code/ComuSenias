package com.example.comusenias.presentation.screen.profile

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.component.defaults.DefaultTopBar
import com.example.comusenias.presentation.component.profile.ChangeProfileContent
import com.example.comusenias.presentation.component.profile.ProfileFooterContent
import com.example.comusenias.presentation.component.profile.ResponseStatusProfile
import com.example.comusenias.presentation.component.profile.SaveImageProfile
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.editData
import com.example.comusenias.presentation.view_model.ChangeProfileViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeProfileScreen(
    navController: NavHostController,
    modifier: Modifier?,
    changeViewModel: ChangeProfileViewModel = hiltViewModel(),
    user : String = ""
) {

    Scaffold(
        topBar = { DefaultTopBar(title = editData, true, navController) },
        content = {
            it.calculateBottomPadding()
            ChangeProfileContent(
                modifier = modifier,
                navController = navController,
            )
        },
        bottomBar = {
            val onClick =
                {
                    changeViewModel.save()
                    navController.navigate(route = AppScreen.ProfileScreen.route)
                }

            ProfileFooterContent(onClick)
            ResponseStatusProfile(changeViewModel)
            SaveImageProfile(changeViewModel)
        })


}

