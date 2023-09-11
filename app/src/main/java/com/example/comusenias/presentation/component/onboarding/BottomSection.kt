package com.example.comusenias.presentation.component.onboarding

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.comusenias.constants.TestTag
import com.example.comusenias.presentation.ui.theme.primaryColorApp

@Composable
fun BottomSection(
    size: Int,
    index: Int,
    onNextClicked:()->Unit
) {
    Box(
        modifier= Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ){
        Indicators(size = size, index = index)

        FloatingActionButton(
            onClick =onNextClicked,
            modifier=Modifier
                .align(CenterEnd)
                .testTag(TestTag.TAG_BUTTON_NEXT),
            backgroundColor = Color.Black,
            contentColor = Color.White
        ) {
            Icon(Icons.Outlined.KeyboardArrowRight,null)
        }
    }
}

@Composable
fun BoxScope.Indicators(size:Int, index:Int){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier.align(Alignment.CenterStart)
    ) {
        repeat(size){
            Indicator(isSelected = it == index)
        }
    }
}

@Composable
fun Indicator(isSelected:Boolean){
    val width= animateDpAsState(
        targetValue = if (isSelected) 16.dp else 13.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy), label = ""
    )
    Box(
        modifier = Modifier
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                if (isSelected) Color.Black
                else primaryColorApp
            )
            .testTag(TestTag.TAG_INDICATOR+ isSelected)
    ){
    }
}