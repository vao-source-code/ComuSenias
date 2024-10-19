package com.ars.comusenias.presentation.component.home

import android.graphics.Color
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ars.comusenias.R
import com.ars.comusenias.presentation.ui.theme.IMAGE_AVATAR
import com.ars.comusenias.presentation.ui.theme.SELECTED_IMAGE
import com.ars.comusenias.presentation.ui.theme.SIZE40
import com.ars.comusenias.presentation.ui.theme.SIZE45
import com.ars.comusenias.presentation.ui.theme.SIZE70
import com.ars.comusenias.presentation.ui.theme.backgroundPrimaryColor
import com.ars.comusenias.presentation.ui.theme.borderColorTextFieldFocused

import com.ars.comusenias.presentation.ui.theme.softBlueHighlight
import com.ars.comusenias.presentation.view_model.ChildrenProfileViewModel


@Composable
fun CircleWithProfileImage(viewModel: ChildrenProfileViewModel) {
    // Ajustar el tamaño del contenedor
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(SIZE70.dp) // Tamaño total del círculo con bordes
    ) {

        // Dibujar los bordes circulares con Canvas
        Canvas(modifier = Modifier.fillMaxSize()) {
            val outerRadius = size.minDimension / 2
            val middleRadius = size.minDimension / 2.1f
            val innerRadius = size.minDimension / 2.5f
            val center = size.minDimension / 2.3f

            drawCircle(
                color = softBlueHighlight,
                radius = outerRadius
            )

            drawCircle(
                color =  backgroundPrimaryColor,
                radius = center,
                style = Stroke(width = 10f)
            )

            drawCircle(
                color = borderColorTextFieldFocused,
                radius = middleRadius,
                style = Stroke(width = 10f)
            )
            drawCircle(
                color = borderColorTextFieldFocused,
                radius = innerRadius,
                style = Stroke(width = 8f)
            )
        }

        if (viewModel.userData.image?.isNotEmpty() == true) {
            AsyncImage(
                modifier = Modifier
                    .size(SIZE45.dp) // Tamaño de la imagen circular
                    .clip(CircleShape), contentScale = ContentScale.Crop,
                model = viewModel.userData.image, contentDescription = SELECTED_IMAGE
            )
        } else {
            Image(
                modifier = Modifier
                    .size(SIZE40.dp) // Tamaño de la imagen circular
                    .clip(CircleShape),
                contentScale = ContentScale.Fit,
                painter = painterResource(id = R.drawable.profile_avatar),
                contentDescription = IMAGE_AVATAR,
            )
        }

    }
}