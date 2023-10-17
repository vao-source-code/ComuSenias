package com.example.comusenias.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.library.LibraryString
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.state.LoginState
import com.example.comusenias.domain.use_cases.auth.AuthFactoryUseCases
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.example.comusenias.presentation.ui.theme.INVALID_EMAIL
import com.example.comusenias.presentation.ui.theme.emptyString
import com.example.comusenias.presentation.ui.theme.invalidPassword
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCases: AuthFactoryUseCases) :
    ViewModel() {

    var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)
    var state by mutableStateOf(LoginState())
        private set
    var isEmailValid: Boolean by mutableStateOf(false)
    var errorEmail: String by mutableStateOf(EMPTY_STRING)
    var isPasswordValid: Boolean by mutableStateOf(false)
    var errorPassword: String by mutableStateOf(EMPTY_STRING)
    var isLoginEnabled = false
    val currentUser = authUseCases.getCurrentUserUseCase()

    init {
        currentUser?.let { loginResponse = Response.Success(it) }
    }

    fun enabledLoginButton() {
        isLoginEnabled = isEmailValid && isPasswordValid
    }

    fun validateEmail() {
        val isValid = LibraryString.validEmail(state.email)
        isEmailValid = isValid
        errorEmail = if (isValid) emptyString else INVALID_EMAIL
        enabledLoginButton()
    }

    fun validatePassword() {
        val isValid = LibraryString.validPassword(state.password)
        isPasswordValid = isValid
        errorPassword = if (isValid) emptyString else invalidPassword
        enabledLoginButton()
    }

    fun login() = viewModelScope.launch(IO) {
        loginResponse = Response.Loading
        val result = authUseCases.loginUseCase(state.email, state.password)
        loginResponse = result
    }

    fun onEmailInput(email: String) {
        state = state.copy(email = email)
    }

    fun onPasswordInput(password: String) {
        state = state.copy(password = password)
    }
}