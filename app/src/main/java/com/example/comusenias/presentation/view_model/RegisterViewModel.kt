package com.example.comusenias.presentation.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.example.comusenias.domain.library.LibraryString
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.User
import com.example.comusenias.domain.use_cases.auth.AuthUseCases
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.example.comusenias.presentation.ui.theme.invalidEmail
import com.example.comusenias.presentation.ui.theme.passwordDoNotMatch
import com.example.comusenias.presentation.ui.theme.restrictionNameUserAccount
import com.example.comusenias.presentation.ui.theme.restrictionPasswordUserAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authUseCases: AuthUseCases) : ViewModel() {

    var userName: MutableState<String> = mutableStateOf("")
    var isUserNameValid: MutableState<Boolean> = mutableStateOf(false)
    var errorUserName: MutableState<String> = mutableStateOf("")

    var email: MutableState<String> = mutableStateOf("")
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var errorEmail: MutableState<String> = mutableStateOf("")

    var password: MutableState<String> = mutableStateOf("")
    var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var errorPassword: MutableState<String> = mutableStateOf("")

    var confirmPassword: MutableState<String> = mutableStateOf("")
    var isConfirmPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var errorConfirmPassword: MutableState<String> = mutableStateOf("")

    var isRegisterEnabled = false

    private val _registerFlow = MutableStateFlow<Response<FirebaseUser>?>(null)
    val registerFlow: StateFlow<Response<FirebaseUser>?> = _registerFlow

    fun register(user: User) = viewModelScope.launch {
        _registerFlow.value = Response.Loading
        val result = authUseCases.register(user)
        _registerFlow.value = result
    }

    fun onRegister() {
        val user = User(
            userName = userName.value,
            email = email.value,
            password = password.value
        )
        register(user)
    }

    private fun enabledRegisterButton() {
        isRegisterEnabled =
            isUserNameValid.value && isEmailValid.value && isPasswordValid.value && isConfirmPasswordValid.value
    }

    fun validateUserName() {
        val isValid = LibraryString.validUserName(userName.value)
        isUserNameValid.value = isValid
        errorUserName.value =
            if (isValid) EMPTY_STRING else restrictionNameUserAccount
        enabledRegisterButton()
    }

    fun validateEmail() {
        val isValid = LibraryString.validEmail(email.value)
        isEmailValid.value = isValid
        errorEmail.value = if (isValid) EMPTY_STRING else invalidEmail
        enabledRegisterButton()
    }

    fun validatePassword() {
        val isValid = LibraryString.validPassword(password.value)
        isPasswordValid.value = isValid
        errorPassword.value = if (isValid) EMPTY_STRING else restrictionPasswordUserAccount
        enabledRegisterButton()
    }

    fun validateConfirmPassword() {
        val isValid = password.value == confirmPassword.value
        isConfirmPasswordValid.value = isValid
        errorConfirmPassword.value = if (isValid) EMPTY_STRING else passwordDoNotMatch
        enabledRegisterButton()
    }
}