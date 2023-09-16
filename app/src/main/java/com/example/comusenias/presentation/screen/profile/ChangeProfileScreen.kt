package com.example.comusenias.presentation.screen.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.component.profile.ChangeProfileContent
import com.example.comusenias.presentation.component.profile.ProfileFooterContent
import com.example.comusenias.presentation.component.profile.ResponseStatusProfile
import com.example.comusenias.presentation.component.profile.SaveImageProfile
import com.example.comusenias.presentation.view_model.ChangeProfileViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeProfileScreen(
    navController: NavHostController,
    modifier: Modifier,
    changeViewModel: ChangeProfileViewModel = hiltViewModel(),
    user: String = ""
) {

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(modifier = modifier.fillMaxSize()) {
            ChangeProfileContent(
                modifier = modifier,
                navController = navController,
            )
            val onClick : () -> Unit =
                {
                    changeViewModel.saveImage()

                }

            ProfileFooterContent(onClick)
            SaveImageProfile()
            ResponseStatusProfile()


        }
    }

}

