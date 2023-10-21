package com.example.comusenias.presentation.component.specialist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.comusenias.domain.models.card_data.CardData
import com.example.comusenias.presentation.ui.theme.SIZE16
import com.example.comusenias.presentation.ui.theme.SIZE8
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.sp
import com.example.comusenias.R
import com.example.comusenias.presentation.ui.theme.DATE_One
import com.example.comusenias.presentation.ui.theme.CARD_DESCRIPTION
import com.example.comusenias.presentation.ui.theme.SIZE14

// Se definio el font "Lato"
private val LatoFontFamily = FontFamily(
    Font(R.font.lato_regular),
    Font(R.font.lato_bold, FontWeight.Bold)
)

@Composable
fun Screen() {
    val cardList = listOf(
        CardData(DATE_One, CARD_DESCRIPTION),
        CardData(DATE_One, CARD_DESCRIPTION),
        CardData(DATE_One, CARD_DESCRIPTION),
        CardData(DATE_One, CARD_DESCRIPTION),
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(cardList) { index, card ->
            CardItem(card)
            if (index < cardList.size - 1) {
                Divider()
            }
        }
    }
}

@Composable
fun CardItem(card: CardData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Handle card click event */ },
        elevation = SIZE8.dp
    ) {
        Column(
            modifier = Modifier.padding(SIZE16.dp)
        ) {
            Text(
                text = card.date,
                style = MaterialTheme.typography.subtitle1.copy(fontFamily = LatoFontFamily, fontWeight = FontWeight.Bold,fontSize = SIZE14.sp),
                modifier = Modifier.padding(bottom = SIZE8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = card.description,
                style = MaterialTheme.typography.body1.copy(fontFamily = LatoFontFamily,fontSize = SIZE14.sp),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun Divider() {
    androidx.compose.material.Divider(
        modifier = Modifier.padding(horizontal = SIZE16.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )
}

@Preview
@Composable
fun ScreenPreview() {
    Screen()
}