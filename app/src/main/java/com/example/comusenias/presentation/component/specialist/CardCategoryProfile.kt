package com.example.comusenias.presentation.component.specialist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.presentation.ui.theme.CIRCLE_IMAGE
import com.example.comusenias.presentation.ui.theme.SIZE100
import com.example.comusenias.presentation.ui.theme.SIZE3
import com.example.comusenias.presentation.ui.theme.SIZE10
import com.example.comusenias.presentation.ui.theme.size150
import com.example.comusenias.presentation.ui.theme.size20
import com.example.comusenias.presentation.ui.theme.size24
import com.example.comusenias.presentation.ui.theme.size40

@Composable
fun CardHomeSpecialist(image: Int, title: String, subtitle: String, onClickCard: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(size150.dp)
            .padding(size20.dp)
            .clickable {
                onClickCard()
            }
            .shadow(
                elevation = SIZE3.dp,
                shape = RoundedCornerShape(SIZE10.dp),
                spotColor = Color(0x33000000),
                ambientColor = Color(0x33000000)
            )
            .shadow(
                elevation = SIZE3.dp,
                shape = RoundedCornerShape(SIZE10.dp),
                spotColor = Color(0x21000000),
                ambientColor = Color(0x21000000)
            )
            .background(Color.White, shape = RoundedCornerShape(SIZE10.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.spacedBy(size40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircleImageView(image = image)

            Column() {
                TitleCardCategoryProfile(title = title)
                SubTitleCardCategoryProfile(title = subtitle)
            }
        }
    }
}

@Composable
fun CircleImageView(image: Int) {
    Image(
        painter = painterResource(image),
        contentDescription = CIRCLE_IMAGE,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(SIZE100.dp)
    )
}

@Composable
fun TitleCardCategoryProfile(title: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = title,
        color = Color.Black,
        fontSize = size24.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun SubTitleCardCategoryProfile(title: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = title,
        color = Color.Black,
        fontSize = size20.sp,
    )
}

