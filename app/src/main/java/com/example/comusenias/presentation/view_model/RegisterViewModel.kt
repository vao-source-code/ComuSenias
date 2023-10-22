package com.example.comusenias.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.library.LibraryPassword
import com.example.comusenias.domain.library.LibraryString
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.users.UserModel
import com.example.comusenias.domain.models.state.RegisterState
import com.example.comusenias.domain.use_cases.auth.AuthFactoryUseCases
import com.example.comusenias.domain.use_cases.users.UsersFactoryUseCases
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
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
    private val authUseCases: AuthFactoryUseCases, private val usersUseCase: UsersFactoryUseCases
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
    var errorPassword: String by mutableStateOf("")
    var isConfirmPasswordValid: Boolean by mutableStateOf(false)
    var errorConfirmPassword: String by mutableStateOf("")
    var isRegisterEnabled = true
    var user = UserModel()

    fun register(user: UserModel) = viewModelScope.launch {
        registerResponse = Response.Loading
        val result = authUseCases.registerUseCase(user)
        registerResponse = result
    }

    fun onRegister() {
        user = UserModel(
            userName = state.userName, email = state.email, password = state.password
        )
        register(user)

    }

    private fun enabledRegisterButton() {
        isRegisterEnabled = isUserNameValid && isEmailValid && isPasswordValid && isConfirmPasswordValid
    }

    fun validateUserName() {
        val isValid = LibraryString.validUserName(state.userName)
        isUserNameValid = isValid
        errorUserName = if (isValid) EMPTY_STRING else restrictionNameUserAccount
        enabledRegisterButton()
    }

    fun validateEmail() {
        val isValid = LibraryString.validEmail(state.email)
        isEmailValid = isValid
        errorEmail = if (isValid) EMPTY_STRING else invalidEmail
        enabledRegisterButton()
    }

    fun validatePassword() {
        val isValid = LibraryString.validPassword(state.password)
        isPasswordValid = isValid
        errorPassword = if (isValid) EMPTY_STRING else restrictionPasswordUserAccount
        enabledRegisterButton()
    }

    fun validateConfirmPassword() {
        val isValid = state.password == state.confirmPassword
        isConfirmPasswordValid = isValid
        errorConfirmPassword = if (isValid) EMPTY_STRING else passwordDoNotMatch
        enabledRegisterButton()
    }

    fun createUser() = viewModelScope.launch {
        user.id = authUseCases.getCurrentUserUseCase()?.uid!!
        user.password = LibraryPassword.hashPassword(user.password)
        usersUseCase.createUserUseCase(user)
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