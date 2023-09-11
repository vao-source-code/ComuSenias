package com.example.comusenias.presentation.view_model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.library.LibraryString
import com.example.comusenias.domain.models.LoginState
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.use_cases.auth.AuthUseCases
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCases: AuthUseCases): ViewModel() {

    var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)


    var state by mutableStateOf(LoginState())
        private set


    var isEmailValid: Boolean by mutableStateOf(false)
    var errorEmail :  String  by mutableStateOf("")

    var isPasswordValid : Boolean by  mutableStateOf(false)
    var errorPassword: String by  mutableStateOf("")

    var isLoginEnabled = false

    val currentUser = authUseCases.getCurrentUser()



    init{
        if (currentUser != null) {
            loginResponse = Response.Success(currentUser)
        }
    }



    fun enabledLoginButton() {
        isLoginEnabled =  isEmailValid && isPasswordValid
    }
    fun validateEmail() {
        if (LibraryString.validEmail(state.email)) {
            isEmailValid = true
            errorEmail = ""
            Log.d("LoginViewModel", "validateEmail: ${isEmailValid}")
        }else{
            isEmailValid = false
            errorEmail = "Email no es valido"
            Log.d("LoginViewModel", "validateEmail: ${isEmailValid}")
        }
        enabledLoginButton()

    }

    fun validatePassword(){
        if (LibraryString.validPassword(state.password)) {
            isPasswordValid = true
            errorPassword = ""
        }else{
            isPasswordValid = false
            errorPassword = "Password no es valido"
        }
        enabledLoginButton()
    }

    /*-------------------corrutinas -------------------------------------------------*/

    fun login () = viewModelScope.launch(IO) {
        loginResponse = Response.Loading
        val result = authUseCases.login(state.email, state.password)
        loginResponse = result

    }

    fun onEmailInput( email : String){
        state = state.copy(email = email)
    }

    fun onPasswordInput( password : String){
        state = state.copy(password = password)
    }
}