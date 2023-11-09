package com.example.comusenias.presentation.component.premium

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.domain.models.premiumBenefits.PremiumBenefits
import com.example.comusenias.presentation.ui.theme.EXPERIENCE_COMPLETED
import com.example.comusenias.presentation.ui.theme.SIZE1
import com.example.comusenias.presentation.ui.theme.SIZE12
import com.example.comusenias.presentation.ui.theme.SIZE20
import com.example.comusenias.presentation.ui.theme.blackColorApp

@Composable
fun ContentBenefitsPremium() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = SIZE1.dp,
                color = Color(0x4D000000),
                shape = RoundedCornerShape(size = SIZE12.dp)
            )
    ) {
        Spacer(modifier = Modifier.height(SIZE20.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = EXPERIENCE_COMPLETED,
            style = TextStyle(
                fontSize = SIZE20.sp,
                fontWeight = SemiBold,
                color = blackColorApp,
                textAlign = Center,
            )
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(SIZE20.dp),
            verticalArrangement = spacedBy(SIZE20.dp)
        ) {
            PremiumBenefits.get().forEach {
                BenefitsPremiumItem(image = it.image, title = it.title, description = it.text)
            }
        }
    }
}