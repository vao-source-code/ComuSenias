package com.example.comusenias.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.library.LibraryString
import com.example.comusenias.domain.models.ChangeProfileState
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.User
import com.example.comusenias.domain.use_cases.users.UsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangeProfileViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val usersUseCase: UsersUseCase
) :
    ViewModel() {

    var state by mutableStateOf(ChangeProfileState())
        private set

    var isUserNameValid: Boolean by mutableStateOf(false)
    var errorUserName: String by mutableStateOf("")

    val data = savedStateHandle.get<String>("user")
    val user = User.fromJson(data!!)

    var updateResponse by mutableStateOf<Response<Boolean>?>(null)
        private set


    init {
        state = state.copy(userName = user.userName)
    }

    fun updateUser(user: User) = viewModelScope.launch {
        updateResponse = Response.Loading
        val result = usersUseCase.updateUser(user)
        updateResponse = result
    }

    fun onUpdate() {
        val user = User(id = user.id, userName = state.userName, image = "")
        updateUser(user)
    }

    fun onUserNameChange(userName: String) {
        state = state.copy(userName = userName)
    }


    fun validateUserName() {
        if (LibraryString.validUserName(state.userName)) {
            isUserNameValid = true
            errorUserName = ""
        } else {
            isUserNameValid = false
            errorUserName = "El nombre de usuario debe tener al menos 3 caracteres"
        }
    }

}