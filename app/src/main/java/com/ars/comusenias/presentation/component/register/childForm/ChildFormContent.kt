package com.ars.comusenias.presentation.component.register.childForm

import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ars.comusenias.R
import com.ars.comusenias.presentation.ui.theme.ADVANCED
import com.ars.comusenias.presentation.ui.theme.BEGINNER
import com.ars.comusenias.presentation.ui.theme.INTERMEDIATE
import com.ars.comusenias.presentation.ui.theme.LEVEL_SELECTION_DESCRIPTION
import com.ars.comusenias.presentation.ui.theme.LEVEL_SELECTION_SUBTITLE
import com.ars.comusenias.presentation.ui.theme.LEVEL_SELECTION_TITLE
import com.ars.comusenias.presentation.ui.theme.SIZE14
import com.ars.comusenias.presentation.ui.theme.SIZE16
import com.ars.comusenias.presentation.ui.theme.SIZE20
import com.ars.comusenias.presentation.ui.theme.SIZE24
import com.ars.comusenias.presentation.ui.theme.SIZE30
import com.ars.comusenias.presentation.ui.theme.lightGreey
import com.ars.comusenias.presentation.ui.theme.lightPrimaryColor
import com.ars.comusenias.presentation.ui.theme.mintGreen
import com.ars.comusenias.presentation.ui.theme.mintOrange
import com.ars.comusenias.presentation.ui.theme.mintRed
import com.ars.comusenias.presentation.ui.theme.primaryBlackColorApp

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

    /*
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
            InputTextField(
                value = state.name,
                onValueChange = { viewModel.onNameInputChanged(it) },
                validateField = { viewModel.validateName() },
                label = COMPLETE_NAME,
                icon = Icons.Default.Person
            )
            InputTextFieldDate(
                label = BIRTHDAY,
                validateField = { viewModel.validateDate() },
                onValueChange = { birthday -> viewModel.onDateInputChanged(birthday) }
            )
            InputTextField(
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

     */

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()

    ) {

        Spacer(modifier = Modifier.padding(SIZE30.dp))
        // Title Section
        Text(
            text = LEVEL_SELECTION_TITLE,
            fontSize = SIZE16.sp,
            color = lightGreey,
            textAlign = TextAlign.Center
        )
        Text(
            text = LEVEL_SELECTION_SUBTITLE,
            fontSize = SIZE24.sp,
            fontWeight = FontWeight.Bold,
            color = lightPrimaryColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = LEVEL_SELECTION_DESCRIPTION,
            fontSize = SIZE14.sp,
            color = lightGreey,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = SIZE24.dp)
        )
        Spacer(modifier = Modifier.padding(SIZE30.dp))

        // Gradient Background Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.background_child_form),
                contentDescription = null, // Descripción accesible opcional
                modifier = Modifier.fillMaxSize(), // Ajusta el modificador según tu necesidad
                contentScale = ContentScale.Crop // Ajusta el escalado para adaptarse al fondo
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(vertical = SIZE24.dp, horizontal = SIZE20.dp)
            ) {
                LevelButton(
                    text = BEGINNER,
                    backgroundColor = mintGreen,
                    onClick = { viewModel.onRegister() })
                Spacer(modifier = Modifier.height(SIZE30.dp))
                LevelButton(
                    text = INTERMEDIATE,
                    backgroundColor = mintOrange,
                    onClick = { viewModel.onRegister() })
                Spacer(modifier = Modifier.height(SIZE30.dp))
                LevelButton(
                    text = ADVANCED,
                    backgroundColor = mintRed,
                    onClick = { viewModel.onRegister() })
            }
        }
    }


    ResponseStatusChildrenRegister(navController = navController, viewModel = viewModel)

}

@Composable
fun LevelButton(text: String, backgroundColor: Color, onClick: () -> Unit = {}) {
    Button(
        onClick = onClick ,
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}