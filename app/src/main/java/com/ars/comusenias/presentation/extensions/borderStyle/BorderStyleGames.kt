package com.ars.comusenias.presentation.extensions.borderStyle

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ars.comusenias.presentation.ui.theme.EMPTY_STRING
import com.ars.comusenias.presentation.ui.theme.SIZE2
import com.ars.comusenias.presentation.ui.theme.SIZE5
import com.ars.comusenias.presentation.ui.theme.borderButtonLetter
import com.ars.comusenias.presentation.ui.theme.secondaryColorApp

/**
 * Clase para manejar los cambios de estilo en los juegos de bordes.
 *
 * @property selectedButtonIndex Índice del botón seleccionado.
 * @property index Índice del botón actual.
 */
class BorderStyleGames(
    private var selectedButtonIndex: MutableState<Int>,
    var index: Int
) {

    /**
     * Devuelve el color del borde basado en si el botón actual está seleccionado o no.
     *
     * @return Color del borde.
     */
    @Composable
    fun getBorderColor(): Color {
        val isSelected = selectedButtonIndex.value == index
        return animateColorAsState(
            if (isSelected) secondaryColorApp else borderButtonLetter,
            label = EMPTY_STRING
        ).value
    }

    /**
     * Devuelve el ancho del borde basado en si el botón actual está seleccionado o no.
     *
     * @return Ancho del borde.
     */
    @Composable
    fun getBorderWidth(): Dp {
        val isSelected = selectedButtonIndex.value == index
        return animateDpAsState(
            if (isSelected) SIZE5.dp else SIZE2.dp,
            label = EMPTY_STRING
        ).value
    }
}