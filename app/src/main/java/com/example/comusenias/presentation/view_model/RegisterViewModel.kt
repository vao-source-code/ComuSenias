package com.example.comusenias.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.library.LibraryPassword
import com.example.comusenias.domain.library.LibraryString
import com.example.comusenias.domain.models.RegisterState
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.User
import com.example.comusenias.domain.use_cases.auth.AuthUseCases
import com.example.comusenias.domain.use_cases.users.UsersUseCase
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val usersUseCase: UsersUseCase
) : ViewModel() {


    var registerResponse by mutableStateOf<Response<FirebaseUser>?>(null)
        private set

    var state by mutableStateOf(RegisterState())
        private set

    var isUserNameValid: Boolean by mutableStateOf(false)
    var errorUserName: String by mutableStateOf("")

    var isEmailValid: Boolean by mutableStateOf(false)
    var errorEmail: String by mutableStateOf("")


    var isPasswordValid: Boolean by mutableStateOf(false)
    var errorPassword: String by  mutableStateOf("")


    var isConfirmPasswordValid: Boolean by mutableStateOf(false)
    var errorConfirmPassword: String by mutableStateOf("")


    var isRegisterEnabled = false

    var user = User()


    fun register(user: User) = viewModelScope.launch {
        registerResponse = Response.Loading
        val result = authUseCases.register(user)
        registerResponse = result
    }

    fun onRegister() {

        user = User(
            userName = state.userName,
            email = state.email,
            password = state.password
        )
        register(user)

    }

    fun enabledRegisterButton() {
        isRegisterEnabled =
            isUserNameValid && isEmailValid && isPasswordValid && isConfirmPasswordValid
    }

    fun validateUserName() {
        if (LibraryString.validUserName(state.userName)) {
            isUserNameValid = true
            errorUserName = ""
        } else {
            isUserNameValid = false
            errorUserName = "El nombre de usuario debe tener al menos 3 caracteres"
        }
        enabledRegisterButton()
    }

    fun validateEmail() {
        if (LibraryString.validEmail(state.email)) {
            isEmailValid = true
            errorEmail = ""
        } else {
            isEmailValid = false
            errorEmail = "El email no es valido"
        }
        enabledRegisterButton()
    }


    fun validatePassword() {
        if (LibraryString.validPassword(state.password)) {
            isPasswordValid = true
            errorPassword = ""
        } else {
            isPasswordValid = false
            errorPassword = "La contraseña debe tener al menos 8 caracteres, una mayúscula y un número"
        }
        enabledRegisterButton()
    }

    fun validateConfirmPassword() {
        if (state.password == state.confirmPassword) {
            isConfirmPasswordValid = true
            errorConfirmPassword = ""
        } else {
            isConfirmPasswordValid = false
            errorConfirmPassword = "Las contraseñas no coinciden"
        }
        enabledRegisterButton()
    }

    fun createUser() = viewModelScope.launch {
        user.id = authUseCases.getCurrentUser()?.uid!!
        user.password = LibraryPassword.hashPassword(user.password)
        usersUseCase.createUser(user)
    }

    fun onEmailInput(email: String) {
        state = state.copy(email = email)
    }

    fun onUserNameInput(userName: String) {
        state = state.copy(userName = userName)
    }

    fun onPasswordInput(password: String) {
        state = state.copy(password = password)
    }

    fun onConfirmPasswordInput(confirmPassword: String) {
        state = state.copy(confirmPassword = confirmPassword)
    }



}