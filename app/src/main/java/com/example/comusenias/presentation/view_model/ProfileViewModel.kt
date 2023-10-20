package com.example.comusenias.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.models.users.ChildrenModel
import com.example.comusenias.domain.use_cases.auth.AuthFactoryUseCases
import com.example.comusenias.domain.use_cases.children.ChildrenFactory
import com.example.comusenias.domain.use_cases.users.UsersFactoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUsesCases: AuthFactoryUseCases, private val useCases: UsersFactoryUseCases,
    private val childrenUser: ChildrenFactory
) : ViewModel() {

    var userData by mutableStateOf(ChildrenModel())
        private set
    val currentUser = authUsesCases.getCurrentUserUseCase()

    init {
        getUserData()
    }

    private fun getUserData() = viewModelScope.launch {
        currentUser?.let {
            childrenUser.getChildrenById(it.uid).collect { user ->
                userData = user
            }
        }
    }

    fun logout() {
        authUsesCases.logoutUseCase()
    }
}