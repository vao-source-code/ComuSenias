package com.ars.comusenias.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ars.comusenias.constants.PreferencesConstant.PREFERENCE_ROL_CURRENT
import com.ars.comusenias.domain.library.LibraryString
import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.domain.models.state.LoginState
import com.ars.comusenias.domain.models.users.UserModel
import com.ars.comusenias.domain.use_cases.auth.AuthFactoryUseCases
import com.ars.comusenias.domain.use_cases.shared_preferences.DataRolStorageFactory
import com.ars.comusenias.domain.use_cases.users.UsersFactoryUseCases
import com.ars.comusenias.presentation.ui.theme.EMPTY_STRING
import com.ars.comusenias.presentation.ui.theme.INVALID_EMAIL
import com.ars.comusenias.presentation.ui.theme.invalidPassword
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class LoginViewModel @Inject constructor(
    private val authUseCases: AuthFactoryUseCases,
    private val usersUseCase: UsersFactoryUseCases,
    val dataRolStorageFactory: DataRolStorageFactory
) :
    ViewModel() {

    var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)
    var userResponse by mutableStateOf<Response<UserModel>?>(null)

    var loginReset by mutableStateOf<Response<Boolean>?>(null)

    var state by mutableStateOf(LoginState())
        private set
    var isEmailValid: Boolean by mutableStateOf(false)
    var errorEmail: String by mutableStateOf(EMPTY_STRING)
    var isPasswordValid: Boolean by mutableStateOf(false)
    var errorPassword: String by mutableStateOf(EMPTY_STRING)
    var isLoginEnabled = false
    var currentUser = authUseCases.getCurrentUserUseCase()
    var rol: String by mutableStateOf(EMPTY_STRING)

    init {
        currentUser?.let {
            loginResponse = Response.Success(it)
        }
        onLogin()
    }

    fun onLogin() = viewModelScope.launch(Main) {
        currentUser?.let {
            usersUseCase.getUserByIdUseCase(it.uid).collect { user ->
                dataRolStorageFactory.putRolValue(PREFERENCE_ROL_CURRENT, user.rol)
                rolLogin()
            }
        }
    }


    fun rolLogin() = viewModelScope.launch(IO) {
        rol = dataRolStorageFactory.getRolValue(PREFERENCE_ROL_CURRENT) ?: ""
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
        }else{
            errorEmail = INVALID_EMAIL
        }
    }

    fun onEmailInput(email: String) {
        state = state.copy(email = email.trim())
    }

    fun onPasswordInput(password: String) {
        state = state.copy(password = password.trim())
    }

    fun initRol() = viewModelScope.launch(IO) {
        userResponse = Response.Loading
        currentUser = authUseCases.getCurrentUserUseCase()
        currentUser?.let {
            usersUseCase.getUserByIdUseCase(it.uid).collect { user ->
                dataRolStorageFactory.putRolValue(PREFERENCE_ROL_CURRENT, user.rol)
                userResponse = Response.Success(user)
            }
        }
    }

}