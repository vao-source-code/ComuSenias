package com.example.comusenias.presentation.screen.gameAction

import android.Manifest.permission.CAMERA
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.TIRAMISU
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.example.comusenias.domain.models.game.SubLevelModel
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.presentation.activities.MainActivity.Companion.getLevelViewModel
import com.example.comusenias.presentation.component.defaults.app.CircularProgressBar
import com.example.comusenias.presentation.component.gameAction.GameAction
import com.example.comusenias.presentation.component.gameAction.MatchSign
import com.example.comusenias.presentation.component.permission.AlertDialogPermission
import com.example.comusenias.presentation.extensions.validation.selectedOption
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.CONTINUE
import com.example.comusenias.presentation.ui.theme.ERROR_RETRY_SUB_LEVEL
import com.example.comusenias.presentation.ui.theme.STEP_TREE
import com.example.comusenias.presentation.ui.theme.WHAT_SIGN_IS
import com.example.comusenias.presentation.view_model.LevelViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@Composable
fun ChoseTheSignPlayScreen(
    navController: NavHostController,
    level: String,
    subLevel: String,
    levelViewModel: LevelViewModel
) {
    val subLevelViewModel = levelViewModel.getSubLevelById(level, subLevel)

    val isButtonEnabled = remember { mutableStateOf(true) }
    val onMatchResult: (Boolean) -> Unit = {
        isButtonEnabled.value = it
    }

    when (levelViewModel.levelsResponse) {
        is Response.Loading -> {
            CircularProgressBar()
        }

        is Response.Error -> {
            Snackbar {
                Text(text = ERROR_RETRY_SUB_LEVEL)
            }
        }

        is Response.Success -> {
            ShowChoseTheSign(subLevelViewModel, isButtonEnabled, navController, onMatchResult)
        }

        else -> {
//            ContentProgressBar(null)
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun ShowChoseTheSign(
    subLevel: SubLevelModel?,
    isButtonEnabled: MutableState<Boolean>,
    navController: NavHostController,
    onMatchResult: (Boolean) -> Unit
) {
    var showSetting by remember { mutableStateOf(false) }
    var permissionDenied by remember { mutableStateOf(false) }
    val permissionCamera = if (SDK_INT >= TIRAMISU) {
        listOf(CAMERA)
    } else {
        listOf(CAMERA, WRITE_EXTERNAL_STORAGE)
    }
    var subLevelImageOnly by remember { mutableStateOf("") }

    val multiplePermissionState =
        rememberMultiplePermissionsState(permissionCamera) { permissions ->
            if (permissions.all { it.value }) {
                navController.navigate(AppScreen.InfoMakeSignScreen.route)
                selectedOption(subLevelImageOnly, getLevelViewModel)
            } else {
                permissionDenied = true
            }
        }
    LaunchedEffect(showSetting) {
        if (showSetting) {
            permissionDenied = false
            showSetting = false
        }
    }

    subLevel?.let {
        //TODO en caso de ser video se podria usar las imagenes mejor y que decida
        val sign = Sign(imageResource = it.imageOnly, letter = it.name)
        val randomSign = Sign(imageResource = it.randomImageOnly, letter = it.randomLetter)
        GameAction(
            imageSign = it.image,
            letterSign = it.name,
            title = WHAT_SIGN_IS,
            titleButton = CONTINUE,
            enabledButton = isButtonEnabled.value,
            currentSteps = STEP_TREE,
            navController = navController,
            clickButton = {
                multiplePermissionState.launchMultiplePermissionRequest()
                subLevelImageOnly = it.imageOnly
            },
        ) {
            if (permissionDenied) {
                AlertDialogPermission(permissionDenied) { statusPermission ->
                    showSetting = statusPermission
                }
            } else {
                MatchSign(
                    sign = sign,
                    randomSign = randomSign,
                    onMatchResult = onMatchResult
                )
            }
        }
    }
}

data class Sign(
    val imageResource: String,
    val letter: String
)