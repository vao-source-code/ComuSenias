package com.example.comusenias.presentation.component.gameAction

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.comusenias.R
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.example.comusenias.presentation.ui.theme.greenColorApp
import com.example.comusenias.presentation.ui.theme.size150

enum class Status {
    LOADING,
    CORRECT,
    INCORRECT
}

@Composable
fun SignInterpretationStatus(
    status: Status,
    response: (Boolean) -> Unit
) {
    CrossfadeIcon(status){
        response(it)
    }
}

@Composable
fun CrossfadeIcon(status: Status, response: (Boolean) -> Unit) {
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
                    modifier = modifierSize
                        .testTag(Status.LOADING.name),
                    color = greenColorApp,
                    strokeWidth = 4.dp
                )
            }

            Status.CORRECT -> {
                Image(
                    painter = painterResource(id = R.drawable.correct_image),
                    contentDescription = EMPTY_STRING,
                    modifier = modifierSize
                        .testTag(Status.CORRECT.name),
                    contentScale = ContentScale.Fit
                )
                response(true)
            }

            Status.INCORRECT -> {
                Image(
                    painter = painterResource(id = R.drawable.correct_image),
                    contentDescription = EMPTY_STRING,
                    modifier = modifierSize
                        .testTag(Status.INCORRECT.name),
                    contentScale = ContentScale.Fit
                )
                response(false)
            }
        }
    }
}