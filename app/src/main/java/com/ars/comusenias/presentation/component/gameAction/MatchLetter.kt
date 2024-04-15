package com.ars.comusenias.presentation.component.gameAction

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ars.comusenias.constants.TestTag
import com.ars.comusenias.presentation.activities.MainActivity.Companion.getLevelViewModel
import com.ars.comusenias.presentation.extensions.borderStyle.BorderStyleGames
import com.ars.comusenias.presentation.ui.theme.EMPTY_STRING
import com.ars.comusenias.presentation.ui.theme.SIZE1
import com.ars.comusenias.presentation.ui.theme.SIZE12
import com.ars.comusenias.presentation.ui.theme.SIZE120
import com.ars.comusenias.presentation.ui.theme.SIZE150
import com.ars.comusenias.presentation.ui.theme.SIZE50
import com.ars.comusenias.presentation.ui.theme.SIZE15
import com.ars.comusenias.presentation.ui.theme.SIZE20

@Composable
fun MatchLetter(
    singLetter: String,
    randomLetter: String,
    onMatchResult: (Boolean) -> Unit,
) {
    val letters = listOf(singLetter, randomLetter)
    val randomLetters = remember { letters.shuffled() }
    val selectedButtonIndex = remember { mutableStateOf(-1) }

    LazyRow(
        modifier = Modifier
            .fillMaxSize()
            .padding(SIZE12.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        itemsIndexed(randomLetters) { index, text ->
            ButtonLetter(
                text = text,
                index = index,
                selectedButtonIndex = selectedButtonIndex,
            )
            if (index < letters.size - SIZE1) {
                Spacer(Modifier.width(SIZE15.dp))
            }
        }
    }
    getSelectedLetter(selectedButtonIndex, letters)
    onMatchResult(validateButton(selectedButtonIndex))
}

@Composable
fun ButtonLetter(
    text: String,
    index: Int,
    selectedButtonIndex: MutableState<Int>,
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
                getLevelViewModel.onOptionSelected = text
            }
            .testTag(TestTag.TAG_MATCH_SIGN + text)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            text = text.uppercase(),
            style = TextStyle(
                fontSize = if (getLevelViewModel.isVideo) SIZE20.sp else SIZE50.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        )
    }
}

/**
 * Devuelve la letra seleccionada basada en el índice del botón seleccionado.
 *
 * @param selectedButtonIndex Índice del botón seleccionado.
 * @param letters Lista de letras.
 * @return La letra en el índice seleccionado, o una cadena vacía si no se ha seleccionado ningún botón.
 */
@Composable
fun getSelectedLetter(selectedButtonIndex: MutableState<Int>, letters: List<String>): String =
    if (selectedButtonIndex.value != -1) letters[selectedButtonIndex.value] else EMPTY_STRING

/**
 * Valida si se ha seleccionado algún botón.
 *
 * @param selectedButtonIndex Índice del botón seleccionado.
 * @return Verdadero si se ha seleccionado algún botón, falso en caso contrario.
 */
fun validateButton(selectedButtonIndex: MutableState<Int>): Boolean =
    selectedButtonIndex.value != -1