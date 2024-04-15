package com.ars.comusenias.presentation.view_model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class BottomBarViewModel @Inject constructor()  : ViewModel() {
    val isBottomAppBarVisible = mutableStateOf(true)
}