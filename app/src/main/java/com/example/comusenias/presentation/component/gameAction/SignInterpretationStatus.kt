package com.example.comusenias.presentation.component.gameAction

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.comusenias.R
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.example.comusenias.presentation.ui.theme.greenColorApp
import com.example.comusenias.presentation.ui.theme.size150
import kotlinx.coroutines.delay

enum class Status {
    LOADING,
    CORRECT,
    INCORRECT
}
class MyViewModel : ViewModel() {
    var status: Status = Status.LOADING
}

@Composable
fun SignInterpretationStatus(response: (Boolean) -> Unit) {
    val viewModel: MyViewModel = viewModel()
    var status by remember { mutableStateOf(Status.LOADING) }

    LaunchedEffect(Unit) {
        delay(5000)
        viewModel.status = Status.CORRECT
        status = Status.CORRECT
    }
    CrossfadeIcon(status){
        response(it)
    }
}

@Composable
private fun CrossfadeIcon(status: Status, response: (Boolean) -> Unit) {
    val modifierSize = Modifier
        .size(size150.dp)

    Crossfade(
        modifier = Modifier
            .padding(top = 70.dp),
        targetState = status,
        label = EMPTY_STRING
    ) { selectedStatus ->
        when (selectedStatus) {
            Status.LOADING -> {
                CircularProgressIndicator(
                    modifier = modifierSize,
                    color = greenColorApp,
                    strokeWidth = 4.dp
                )
            }

            Status.CORRECT -> {
                Image(
                    painter = painterResource(id = R.drawable.correct_image),
                    contentDescription = EMPTY_STRING,
                    modifier = modifierSize,
                    contentScale = ContentScale.Fit
                )
                response(true)
            }

            Status.INCORRECT -> {
                Image(
                    painter = painterResource(id = R.drawable.incorrect_image),
                    contentDescription = EMPTY_STRING,
                    modifier = modifierSize,
                    contentScale = ContentScale.Fit
                )
                response(false)
            }
        }
    }
}