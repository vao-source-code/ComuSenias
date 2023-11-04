package com.example.comusenias.presentation.screen.specialist

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.presentation.component.defaults.DefaultLoadingProgressIndicator
import com.example.comusenias.presentation.component.defaults.DefaultTopBar
import com.example.comusenias.presentation.component.defaults.app.ButtonApp
import com.example.comusenias.presentation.component.defaults.app.showToast
import com.example.comusenias.presentation.component.specialist.observation.CustomMultilineHintTextField
import com.example.comusenias.presentation.ui.theme.OBSERVATION
import com.example.comusenias.presentation.ui.theme.SEND
import com.example.comusenias.presentation.ui.theme.SIZE1
import com.example.comusenias.presentation.ui.theme.SIZE12
import com.example.comusenias.presentation.ui.theme.SIZE20
import com.example.comusenias.presentation.ui.theme.SIZE3
import com.example.comusenias.presentation.ui.theme.SIZE40
import com.example.comusenias.presentation.ui.theme.blackColorApp
import com.example.comusenias.presentation.view_model.specialist.ObservationViewModel

@Composable
fun SendObservationScreen(
    navController: NavHostController,
    modifier: Modifier,
    viewModel: ObservationViewModel = hiltViewModel(),
    observation: String
) {
    Log.d("Observation", viewModel.observation.toString())


    var letters by remember {
        mutableStateOf(0)
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            Surface(shadowElevation = SIZE3.dp) {
                DefaultTopBar(
                    title = OBSERVATION,
                    upAvailable = true,
                    navHostController = navController
                )
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .padding(SIZE20.dp)
            ) {
                ButtonApp(
                    titleButton = SEND,
                    onClickButton = {
                        viewModel.createObservation()
                    }
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
                .padding(top = SIZE40.dp, start = SIZE20.dp, end = SIZE20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(SIZE1.dp)
            ) {
                CustomMultilineHintTextField(
                    value = viewModel.stateObservation.observation,
                    onValueChanged = {
                        letters = it.length
                        if (it.length <= 230) {
                            viewModel.onObservation(it)
                        }
                    }
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "${letters}/230",
                    style = TextStyle(
                        fontSize = SIZE12.sp,
                        fontWeight = FontWeight.Normal,
                        color = blackColorApp,
                        textAlign = TextAlign.Right
                    )
                )
            }

        }
    }

    ResponseStatusSendObservationScreen(navController = navController, viewModel = viewModel)
}

@Composable
fun ResponseStatusSendObservationScreen(
    navController: NavController,
    viewModel: ObservationViewModel
) {

    when (val observationResponse = viewModel.sendObservationResponse) {
        is Response.Loading -> {
            DefaultLoadingProgressIndicator()
        }

        is Response.Success -> {
            LaunchedEffect(Unit) {
                navController.popBackStack()
            }
        }

        is Response.Error -> {
            showToast(
                LocalContext.current,
                observationResponse.exception?.message.toString(),
                Toast.LENGTH_SHORT
            )
        }

        else -> {}
    }

}
