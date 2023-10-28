package com.example.comusenias.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.models.users.UserModel
import com.example.comusenias.domain.models.auth.AuthFactory
import com.example.comusenias.domain.use_cases.users.UsersFactoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUsesCases: AuthFactory, private val useCases: UsersFactoryUseCases
) : ViewModel() {

    var userData by mutableStateOf(UserModel())
        private set
    val currentUser = authUsesCases.getCurrentUserUseCase()

    init {
        getUserData()
    }

    private fun getUserData() = viewModelScope.launch {
        currentUser?.let {
            useCases.getUserByIdUseCase(it.uid).collect { user ->
                userData = user
            }
        }
    }

    fun logout() {
        authUsesCases.logoutUseCase()
    }
}