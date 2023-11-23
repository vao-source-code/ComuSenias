package com.example.comusenias.presentation.component.gameAction

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.comusenias.constants.TestTag
import com.example.comusenias.presentation.activities.MainActivity.Companion.getLevelViewModel
import com.example.comusenias.presentation.extensions.borderStyle.BorderStyleGames
import com.example.comusenias.presentation.screen.gameAction.Sign
import com.example.comusenias.presentation.ui.theme.AVATAR
import com.example.comusenias.presentation.ui.theme.SIZE1
import com.example.comusenias.presentation.ui.theme.SIZE12
import com.example.comusenias.presentation.ui.theme.SIZE120
import com.example.comusenias.presentation.ui.theme.SIZE150
import com.example.comusenias.presentation.ui.theme.SIZE15
import com.example.comusenias.util.PlayVideo

@Composable
fun MatchSign(
    sign: Sign,
    randomSign: Sign,
    onMatchResult: (Boolean) -> Unit
) {
    val selectedButtonIndex = remember { mutableStateOf(-1) }

    val signs = listOf(sign, randomSign)
    val randomSignShuffled = remember { signs.shuffled() }

    LazyRow(
        modifier = Modifier
            .fillMaxSize()
            .padding(SIZE12.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        itemsIndexed(randomSignShuffled) { index, image ->
            ButtonSign(
                sign = image,
                index,
                selectedButtonIndex = selectedButtonIndex
            )
            if (index < randomSignShuffled.size - SIZE1) {
                Spacer(Modifier.width(SIZE15.dp))
            }
        }
    }
    onMatchResult(validateButton(selectedButtonIndex))
}

@Composable
fun ButtonSign(
    sign: Sign,
    index: Int,
    selectedButtonIndex: MutableState<Int>
) {
    val borderStyleGames = BorderStyleGames(selectedButtonIndex, index)

    Box(
        modifier = Modifier
            .width(SIZE120.dp)
            .height(SIZE150.dp)
            .border(
                width = borderStyleGames.getBorderWidth(),
                color = borderStyleGames.getBorderColor(),
                shape = RoundedCornerShape(SIZE12.dp)
            )
            .clickable {
                selectedButtonIndex.value = index
                getLevelViewModel.onOptionSelected = sign.imageResource
            }
            .testTag(TestTag.TAG_MATCH_SIGN + sign.letter)
            .absoluteOffset(y = with(LocalDensity.current) { (-2).toDp() })
    ) {
        ShowImageOrVideoDos(sign.imageResource)
    }
}

@Composable
fun ShowImageOrVideoDos(image: String) {
    if (!getLevelViewModel.isVideo) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize(),
            model = image,
            contentScale = ContentScale.Fit,
            contentDescription = AVATAR
        )
    } else {
        PlayVideo(image)
    }
}