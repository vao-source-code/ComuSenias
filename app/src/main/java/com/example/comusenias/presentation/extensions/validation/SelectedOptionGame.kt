package com.example.comusenias.presentation.extensions.validation

import com.example.comusenias.presentation.view_model.LevelViewModel

fun selectedOption(letter: String, levelViewModel: LevelViewModel) {
    val isLetterSelected = levelViewModel.onOptionSelected.equals(letter, ignoreCase = true)
    levelViewModel.choiceOfOption.add(isLetterSelected)
}

fun getChoicesSelected(levelViewModel: LevelViewModel): Boolean =
    !levelViewModel.choiceOfOption.any { !it }