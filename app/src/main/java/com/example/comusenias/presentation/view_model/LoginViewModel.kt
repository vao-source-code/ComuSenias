package com.example.comusenias.presentation.view_model

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.library.LibraryString
import com.example.comusenias.domain.library.SPManager
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.domain.models.state.LoginState
import com.example.comusenias.domain.use_cases.auth.AuthFactoryUseCases
import com.example.comusenias.domain.use_cases.users.UsersFactoryUseCases
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.example.comusenias.presentation.ui.theme.INVALID_EMAIL
import com.example.comusenias.presentation.ui.theme.invalidPassword
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCases: AuthFactoryUseCases,
    private val usersUseCase: UsersFactoryUseCases,
    @ApplicationContext private val context: Context

) :
    ViewModel() {

    var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)
    var loginReset by mutableStateOf<Response<Boolean>?>(null)

    var state by mutableStateOf(LoginState())
        private set
    var isEmailValid: Boolean by mutableStateOf(false)
    var errorEmail: String by mutableStateOf(EMPTY_STRING)
    var isPasswordValid: Boolean by mutableStateOf(false)
    var errorPassword: String by mutableStateOf(EMPTY_STRING)
    var isLoginEnabled = false
    var currentUser = authUseCases.getCurrentUserUseCase()

    init {
        currentUser?.let {
            loginResponse = Response.Success(it)
        }
        onLogin()
    }

    fun onLogin() = viewModelScope.launch(Main) {
        currentUser?.let {
            usersUseCase.getUserByIdUseCase(it.uid).collect { user ->
                SPManager(context).add(SPManager.ROL, user.rol)
            }
        }
    }




    fun enabledLoginButton() {
        isLoginEnabled = isEmailValid && isPasswordValid
    }

    fun validateEmail() {
        val isValid = LibraryString.validEmail(state.email)
        isEmailValid = isValid
        errorEmail = if (isValid) EMPTY_STRING else INVALID_EMAIL
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

    fun resetPassword() = viewModelScope.launch(IO) {
        if (isEmailValid) {
            loginReset = Response.Loading
            if (isEmailValid) {
                val result = authUseCases.resetPasswordUseCase(state.email)
                loginReset = result
            } else {
                errorEmail = INVALID_EMAIL
            }
        }
    }

    fun onEmailInput(email: String) {
        state = state.copy(email = email)
    }

    fun onPasswordInput(password: String) {
        state = state.copy(password = password)
    }

    fun initRol() = viewModelScope.launch(IO) {
        currentUser = authUseCases.getCurrentUserUseCase()
        currentUser?.let {
            usersUseCase.getUserByIdUseCase(it.uid).collect { user ->
                SPManager(context).add(SPManager.ROL, user.rol)
            }
        }
    }

    fun getRol(): String? {
        return SPManager(context).getString(SPManager.ROL, "")
    }

}