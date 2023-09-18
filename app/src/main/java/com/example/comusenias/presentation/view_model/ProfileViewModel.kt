package com.example.comusenias.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.models.User
import com.example.comusenias.domain.use_cases.auth.AuthUseCases
import com.example.comusenias.domain.use_cases.users.UsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUsesCases: AuthUseCases, private val useCases: UsersUseCase
) : ViewModel() {


    var userData by mutableStateOf(User())
        private set

    val currentUser = authUsesCases.getCurrentUser()

    init {
        getUserData()
    }

    private fun getUserData() = viewModelScope.launch {
        currentUser?.let {
            useCases.getUserById(it.uid).collect() { user ->
                userData = user
            }
        }
    }

    fun logout() {
        authUsesCases.logout()
    }
}