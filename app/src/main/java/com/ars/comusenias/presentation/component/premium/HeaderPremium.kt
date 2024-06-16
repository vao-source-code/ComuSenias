package com.ars.comusenias.presentation.component.premium

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ars.comusenias.presentation.ui.theme.PRICE_SUSCRIBER
import com.ars.comusenias.presentation.ui.theme.SIZE12
import com.ars.comusenias.presentation.ui.theme.SIZE20
import com.ars.comusenias.presentation.ui.theme.SUSCRIBER
import com.ars.comusenias.presentation.ui.theme.blackColorApp

@Composable
fun HeaderPremium() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = spacedBy(SIZE12.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = SUSCRIBER,
            style = TextStyle(
                fontSize = SIZE20.sp,
                fontWeight = SemiBold,
                color = blackColorApp,
                textAlign = Center,
            )
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = PRICE_SUSCRIBER,
            style = TextStyle(
                fontSize = SIZE20.sp,
                fontWeight = SemiBold,
                color = blackColorApp,
                textAlign = Center,
            )
        )
    }
}