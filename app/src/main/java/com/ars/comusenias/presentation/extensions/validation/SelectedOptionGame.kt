package com.ars.comusenias.presentation.extensions.validation

import com.ars.comusenias.presentation.view_model.LevelViewModel

/**
 * Selecciona una opción basada en la letra proporcionada y añade la elección a la lista de opciones seleccionadas en el ViewModel.
 *
 * @param letter La letra de la opción seleccionada.
 * @param levelViewModel El ViewModel que contiene la opción seleccionada y la lista de opciones seleccionadas.
 */
fun selectedOption(letter: String, levelViewModel: LevelViewModel) {
    val isLetterSelected = levelViewModel.onOptionSelected.equals(letter, ignoreCase = true)
    levelViewModel.choiceOfOption.add(isLetterSelected)
}

/**
 * Verifica si todas las opciones seleccionadas son correctas.
 *
 * @param levelViewModel El ViewModel que contiene la lista de opciones seleccionadas.
 * @return Verdadero si todas las opciones seleccionadas son correctas, falso en caso contrario.
 */
fun getChoicesSelected(levelViewModel: LevelViewModel): Boolean =
    levelViewModel.choiceOfOption.all { it }