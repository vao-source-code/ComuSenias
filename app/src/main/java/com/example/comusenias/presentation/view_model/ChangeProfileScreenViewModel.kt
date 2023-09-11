package com.example.comusenias.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.comusenias.domain.models.ChangeProfileState

class ChangeProfileScreenViewModel {

    var state by mutableStateOf(ChangeProfileState())
        private set



}