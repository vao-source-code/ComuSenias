package com.example.comusenias.presentation.screen.plays

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.comusenias.R
import com.example.comusenias.presentation.component.defaults.app.ButtonApp
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.CONTINUE
import com.example.comusenias.presentation.ui.theme.DESC_IMAGE_SIGN
import com.example.comusenias.presentation.ui.theme.SIZE12
import com.example.comusenias.presentation.ui.theme.SIZE16
import com.example.comusenias.presentation.ui.theme.SIZE2
import com.example.comusenias.presentation.ui.theme.SIZE300
import com.example.comusenias.presentation.ui.theme.SIZE60
import com.example.comusenias.presentation.ui.theme.START_WITH_LETTER
import com.example.comusenias.presentation.ui.theme.primaryColorApp
import com.example.comusenias.presentation.ui.theme.size48

@Composable
fun LearnSignScreen(navController: NavHostController, modifier: Modifier) {
    LearnSignView(navController = navController, modifier)
}

@Composable
fun LearnSignView(navController: NavHostController, modifier: Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Box(
                modifier = modifier
                    .padding(top = SIZE16.dp, start = SIZE16.dp, end = SIZE16.dp)
                    .border(
                        SIZE2.dp,
                        primaryColorApp,
                        shape = MaterialTheme.shapes.medium
                    )
                    .height(SIZE300.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.letra_o),
                    contentDescription = DESC_IMAGE_SIGN,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Text(
                text = START_WITH_LETTER,
                style = MaterialTheme.typography.h6,
            )
            Text(
                modifier = Modifier
                    .padding(top = SIZE12.dp),
                fontSize = size48.sp,
                text = "O",
                style = MaterialTheme.typography.h6,
                color = primaryColorApp,
            )
            Box( modifier = Modifier.fillMaxSize()
                .padding(bottom = 20.dp),
                contentAlignment = Alignment.BottomCenter ){
                ButtonApp(
                    titleButton = CONTINUE,
                    onClickButton = { navController.navigate(AppScreen.ChoseTheSignPlayScreen.route) },
                    modifier = modifier.padding(top = SIZE60.dp, start = SIZE16.dp, end = SIZE16.dp)
                )
            }

        }
    }
}