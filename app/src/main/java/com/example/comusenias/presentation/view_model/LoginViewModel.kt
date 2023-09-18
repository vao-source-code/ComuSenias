package com.example.comusenias.presentation.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.example.comusenias.domain.library.LibraryString
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.use_cases.auth.AuthUseCases
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.example.comusenias.presentation.ui.theme.invalidEmail
import com.example.comusenias.presentation.ui.theme.invalidPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCases: AuthUseCases) : ViewModel() {

    private val _loginFlow = MutableStateFlow<Response<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Response<FirebaseUser>?> = _loginFlow

    var email: MutableState<String> = mutableStateOf("")
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var errorEmail: MutableState<String> = mutableStateOf("")

    var password: MutableState<String> = mutableStateOf("")
    var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var errorPassword: MutableState<String> = mutableStateOf("")

    var isLoginEnabled = false

    private val currentUser = authUseCases.getCurrentUser()

    init {
        if (currentUser != null) {
            _loginFlow.value = Response.Success(currentUser)
        }
    }

    private fun enabledLoginButton() {
        isLoginEnabled = isEmailValid.value && isPasswordValid.value
    }

    fun validateEmail() {
        val isValid = LibraryString.validEmail(email.value)
        isEmailValid.value = isValid
        errorEmail.value = if (isValid) EMPTY_STRING else invalidEmail
        enabledLoginButton()
    }

    fun validatePassword() {
        val isValid = LibraryString.validPassword(password.value)
        isPasswordValid.value = isValid
        errorPassword.value = if (isValid) EMPTY_STRING else invalidPassword
        enabledLoginButton()
    }

    /*-------------------coroutines-------------------------------------------------*/
    fun login() = viewModelScope.launch(IO) {
        _loginFlow.value = Response.Loading
        val result = authUseCases.login(email.value, password.value)
        _loginFlow.value = result
    }
}