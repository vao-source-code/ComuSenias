package com.example.comusenias.presentation.view_model.specialist

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.comusenias.domain.models.state.ChangeProfileState
import com.example.comusenias.domain.models.users.ChildrenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class ProfilePatientViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    @ApplicationContext private val context: Context
) : ViewModel() {

    var state by mutableStateOf(ChangeProfileState())
        private set


    val data = savedStateHandle.get<String>("pacient")
    val user = ChildrenModel.fromJson(data!!)

}