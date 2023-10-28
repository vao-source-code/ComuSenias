package com.example.comusenias.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.library.LibraryString
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.domain.models.state.LoginState
import com.example.comusenias.domain.models.auth.AuthFactory
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.example.comusenias.presentation.ui.theme.invalidEmail
import com.example.comusenias.presentation.ui.theme.invalidPassword
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCases: AuthFactory) :
    ViewModel() {

    var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)
    var state by mutableStateOf(LoginState())
        private set
    private var isEmailValid: Boolean by mutableStateOf(false)
    var errorEmail: String by mutableStateOf(EMPTY_STRING)
    private var isPasswordValid: Boolean by mutableStateOf(false)
    var errorPassword: String by mutableStateOf(EMPTY_STRING)
    var isLoginEnabled = false
    private val currentUser = authUseCases.getCurrentUserUseCase()

    init {
        currentUser?.let { loginResponse = Response.Success(it) }
    }

    private fun enabledLoginButton() {
        isLoginEnabled = isEmailValid && isPasswordValid
    }

    fun validateEmail() {
        val isValid = LibraryString.validEmail(state.email)
        isEmailValid = isValid
        errorEmail = if (isValid) EMPTY_STRING else invalidEmail
        enabledLoginButton()
    }

    fun validatePassword() {
        val isValid = LibraryString.validPassword(state.password)
        isPasswordValid = isValid
        errorPassword = if (isValid) EMPTY_STRING else invalidPassword
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