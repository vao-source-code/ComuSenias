package com.ars.comusenias.presentation.component.register.especialistForm

import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ars.comusenias.presentation.component.defaults.app.ButtonApp
import com.ars.comusenias.presentation.component.defaults.app.TextFieldApp
import com.ars.comusenias.presentation.component.defaults.app.TextFieldDate
import com.ars.comusenias.presentation.component.register.TermsAndConditions
import com.ars.comusenias.presentation.ui.theme.BIRTHDAY
import com.ars.comusenias.presentation.ui.theme.CONTINUE
import com.ars.comusenias.presentation.ui.theme.EXPIRY_OF_PROFESSIONAL_REGISTRATION
import com.ars.comusenias.presentation.ui.theme.MEDICAL_TITLE
import com.ars.comusenias.presentation.ui.theme.COMPLETE_NAME
import com.ars.comusenias.presentation.ui.theme.NUMBER_PHONE
import com.ars.comusenias.presentation.ui.theme.PROFESSIONAL_REGISTRATION
import com.ars.comusenias.presentation.ui.theme.SIZE160
import com.ars.comusenias.presentation.ui.theme.SIZE2
import com.ars.comusenias.presentation.ui.theme.SIZE20
import com.ars.comusenias.presentation.ui.theme.SIZE64
import com.ars.comusenias.presentation.ui.theme.SPECIALTY
import com.ars.comusenias.presentation.view_model.specialist.SpecialistRegisterViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EspecialistFormContent(
    navController: NavHostController,
    viewModel: SpecialistRegisterViewModel = hiltViewModel()
) {

    val state = viewModel.stateSpecialist
    val openLink =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = SIZE20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState), // Agregar scroll
            verticalArrangement = Arrangement.spacedBy(SIZE2.dp)
        ) {
            TextFieldApp(
                value = state.name,
                onValueChange = { viewModel.onNameInputChanged(it) },
                validateField = { viewModel.validateName() },
                label = COMPLETE_NAME,
                icon = Icons.Default.Person,
                errorMsg = viewModel.errorName
            )
            TextFieldApp(
                value = state.tel,
                onValueChange = { viewModel.onTelInputChanged(it) },
                validateField = { viewModel.validateTel() },
                label = NUMBER_PHONE,
                icon = Icons.Default.Phone,
                errorMsg = viewModel.errorTelephone
            )
            TextFieldDate(
                label = BIRTHDAY,
                onValueChange = { birthday ->
                    viewModel.onDateInputChanged(birthday)
                },
                validateField = { viewModel.validateDate() }
            )
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
                TextFieldDate(
                    modifier = Modifier.width(SIZE160.dp),
                    label = EXPIRY_OF_PROFESSIONAL_REGISTRATION,
                    onValueChange = { expiration ->
                        viewModel.onMedicalLicenseExpirationInputChanged(expiration)
                    },
                    validateField = { viewModel.validateMedicalLicenseExpirationValid() }
                )
            }
            TextFieldApp(
                value = state.titleMedical,
                onValueChange = { viewModel.onTitleMedicalInputChanged(it) },
                validateField = { viewModel.validateTitleMedical() },
                label = MEDICAL_TITLE,
                icon = null,
                errorMsg = viewModel.errorTitleMedical
            )
            TextFieldApp(
                value = state.speciality,
                onValueChange = { viewModel.onSpecialityInputChanged(it) },
                validateField = { viewModel.validateSpeciality() },
                label = SPECIALTY,
                icon = null,
                errorMsg = viewModel.errorSpeciality
            )
            TermsAndConditions(
                onClickTerms = { viewModel.onClickTerms(openLink) },
                onClickConditions = { viewModel.onClickConditions(openLink) },
                onCheckChange = {
                    viewModel.onCheckTermsAndConditions(it)
                }
            )

            Spacer(modifier = Modifier.height(SIZE20.dp))

            ButtonApp(
                titleButton = CONTINUE,
                enabledButton = viewModel.isRegisterEnabled,
                onClickButton = { viewModel.onRegister() }
            )
        }


    }

        ResponseStatusSpecialistRegister(navController = navController, viewModel = viewModel)

}