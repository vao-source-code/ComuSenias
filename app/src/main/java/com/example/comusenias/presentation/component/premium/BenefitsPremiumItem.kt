package com.example.comusenias.presentation.component.premium

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.presentation.ui.theme.IMAGE
import com.example.comusenias.presentation.ui.theme.SIZE10
import com.example.comusenias.presentation.ui.theme.SIZE12
import com.example.comusenias.presentation.ui.theme.SIZE14
import com.example.comusenias.presentation.ui.theme.SIZE2
import com.example.comusenias.presentation.ui.theme.SIZE40
import com.example.comusenias.presentation.ui.theme.blackColorApp

@Composable
fun BenefitsPremiumItem(
    image: Int,
    title: String,
    description: String
) {
    Row(
        modifier = Modifier.fillMaxWidth().height(SIZE40.dp),
        horizontalArrangement = spacedBy(SIZE10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.width(SIZE40.dp),
            painter = painterResource(id = image),
            contentDescription = IMAGE,
            contentScale = Crop
        )
        Column(
            modifier = Modifier.fillMaxHeight(),
        ) {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = SIZE14.sp,
                    fontWeight = SemiBold,
                    color = blackColorApp,
                )
            )
            Text(
                text = description,
                style = TextStyle(
                    fontSize = SIZE12.sp,
                    fontWeight = Normal,
                    color = blackColorApp,
                )
            )
        }
    }
}