package com.example.comusenias.presentation.component.gameAction

import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.comusenias.presentation.component.home.ProgressBar
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.AVATAR
import com.example.comusenias.presentation.ui.theme.CLOSE
import com.example.comusenias.presentation.ui.theme.SIZE100
import com.example.comusenias.presentation.ui.theme.SIZE12
import com.example.comusenias.presentation.ui.theme.SIZE2
import com.example.comusenias.presentation.ui.theme.SIZE20
import com.example.comusenias.presentation.ui.theme.SIZE220
import com.example.comusenias.presentation.ui.theme.SIZE24
import com.example.comusenias.presentation.ui.theme.SIZE50
import com.example.comusenias.presentation.ui.theme.blackColorApp
import com.example.comusenias.presentation.ui.theme.primaryColorApp
import com.example.comusenias.util.PlayVideo
import com.example.comusenias.presentation.activities.MainActivity.Companion.getLevelViewModel
import com.example.comusenias.presentation.ui.theme.DISCULPA
import com.example.comusenias.presentation.ui.theme.PERMISO

@OptIn(UnstableApi::class)
@Composable
fun TopSectionGameAction(
    letterSign: String,
    title: String,
    image: String,
    currentStep: Int,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(SIZE20.dp)
    ) {
        CloseAndProgressBar(currentStep = currentStep, navController = navController)
        TitleGameAction(title = title)
        ContentImageGame(
            image = image,
            letterSign = letterSign
        )
    }
}

@Composable
fun CloseAndProgressBar(currentStep: Int = 0, navController: NavHostController) {
    val totalSteps = 5
    val progress = currentStep / totalSteps.toFloat()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(SIZE50.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = CLOSE,
            modifier = Modifier
                .size(SIZE24.dp)
                .clickable { navController.navigate(AppScreen.HomeScreen.route) },
        )
        ProgressBar(progress = progress)
    }
}

@Composable
fun TitleGameAction(title: String) {
    Text(
        text = title,
        style = TextStyle(
            fontSize = SIZE20.sp,
            fontWeight = FontWeight.SemiBold,
            color = blackColorApp,
            textAlign = TextAlign.Center,
        )
    )
}

@Composable
fun ContentImageGame(
    image: String,
    letterSign: String
) {
    val height = if (getLevelViewModel.isVideo) SIZE220.dp else SIZE220.dp
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .border(
                width = SIZE2.dp,
                color = primaryColorApp,
                shape = RoundedCornerShape(size = SIZE12.dp)
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(size = SIZE12.dp)
            ),
    ) {
        if (letterSign.isNotEmpty()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                text = letterSign.uppercase(),
                style = TextStyle(
                    fontSize = if (getLevelViewModel.subLevelSelected == DISCULPA || getLevelViewModel.subLevelSelected == PERMISO) 70.sp else SIZE100.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = blackColorApp,
                    textAlign = TextAlign.Center,
                )
            )
        } else {
            ShowImageOrVideo(image)
        }
    }
}

@Composable
fun ShowImageOrVideo(image: String) {
    if (!getLevelViewModel.isVideo) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize(),
            model = image,
            contentScale = ContentScale.Fit,
            contentDescription = AVATAR
        )
    } else {
        PlayVideo(videoUrl = image, isVideoYoutube = false)
    }
}