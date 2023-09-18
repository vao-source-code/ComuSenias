package com.example.comusenias.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.library.LibraryString
import com.example.comusenias.domain.models.LoginState
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.use_cases.auth.AuthUseCases
import com.example.comusenias.presentation.ui.theme.emptyString
import com.example.comusenias.presentation.ui.theme.invalidEmail
import com.example.comusenias.presentation.ui.theme.invalidPassword
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCases: AuthUseCases) : ViewModel() {

    var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)
    var state by mutableStateOf(LoginState())
        private set

    var isEmailValid: Boolean by mutableStateOf(false)
    var errorEmail: String by mutableStateOf("")

    var isPasswordValid: Boolean by mutableStateOf(false)
    var errorPassword: String by mutableStateOf("")

    var isLoginEnabled = false

    val currentUser = authUseCases.getCurrentUser()


    init {
        if (currentUser != null) {
            loginResponse = Response.Success(currentUser)
        }
    }

    fun enabledLoginButton() {
        isLoginEnabled = isEmailValid && isPasswordValid
    }

    fun validateEmail() {
        val isValid = LibraryString.validEmail(state.email)
        isEmailValid = isValid
        errorEmail = if (isValid) emptyString else invalidEmail
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
        val result = authUseCases.login(state.email, state.password)
        loginResponse = result

    }

    fun onEmailInput(email: String) {
        state = state.copy(email = email)
    }

    fun onPasswordInput(password: String) {
        state = state.copy(password = password)
    }
}