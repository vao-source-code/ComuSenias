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
import com.example.comusenias.presentation.ui.theme.emptyString
import com.example.comusenias.presentation.ui.theme.invalidEmail
import com.example.comusenias.presentation.ui.theme.passwordDoNotMatch
import com.example.comusenias.presentation.ui.theme.restrictionNameUserAccount
import com.example.comusenias.presentation.ui.theme.restrictionPasswordUserAccount
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
        val isValid = LibraryString.validUserName(state.userName)
        isUserNameValid = isValid
        errorUserName =
            if (isValid) emptyString else restrictionNameUserAccount
        enabledRegisterButton()
    }

    fun validateEmail() {
        val isValid = LibraryString.validEmail(state.email)
        isEmailValid = isValid
        errorEmail = if (isValid) emptyString else invalidEmail
        enabledRegisterButton()
    }

    fun validatePassword() {
        val isValid = LibraryString.validPassword(state.password)
        isPasswordValid = isValid
        errorPassword = if (isValid) emptyString else restrictionPasswordUserAccount
        enabledRegisterButton()
    }

    fun validateConfirmPassword() {
        val isValid = state.password == state.confirmPassword
        isConfirmPasswordValid = isValid
        errorConfirmPassword = if (isValid) emptyString else passwordDoNotMatch
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