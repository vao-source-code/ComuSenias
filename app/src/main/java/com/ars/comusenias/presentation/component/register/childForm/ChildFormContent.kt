package com.ars.comusenias.presentation.component.register.childForm

import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
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
import com.ars.comusenias.presentation.ui.theme.COMPLETE_NAME
import com.ars.comusenias.presentation.ui.theme.NUMBER_PHONE
import com.ars.comusenias.presentation.ui.theme.SIZE100
import com.ars.comusenias.presentation.ui.theme.SIZE2
import com.ars.comusenias.presentation.view_model.ChildrenRegisterViewModel
@RequiresApi(Build.VERSION_CODES.O)

@Composable
fun ChildFormContent(
    navController: NavHostController,
    viewModel: ChildrenRegisterViewModel = hiltViewModel()
) {

    val state = viewModel.stateChildren
    val openLink =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = SIZE100.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(SIZE2.dp)
        ) {
            TextFieldApp(
                value = state.name,
                onValueChange = { viewModel.onNameInputChanged(it) },
                validateField = { viewModel.validateName() },
                label = COMPLETE_NAME,
                icon = Icons.Default.Person
            )
            TextFieldDate(
                label = BIRTHDAY,
                validateField = { viewModel.validateDate() },
                onValueChange = { birthday -> viewModel.onDateInputChanged(birthday) }
            )
            TextFieldApp(
                value = state.phone,
                onValueChange = { viewModel.onTelInputChanged(it) },
                validateField = { viewModel.validateTel() },
                label = NUMBER_PHONE,
                icon = Icons.Default.Phone
            )
            TermsAndConditions(
                onClickTerms = { viewModel.onClickTerms(openLink) },
                onClickConditions = { viewModel.onClickConditions(openLink) },
                onCheckChange = {
                    viewModel.onCheckTermsAndConditions(it)
                })
        }
        ButtonApp(
            titleButton = CONTINUE,
            enabledButton = viewModel.isRegisterEnabled,
            onClickButton = { viewModel.onRegister() },
        )
    }
    ResponseStatusChildrenRegister(navController = navController, viewModel = viewModel)

}