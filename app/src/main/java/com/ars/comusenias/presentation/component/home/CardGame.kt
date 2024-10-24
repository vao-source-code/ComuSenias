package com.ars.comusenias.presentation.component.home

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Arrangement.spacedBy
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
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ars.comusenias.data.repositories.firebase.GestureRecognizerHelper
import com.ars.comusenias.domain.models.game.SubLevelModel
import com.ars.comusenias.presentation.component.home.StatusGame.BLOCKED
import com.ars.comusenias.presentation.navigation.AppScreen
import com.ars.comusenias.presentation.ui.theme.EMPTY_STRING
import com.ars.comusenias.presentation.ui.theme.SIZE1
import com.ars.comusenias.presentation.ui.theme.SIZE12
import com.ars.comusenias.presentation.ui.theme.SIZE15
import com.ars.comusenias.presentation.ui.theme.SIZE16
import com.ars.comusenias.presentation.ui.theme.SIZE2
import com.ars.comusenias.presentation.ui.theme.SIZE24
import com.ars.comusenias.presentation.ui.theme.SIZE27
import com.ars.comusenias.presentation.ui.theme.SIZE28
import com.ars.comusenias.presentation.ui.theme.SIZE36
import com.ars.comusenias.presentation.ui.theme.SIZE90
import com.ars.comusenias.presentation.ui.theme.blackColorApp

@Composable
fun CardGame(
    lineColor: State<Color>,
    backgroundCard: State<Color>,
    iconColor: State<Color>,
    icon: State<Int>,
    level: String,
    subLevel: SubLevelModel,
    navController: NavController,
    status: StatusGame
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .background(backgroundCard.value, shape = RoundedCornerShape(SIZE12.dp))
            .fillMaxWidth()
            .height(SIZE90.dp)
            .padding(SIZE2.dp),
        elevation = SIZE1.dp
    ) {
        Row(
            modifier = Modifier
                .clickable(enabled = status != BLOCKED) {
                    navigateToLearSign(navController, level, subLevel,context)
                }
                .fillMaxSize()
                .padding(start = SIZE15.dp, end = SIZE27.dp),
            verticalAlignment = CenterVertically,
            horizontalArrangement = spacedBy(SIZE28.dp)
        ) {
            ImageWhitBorder(image = subLevel.image, borderColor = lineColor)
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = SpaceBetween,
                verticalAlignment = CenterVertically
            ) {
                Text(
                    text = subLevel.name,
                    style = TextStyle(
                        fontSize = SIZE16.sp,
                        lineHeight = SIZE24.sp,
                        fontWeight = SemiBold,
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
    subLevel: SubLevelModel,
    context: Context
) {
    val gesture = GestureRecognizerHelper(context = context)
    //TODO pasarle (ojo con la resposabilidad) el level y el sublevel para que decida que tasks debe usar
    gesture.setupGestureRecognizer(subLevel.idLevel)
    navController.navigate(AppScreen.LearnSignScreen.createRoute(level, subLevel.name))

}