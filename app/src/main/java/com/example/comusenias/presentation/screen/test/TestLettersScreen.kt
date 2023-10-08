package com.example.comusenias.presentation.screen.test

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.comusenias.constants.AlphabetConstants
import com.example.comusenias.presentation.view_model.LettersViewModel

@Composable
fun TestLettersScreen(viewModel: LettersViewModel = hiltViewModel()) {
    viewModel.searchSpecificLetter(AlphabetConstants.Letter.L.toString())
    AsyncImage(model = viewModel.letters.image, contentDescription = "")
}