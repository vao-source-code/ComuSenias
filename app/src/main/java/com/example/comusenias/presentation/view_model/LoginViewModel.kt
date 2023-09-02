package com.example.comusenias.presentation.view_model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.example.comusenias.domain.LibraryString
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.use_cases.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCases: AuthUseCases): ViewModel() {

    private val _loginFlow = MutableStateFlow<Response<FirebaseUser>?>(null)
    val loginFlow : StateFlow<Response<FirebaseUser>?> = _loginFlow


    var email: MutableState<String> = mutableStateOf("")
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var errorEmail: MutableState<String> = mutableStateOf("")

    var password: MutableState<String> = mutableStateOf("")
    var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var errorPassword: MutableState<String> = mutableStateOf("")

    var isLoginEnabled = false

    val currentUser = authUseCases.getCurrentUser()
    init{
        if (currentUser != null) {
            _loginFlow.value = Response.Success(currentUser)
        }
    }
    fun enabledLoginButton() {
        isLoginEnabled =  isEmailValid.value && isPasswordValid.value
    }
    fun validateEmail() {
        if (LibraryString.validEmail(email.value)) {
            isEmailValid.value = true
            errorEmail.value = ""
            Log.d("LoginViewModel", "validateEmail: ${isEmailValid.value}")
        }else{
            isEmailValid.value = false
            errorEmail.value = "Email no es valido"
            Log.d("LoginViewModel", "validateEmail: ${isEmailValid.value}")
        }
        enabledLoginButton()

    }

    fun validatePassword(){
        if (LibraryString.validPassword(password.value)) {
            isPasswordValid.value = true
            errorPassword.value = ""
            Log.d("LoginViewModel", "validatePassword: ${isPasswordValid.value}")
        }else{
            isPasswordValid.value = false
            errorPassword.value = "Password no es valido"
            Log.d("LoginViewModel", "validatePassword: ${isPasswordValid.value}")
        }
        enabledLoginButton()
    }

    /*-------------------corrutinas -------------------------------------------------*/

    fun login () = viewModelScope.launch(IO) {
        _loginFlow.value = Response.Loading
        val result = authUseCases.login(email.value, password.value)
        _loginFlow.value = result

    }
}