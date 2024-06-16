package com.ars.comusenias.presentation.component.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ars.comusenias.presentation.ui.theme.CHECK_POINT
import com.ars.comusenias.presentation.ui.theme.CONSULT_WHIT_ESPECIALIST
import com.ars.comusenias.presentation.ui.theme.EMPTY_STRING
import com.ars.comusenias.presentation.ui.theme.SIZE12
import com.ars.comusenias.presentation.ui.theme.SIZE15
import com.ars.comusenias.presentation.ui.theme.SIZE16
import com.ars.comusenias.presentation.ui.theme.SIZE18
import com.ars.comusenias.presentation.ui.theme.SIZE28
import com.ars.comusenias.presentation.ui.theme.SIZE7
import com.ars.comusenias.presentation.ui.theme.SIZE90
import com.ars.comusenias.presentation.ui.theme.blackColorApp
import com.ars.comusenias.presentation.ui.theme.cardColorCheckPoint
import com.ars.comusenias.presentation.ui.theme.cardGray

@Preview(showBackground = true)
@Composable
fun CardGameCheckPoint() {
    val border = animateColorAsState(cardGray, label = EMPTY_STRING)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(SIZE90.dp)
            .background(cardColorCheckPoint, shape = RoundedCornerShape(SIZE12.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = SIZE15.dp, end = SIZE18.dp),
            verticalAlignment = CenterVertically,
            horizontalArrangement = spacedBy(SIZE28.dp)
        ) {
            ImageWhitBorder(
                image = "",
                borderColor = border,
                border = 0
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = spacedBy(SIZE7.dp)
            ) {
                Text(
                    text = CHECK_POINT,
                    style = TextStyle(
                        fontSize = SIZE16.sp,
                        fontWeight = SemiBold,
                        color = blackColorApp
                    )
                )
                Text(
                    text = CONSULT_WHIT_ESPECIALIST,
                    style = TextStyle(
                        fontSize = SIZE12.sp,
                        fontWeight = Normal,
                        color = blackColorApp
                    )
                )
            }
        }
    }
}