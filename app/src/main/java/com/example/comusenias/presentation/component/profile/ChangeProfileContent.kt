package com.example.comusenias.presentation.component.profile


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.comusenias.R
import com.example.comusenias.presentation.component.defaults.DialogCapturePicture
import com.example.comusenias.presentation.component.defaults.app.TextFieldApp
import com.example.comusenias.presentation.component.defaults.app.TextFieldAppPassword
import com.example.comusenias.presentation.view_model.ChangeProfileViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun ChangeProfileContent(
    navController: NavHostController? = null,
    modifier: Modifier? = Modifier,
    viewModel: ChangeProfileViewModel = hiltViewModel()
) {

    // Camera permission state
    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )




    val state = viewModel.state
    var dialogState = remember {
        mutableStateOf(false)
    }

    DialogCapturePicture(
       status = dialogState,
        takePhoto = {
            viewModel.takePhoto()
        },
        pickPhoto = {
            viewModel.pickImage()
        }
    )

    viewModel.resultingActivityHandler.handle()
    Box(modifier = modifier!!.padding(20.dp)) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(55.dp))

                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape)
                        .clickable {
                            if (cameraPermissionState.status.isGranted) {
                            } else {
                                cameraPermissionState.launchPermissionRequest()
                            }

                            dialogState.value = true
                        },
                ) {

                    if (viewModel.state.image != "") {
                        AsyncImage(
                            modifier = Modifier
                                .size(140.dp), contentScale = ContentScale.Crop,
                            model = viewModel.state.image, contentDescription = "Seleted Image"
                        )
                    } else {
                        Image(
                            modifier = Modifier
                                .size(140.dp),
                            contentScale = ContentScale.Crop,
                            painter = painterResource(id = R.drawable.profile_avatar),
                            contentDescription = "image avatar",

                            )
                    }


                    IconButton(
                        onClick = { /* Acción al hacer clic en el botón */ },
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .offset(y = (-10).dp),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "image edit button",
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(55.dp))

            TextFieldApp(
                label = "Usuario",
                value = state.userName,
                onValueChange = { viewModel.onUserNameChange(it) },
                validateField = { viewModel.validateUserName() },
                icon = Icons.Default.Edit,
                keyboardType = KeyboardType.Text,
                errorMsg = viewModel.errorUserName,
            )


            Spacer(modifier = Modifier.height(20.dp))
            TextFieldAppPassword(
                label = "Nueva contraseña",
                value = "New Password",
                onValueChange = {},
                validateField = {},

                )
            Spacer(modifier = Modifier.height(20.dp))
            TextFieldAppPassword(
                label = "Repita contraseña",
                value = "New Password",
                onValueChange = {},
                validateField = {},

                )
            Spacer(modifier = Modifier.height(10.dp))

        }

    }
}