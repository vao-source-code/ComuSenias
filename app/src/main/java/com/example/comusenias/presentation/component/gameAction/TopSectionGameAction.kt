package com.example.comusenias.presentation.component.gameAction

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.comusenias.presentation.component.home.ProgressBar
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.CLOSE
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.example.comusenias.presentation.ui.theme.SIZE100
import com.example.comusenias.presentation.ui.theme.SIZE12
import com.example.comusenias.presentation.ui.theme.SIZE2
import com.example.comusenias.presentation.ui.theme.SIZE220
import com.example.comusenias.presentation.ui.theme.SIZE50
import com.example.comusenias.presentation.ui.theme.blackColorApp
import com.example.comusenias.presentation.ui.theme.primaryColorApp
import com.example.comusenias.presentation.ui.theme.size20
import com.example.comusenias.presentation.ui.theme.size24

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
        verticalArrangement = Arrangement.spacedBy(size20.dp)
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
                .size(size24.dp)
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
            fontSize = size20.sp,
            fontWeight = FontWeight.SemiBold,
            color = blackColorApp,
            textAlign = TextAlign.Center,
        )
    )
}

@Composable
fun ContentImageGame(
    image: String,
    urlImage: String = EMPTY_STRING,
    letterSign: String
) {
    val painter = rememberAsyncImagePainter(model = urlImage)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(SIZE220.dp)
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
                    fontSize = SIZE100.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = blackColorApp,
                    textAlign = TextAlign.Center,
                )

            )
        } else {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = image.toInt()),
                contentDescription = EMPTY_STRING,
                contentScale = ContentScale.Fit
            )
        }
    }
}