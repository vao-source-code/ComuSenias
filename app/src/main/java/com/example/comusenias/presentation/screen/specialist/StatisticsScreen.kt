package com.example.comusenias.presentation.screen.specialist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.ui.theme.SIZE10
import com.example.comusenias.presentation.ui.theme.SIZE14
import com.example.comusenias.presentation.ui.theme.SIZE24
import com.example.comusenias.presentation.ui.theme.SIZE30
import com.example.comusenias.presentation.ui.theme.SIZE7

@Composable
fun StatisticsScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(SIZE24.dp),
        verticalArrangement = Arrangement.spacedBy(SIZE30.dp)
    ) {
        GamePerformance.statistics.forEach { gamePerformance ->
            Column(
                verticalArrangement = Arrangement.spacedBy(SIZE10.dp)
            ) {
                Text(
                    text = gamePerformance.title,
                    fontWeight = FontWeight.Bold
                    )
                StatisticsComponent(letterPerformance = gamePerformance.letterPerformance)
            }
        }
    }

}

@Composable
fun StatisticsComponent(letterPerformance: List<LetterPerformance>){

    Row(
        horizontalArrangement = Arrangement.spacedBy(SIZE14.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        letterPerformance.forEach { performance ->
            Column(
                verticalArrangement = Arrangement.spacedBy(SIZE7.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Box(
                        modifier = Modifier
                            .width(SIZE30.dp)
                            .height((performance.successCount * 16).dp)
                            .background(Color.Green)
                    )
                    Box(
                        modifier = Modifier
                            .width(SIZE30.dp)
                            .height((performance.failureCount * 16).dp)
                            .background(Color.Red)
                    )
                }
                Text(
                    text = performance.letter,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

data class LetterPerformance(
    val letter: String,
    val successCount: Int,
    val failureCount: Int
)

data class GamePerformance(
    val title: String,
    val letterPerformance: List<LetterPerformance>,
) {
    companion object {
        private val lettersResults = listOf(
            LetterPerformance("B", 10, 5),
            LetterPerformance("C", 8, 2),
            LetterPerformance("F", 7, 3),
            LetterPerformance("J", 6, 4),
            LetterPerformance("T", 9, 1)
        )

        private val vowelResults = listOf(
            LetterPerformance("A", 10, 5),
            LetterPerformance("E", 8, 2),
            LetterPerformance("I", 7, 3),
            LetterPerformance("O", 6, 4),
            LetterPerformance("U", 9, 1)
        )

        val statistics = listOf(
            GamePerformance("Vocales", vowelResults),
            GamePerformance("Abecedario", lettersResults)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StatisticsScreen()
}