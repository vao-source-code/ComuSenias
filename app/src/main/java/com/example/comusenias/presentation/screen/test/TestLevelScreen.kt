package com.example.comusenias.presentation.screen.test

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

import com.example.comusenias.presentation.view_model.LevelViewModel

@Composable
fun TestLevelScreen(viewModel: LevelViewModel = hiltViewModel()) {

    Log.d("TestScreen", viewModel.levelsResponse.toString())


}