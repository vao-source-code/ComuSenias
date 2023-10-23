package com.example.comusenias.presentation.component.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.comusenias.domain.models.game.SubLevelModel
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.example.comusenias.presentation.ui.theme.SIZE12
import com.example.comusenias.presentation.ui.theme.SIZE16
import com.example.comusenias.presentation.ui.theme.SIZE24
import com.example.comusenias.presentation.ui.theme.SIZE27
import com.example.comusenias.presentation.ui.theme.SIZE28
import com.example.comusenias.presentation.ui.theme.SIZE36
import com.example.comusenias.presentation.ui.theme.SIZE90
import com.example.comusenias.presentation.ui.theme.blackColorApp
import com.example.comusenias.presentation.ui.theme.SIZE15

@Composable
fun CardGame(
    lineColor: State<Color>,
    backgroundCard: State<Color>,
    iconColor: State<Color>,
    icon: State<Int>,
    level: String,
    subLevel: SubLevelModel,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .background(backgroundCard.value, shape = RoundedCornerShape(SIZE12.dp))
            .fillMaxWidth()
            .height(SIZE90.dp)
    ) {
        Row(
            modifier = Modifier
                .clickable { navigateToLearSign(navController, level, subLevel) }
                .fillMaxSize()
                .padding(start = SIZE15.dp, end = SIZE27.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(SIZE28.dp)
        ) {
            ImageWhitBorder(image = subLevel.image, borderColor = lineColor)
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = subLevel.name,
                    style = TextStyle(
                        fontSize = SIZE16.sp,
                        lineHeight = SIZE24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = blackColorApp
                    )
                )
                Icon(
                    modifier = Modifier
                        .size(SIZE36.dp),
                    painter = painterResource(id = icon.value),
                    contentDescription = EMPTY_STRING,
                    tint = iconColor.value
                )
            }
        }
    }
}

private fun navigateToLearSign(
    navController: NavController,
    level: String,
    subLevel: SubLevelModel
) {
    navController.navigate(AppScreen.LearnSignScreen.createRoute(level, subLevel.name))
}