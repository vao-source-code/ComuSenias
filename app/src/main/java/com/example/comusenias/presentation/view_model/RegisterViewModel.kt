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
    val registerFlow : StateFlow<Response<FirebaseUser>?> =_registerFlow
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

    fun enabledRegisterButton() {
        isRegisterEnabled =
            isUserNameValid.value && isEmailValid.value && isPasswordValid.value && isConfirmPasswordValid.value
    }

    fun validateUserName() {
        if (LibraryString.validUserName(userName.value)) {
            isUserNameValid.value = true
            errorUserName.value = ""
        } else {
            isUserNameValid.value = false
            errorUserName.value = "El nombre de usuario debe tener al menos 3 caracteres"
        }
        enabledRegisterButton()
    }

    fun validateEmail() {
        if (LibraryString.validEmail(email.value)) {
            isEmailValid.value = true
            errorEmail.value = ""
        } else {
            isEmailValid.value = false
            errorEmail.value = "El email no es valido"
        }
        enabledRegisterButton()
    }


    fun validatePassword() {
        if (LibraryString.validPassword(password.value)) {
            isPasswordValid.value = true
            errorPassword.value = ""
        } else {
            isPasswordValid.value = false
            errorPassword.value = "La contraseña debe tener al menos 8 caracteres"
        }
        enabledRegisterButton()
    }

    fun validateConfirmPassword() {
        if (password.value == confirmPassword.value) {
            isConfirmPasswordValid.value = true
            errorConfirmPassword.value = ""
        } else {
            isConfirmPasswordValid.value = false
            errorConfirmPassword.value = "Las contraseñas no coinciden"
        }
        enabledRegisterButton()
    }
}