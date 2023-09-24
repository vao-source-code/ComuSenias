package com.example.comusenias.presentation.component.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.ui.theme.SIZE12
import com.example.comusenias.presentation.ui.theme.cardGray
import com.example.comusenias.presentation.ui.theme.size19
import com.example.comusenias.presentation.ui.theme.size5
import com.example.comusenias.presentation.ui.theme.size50

@Preview(showBackground = true)
@Composable
fun ContentCardGame() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .blur(radius = 0.dp)
    ) {
        CardGame()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = size50.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(size19.dp)
                    .width(size5.dp)
                    .background(color = cardGray, shape = RoundedCornerShape(SIZE12.dp))
            )
        }
    }
}