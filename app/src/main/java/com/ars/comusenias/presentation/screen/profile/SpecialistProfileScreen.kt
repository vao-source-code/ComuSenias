package com.ars.comusenias.presentation.screen.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ars.comusenias.domain.library.LibraryString
import com.ars.comusenias.presentation.component.profile.ProfileFooterContent
import com.ars.comusenias.presentation.component.profile.SpecialistProfileContent
import com.ars.comusenias.presentation.navigation.AppScreen
import com.ars.comusenias.presentation.view_model.specialist.SpecialistProfileViewModel

@Composable
fun SpecialistProfileScreen(
    navController: NavHostController,
    modifier: Modifier,
    viewModel: SpecialistProfileViewModel = hiltViewModel()
) {
    Scaffold(
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                SpecialistProfileContent(
                    viewModel = viewModel
                )
                val onClick: () -> Unit =
                    {
                        viewModel.stateSpecialist.image =
                            viewModel.stateSpecialist.image?.let { image ->
                                LibraryString.encodeURL(
                                    image
                                )
                            }
                        navController.navigate(
                            route = AppScreen.ChangeSpecialistProfileScreen
                                .passProfile(specialistProfile = viewModel.stateSpecialist.toJson())
                        )
                        viewModel.stateSpecialist.image =
                            viewModel.stateSpecialist.image?.let { image ->
                                LibraryString.decodeURL(
                                    image
                                )
                            }
                    }
                ProfileFooterContent(
                    onClickChangeProfile = onClick,
                    onclickLogout = {
                        viewModel.logout()
                        navController.navigate(AppScreen.LoginScreen.route) {
                            popUpTo(AppScreen.SpecialistProfileScreen.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
    }
}

