package com.example.comusenias.presentation.component.premium

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.R.drawable.mercada_pago
import com.example.comusenias.presentation.component.defaults.app.ButtonApp
import com.example.comusenias.presentation.ui.theme.IMAGE_JPEG
import com.example.comusenias.presentation.ui.theme.I_WANT_TO_SUSCRIBE
import com.example.comusenias.presentation.ui.theme.PAYMENT_METHODS
import com.example.comusenias.presentation.ui.theme.SIZE1
import com.example.comusenias.presentation.ui.theme.SIZE100
import com.example.comusenias.presentation.ui.theme.SIZE12
import com.example.comusenias.presentation.ui.theme.SIZE20
import com.example.comusenias.presentation.ui.theme.SIZE45
import com.example.comusenias.presentation.ui.theme.SIZE55
import com.example.comusenias.presentation.ui.theme.blackColorApp

@Composable
fun FooterPremium() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SIZE45.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(SIZE12.dp)
        ) {
            Text(
                text = PAYMENT_METHODS,
                style = TextStyle(
                    fontSize = SIZE20.sp,
                    fontWeight = SemiBold,
                    color = blackColorApp,
                    textAlign = Center,
                )
            )
            Box(
                modifier = Modifier
                    .width(SIZE100.dp)
                    .height(SIZE55.dp)
                    .border(
                        width = SIZE1.dp,
                        color = Color(0x4D000000),
                        shape = RoundedCornerShape(size = SIZE12.dp)
                    )
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = painterResource(id = mercada_pago),
                    contentDescription = IMAGE_JPEG,
                    contentScale = Crop
                )
            }
        }
        ButtonApp(
            titleButton = I_WANT_TO_SUSCRIBE,
            onClickButton = { }
        )
    }
}