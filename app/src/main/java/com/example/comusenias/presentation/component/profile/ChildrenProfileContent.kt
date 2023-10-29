package com.example.comusenias.presentation.component.profile

import android.Manifest
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
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
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
import coil.compose.AsyncImage
import com.example.comusenias.R
import com.example.comusenias.presentation.component.defaults.DialogCapturePicture
import com.example.comusenias.presentation.component.defaults.app.TextFieldApp
import com.example.comusenias.presentation.ui.theme.IMAGE_AVATAR
import com.example.comusenias.presentation.ui.theme.IMAGE_EDIT_BUTTON
import com.example.comusenias.presentation.ui.theme.PROFILE_BIRTHDATE
import com.example.comusenias.presentation.ui.theme.PROFILE_EMAIL
import com.example.comusenias.presentation.ui.theme.PROFILE_NAME_SURNAME
import com.example.comusenias.presentation.ui.theme.SELECTED_IMAGE
import com.example.comusenias.presentation.ui.theme.SIZE140
import com.example.comusenias.presentation.ui.theme.SIZE55
import com.example.comusenias.presentation.ui.theme.size10
import com.example.comusenias.presentation.ui.theme.size20
import com.example.comusenias.presentation.view_model.ChildrenProfileViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ChildrenProfileContent(
    viewModel: ChildrenProfileViewModel = hiltViewModel()
) {

    // Camera permission state
    val cameraPermissionState = rememberPermissionState(
        Manifest.permission.CAMERA
    )

    val dialogState = remember {
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

    Box(modifier = Modifier.padding(size20.dp)) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(SIZE55.dp))

                Box(
                    modifier = Modifier
                        .size(SIZE140.dp)
                        .clip(CircleShape)
                        .clickable {
                            if (!cameraPermissionState.status.isGranted) {
                                cameraPermissionState.launchPermissionRequest()
                            }
                            dialogState.value = true
                        },
                ) {

                    if (viewModel.userData.image?.isNotEmpty() == true) {
                        AsyncImage(
                            modifier = Modifier
                                .size(SIZE140.dp), contentScale = ContentScale.Crop,
                            model = viewModel.userData.image, contentDescription = SELECTED_IMAGE
                        )
                    } else {
                        Image(
                            modifier = Modifier
                                .size(SIZE140.dp),
                            contentScale = ContentScale.Crop,
                            painter = painterResource(id = R.drawable.profile_avatar),
                            contentDescription = IMAGE_AVATAR,
                        )
                    }
                    IconButton(
                        onClick = { /* Acción al hacer clic en el botón */ },
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .offset(y = (-size10).dp),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = IMAGE_EDIT_BUTTON,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(SIZE55.dp))

            Spacer(modifier = Modifier.height(size20.dp))
            TextFieldApp(
                icon = Icons.Default.Email,
                value = viewModel.userData.email,
                onValueChange = {},
                validateField = {},
                label = PROFILE_EMAIL,
                keyboardType = KeyboardType.Text,
                hideText = false,
                readOnly = true
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextFieldApp(
                icon = Icons.Default.Person,
                value = viewModel.userData.name,
                onValueChange = {},
                validateField = {},
                label = PROFILE_NAME_SURNAME,
                keyboardType = KeyboardType.Text,
                hideText = false,
                readOnly = true
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextFieldApp(
                icon = Icons.Default.DateRange,
                value = viewModel.userData.date,
                onValueChange = {},
                validateField = {},
                label = PROFILE_BIRTHDATE,
                keyboardType = KeyboardType.Text,
                hideText = false,
                readOnly = true
            )
        }

    }
    SaveImageChildrenProfile()

}