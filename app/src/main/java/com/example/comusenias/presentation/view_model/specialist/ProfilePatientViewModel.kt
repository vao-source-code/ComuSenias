package com.example.comusenias.presentation.view_model.specialist

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.comusenias.domain.library.ResultingActivityHandler
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.domain.models.state.ChangeProfileState
import com.example.comusenias.domain.models.users.ChildrenModel
import com.example.comusenias.domain.use_cases.users.UsersFactoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfilePatientViewModel @Inject constructor(
    private var savedStateHandle: SavedStateHandle,
    private val usersUseCase: UsersFactoryUseCases,
    @ApplicationContext private val context: Context
) : ViewModel() {

    var state by mutableStateOf(ChangeProfileState())
        private set


    val data = savedStateHandle.get<String>("pacient")
    val user = ChildrenModel.fromJson(data!!)

    var updateResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    val resultingActivityHandler = ResultingActivityHandler()
    var saveImageResponse by mutableStateOf<Response<String>?>(null)
        private set
    var file: File? = null

}