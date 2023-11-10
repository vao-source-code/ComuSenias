package com.example.comusenias.presentation.component.profile


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.comusenias.R
import com.example.comusenias.presentation.component.defaults.DialogCapturePicture
import com.example.comusenias.presentation.component.defaults.app.TextFieldApp
import com.example.comusenias.presentation.component.defaults.app.TextFieldValueDate
import com.example.comusenias.presentation.ui.theme.EXPIRY_OF_PROFESSIONAL_REGISTRATION
import com.example.comusenias.presentation.ui.theme.IMAGE_AVATAR
import com.example.comusenias.presentation.ui.theme.IMAGE_EDIT_BUTTON
import com.example.comusenias.presentation.ui.theme.MEDICAL_TITLE
import com.example.comusenias.presentation.ui.theme.PROFESSIONAL_REGISTRATION
import com.example.comusenias.presentation.ui.theme.SELECTED_IMAGE
import com.example.comusenias.presentation.ui.theme.SIZE10
import com.example.comusenias.presentation.ui.theme.SIZE140
import com.example.comusenias.presentation.ui.theme.SIZE160
import com.example.comusenias.presentation.ui.theme.SIZE20
import com.example.comusenias.presentation.ui.theme.SIZE40
import com.example.comusenias.presentation.ui.theme.SIZE5
import com.example.comusenias.presentation.ui.theme.SIZE55
import com.example.comusenias.presentation.ui.theme.SIZE64
import com.example.comusenias.presentation.ui.theme.SPECIALTY
import com.example.comusenias.presentation.view_model.specialist.ChangeSpecialistProfileViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SpecialistChangeProfileContent(
    navController: NavHostController,
    modifier: Modifier,
    viewModel: ChangeSpecialistProfileViewModel = hiltViewModel()
) {
    // Camera permission state
    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )

    val state = viewModel.state
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
    Box(modifier = Modifier.padding(start = SIZE20.dp, end = SIZE20.dp, bottom = SIZE40.dp)) {

        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .weight(weight = 1f, fill = false),
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

                        if (viewModel.state.image?.isNotEmpty() == true) {
                            AsyncImage(
                                modifier = Modifier
                                    .size(SIZE140.dp),
                                contentScale = ContentScale.Crop,
                                model = viewModel.state.image,
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

                Spacer(modifier = Modifier.height(SIZE55.dp))
                Text(text = "Datos de la matrícula", fontSize = SIZE20.sp, color = Color.Black)
                Spacer(modifier = Modifier.height(SIZE10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(SIZE64.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    TextFieldApp(
                        modifier = Modifier.width(SIZE160.dp),
                        value = state.medicalLicense,
                        onValueChange = { viewModel.onMedicalLicenseInputChanged(it) },
                        validateField = { viewModel.validateMedicalLicense() },
                        label = PROFESSIONAL_REGISTRATION,
                        icon = null,
                        errorMsg = viewModel.errorMedicalLicense
                    )
                    TextFieldValueDate(
                        value = state.medicalLicenseExpiration,
                        modifier = Modifier.width(SIZE160.dp),
                        label = EXPIRY_OF_PROFESSIONAL_REGISTRATION,
                        onValueChange = { expiration ->
                            viewModel.onMedicalLicenseExpirationInputChanged(expiration)
                        },
                        validateField = { viewModel.validateMedicalLicenseExpirationValid() }
                    )
                }
                Spacer(modifier = Modifier.height(SIZE10.dp))


                TextFieldApp(
                    value = state.titleMedical,
                    onValueChange = { viewModel.onTitleMedicalInputChanged(it) },
                    validateField = { viewModel.validateTitleMedical() },
                    label = MEDICAL_TITLE,
                    icon = null,
                    errorMsg = viewModel.errorTitleMedical
                )
                Spacer(modifier = Modifier.height(SIZE5.dp))

                TextFieldApp(
                    value = state.speciality,
                    onValueChange = { viewModel.onSpecialityInputChanged(it) },
                    validateField = { viewModel.validateSpeciality() },
                    label = SPECIALTY,
                    icon = null,
                    errorMsg = viewModel.errorSpeciality
                )
            }
        }
    }
}