package com.ars.comusenias.presentation.component.profile

import android.Manifest
import android.util.Log
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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MedicalServices
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
import com.ars.comusenias.R
import com.ars.comusenias.presentation.component.defaults.DialogCapturePicture
import com.ars.comusenias.presentation.component.defaults.app.TextFieldApp
import com.ars.comusenias.presentation.ui.theme.CHANGE_SPECIALIST_MEDICY
import com.ars.comusenias.presentation.ui.theme.CHANGE_TITLE_MEDICY
import com.ars.comusenias.presentation.ui.theme.IMAGE_AVATAR
import com.ars.comusenias.presentation.ui.theme.IMAGE_EDIT_BUTTON
import com.ars.comusenias.presentation.ui.theme.PROFILE_EMAIL
import com.ars.comusenias.presentation.ui.theme.PROFILE_NAME_SURNAME
import com.ars.comusenias.presentation.ui.theme.SELECTED_IMAGE
import com.ars.comusenias.presentation.ui.theme.SIZE10
import com.ars.comusenias.presentation.ui.theme.SIZE140
import com.ars.comusenias.presentation.ui.theme.SIZE20
import com.ars.comusenias.presentation.ui.theme.SIZE55
import com.ars.comusenias.presentation.view_model.specialist.SpecialistProfileViewModel
import com.ars.comusenias.R
import com.ars.comusenias.presentation.component.defaults.DialogCapturePicture
import com.ars.comusenias.presentation.component.defaults.app.TextFieldApp
import com.ars.comusenias.presentation.ui.theme.CHANGE_SPECIALIST_MEDICY
import com.ars.comusenias.presentation.ui.theme.CHANGE_TITLE_MEDICY
import com.ars.comusenias.presentation.ui.theme.IMAGE_AVATAR
import com.ars.comusenias.presentation.ui.theme.IMAGE_EDIT_BUTTON
import com.ars.comusenias.presentation.ui.theme.PROFILE_EMAIL
import com.ars.comusenias.presentation.ui.theme.PROFILE_NAME_SURNAME
import com.ars.comusenias.presentation.ui.theme.SELECTED_IMAGE
import com.ars.comusenias.presentation.ui.theme.SIZE10
import com.ars.comusenias.presentation.ui.theme.SIZE140
import com.ars.comusenias.presentation.ui.theme.SIZE20
import com.ars.comusenias.presentation.ui.theme.SIZE30
import com.ars.comusenias.presentation.ui.theme.SIZE40
import com.ars.comusenias.presentation.ui.theme.SIZE55
import com.ars.comusenias.presentation.view_model.specialist.SpecialistProfileViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SpecialistProfileContent(
    viewModel: SpecialistProfileViewModel = hiltViewModel()
) {

    Log.d("SpecialistProfileContent", "Usuario: ${viewModel.stateSpecialist.toString()}")
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

    Box(modifier = Modifier.padding(SIZE20.dp)) {

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

                    if (viewModel.stateSpecialist.image?.isNotEmpty() == true) {
                        AsyncImage(
                            modifier = Modifier
                                .size(SIZE140.dp),
                            contentScale = ContentScale.Crop,
                            model = viewModel.stateSpecialist.image,
                            contentDescription = SELECTED_IMAGE
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
                            .offset(y = (-SIZE10).dp),
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = IMAGE_EDIT_BUTTON,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(SIZE30.dp))

            Spacer(modifier = Modifier.height(SIZE20.dp))
            TextFieldApp(
                icon = Icons.Default.Email,
                value = viewModel.stateSpecialist.email,
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
                value = viewModel.stateSpecialist.name,
                onValueChange = {},
                validateField = {},
                label = PROFILE_NAME_SURNAME,
                keyboardType = KeyboardType.Text,
                hideText = false,
                readOnly = true
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextFieldApp(
                icon = Icons.Default.MedicalServices,
                value = viewModel.stateSpecialist.titleMedical,
                onValueChange = {},
                validateField = {},
                label = CHANGE_SPECIALIST_MEDICY,
                keyboardType = KeyboardType.Text,
                hideText = false,
                readOnly = true
            )

            Spacer(modifier = Modifier.height(10.dp))
            TextFieldApp(
                icon = Icons.Default.MedicalServices,
                value = viewModel.stateSpecialist.speciality,
                onValueChange = {},
                validateField = {},
                label = CHANGE_TITLE_MEDICY,
                keyboardType = KeyboardType.Text,
                hideText = false,
                readOnly = true
            )
        }

    }
    SaveImageChildrenProfile()

}