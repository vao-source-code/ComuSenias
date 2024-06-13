package com.ars.comusenias.presentation.screen.profile

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ars.comusenias.R
import com.ars.comusenias.domain.library.LibraryDebugger
import com.ars.comusenias.presentation.activities.MainActivity
import com.ars.comusenias.presentation.component.bottomBar.ShowBottomBar
import com.ars.comusenias.presentation.component.defaults.FloatingButtonDefault
import com.ars.comusenias.presentation.component.defaults.app.ButtonApp
import com.ars.comusenias.presentation.component.profile.ChildrenProfileContent
import com.ars.comusenias.presentation.component.profile.ProfileFooterContent
import com.ars.comusenias.presentation.navigation.AppScreen
import com.ars.comusenias.presentation.ui.theme.SIZE20
import com.ars.comusenias.presentation.ui.theme.SIZE5
import com.ars.comusenias.presentation.view_model.ChildrenProfileViewModel

@Composable
fun ChildrenProfileScreen(
    navController: NavHostController,
    modifier: Modifier,
    viewModel: ChildrenProfileViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as? Activity

    val scrollState = rememberScrollState()

    Scaffold(
        bottomBar = {
            ShowBottomBar(navController = navController)
        }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                ChildrenProfileContent(
                    viewModel = viewModel,
                    navController = navController
                )
                val onClick: () -> Unit =
                    {
                        viewModel.save()
                    }

                ProfileFooterContent(
                    onClickChangeProfile = onClick
                ) {
                    viewModel.logout()
                    activity?.finish()
                    activity?.startActivity(Intent(activity, MainActivity::class.java))
                }
            }
        }
    }
}