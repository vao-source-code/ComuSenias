package com.example.comusenias.presentation.screen.gameAction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.ui.theme.SIZE16
import com.example.comusenias.presentation.ui.theme.SIZE32

@Composable
fun StatisticsScreen() {
    val vowelResults = listOf(
        VowelPerformance("A", 10, 5),
        VowelPerformance("E", 8, 2),
        VowelPerformance("I", 7, 3),
        VowelPerformance("O", 6, 4),
        VowelPerformance("U", 9, 1)
    )

    Column {
        vowelResults.forEach { performance ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = performance.vowel,
                    modifier = Modifier.padding(start = SIZE16.dp),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(SIZE16.dp))
                Column {
                    Box(
                        modifier = Modifier
                            .height(SIZE32.dp)
                            .width((performance.successCount * 16).dp)
                            .background(Color.Green)
                    )
                    Box(
                        modifier = Modifier
                            .height(SIZE32.dp)
                            .width((performance.failureCount * 16).dp)
                            .background(Color.Red)
                    )
                }
            }
            Spacer(modifier = Modifier.height(SIZE16.dp))
        }
    }
}

data class VowelPerformance(
    val vowel: String,
    val successCount: Int,
    val failureCount: Int
)
@Preview()
@Composable
fun DefaultPreview() {
    StatisticsScreen()
}