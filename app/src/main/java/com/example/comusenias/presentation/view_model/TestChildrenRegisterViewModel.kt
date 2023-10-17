package com.example.comusenias.presentation.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.library.LibraryPassword
import com.example.comusenias.domain.library.LibraryString
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.state.RegisterState
import com.example.comusenias.domain.models.users.ChildrenModel
import com.example.comusenias.domain.models.users.UserModel
import com.example.comusenias.domain.use_cases.auth.AuthFactoryUseCases
import com.example.comusenias.domain.use_cases.children.ChildrenFactory
import com.example.comusenias.domain.use_cases.users.UsersFactoryUseCases
import com.example.comusenias.presentation.ui.theme.RESTRICTION_PASSWORD_USER_ACCOUNT
import com.example.comusenias.presentation.ui.theme.emptyString
import com.example.comusenias.presentation.ui.theme.invalidEmail
import com.example.comusenias.presentation.ui.theme.restrictionNameUserAccount
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestChildrenRegisterViewModel @Inject constructor(
    private val authUseCases: AuthFactoryUseCases,
    private val usersUseCase: UsersFactoryUseCases,
    private val childrenFactoryUsesCases: ChildrenFactory
) : ViewModel() {

    var registerResponse by mutableStateOf<Response<FirebaseUser>?>(null)
        private set

    var state by mutableStateOf(RegisterState())
        private set

    var stateChildren by mutableStateOf(ChildrenModel())
        private set

    private val _isCheckedRol = mutableStateOf(false)
    val isCheckedRol: State<Boolean> = _isCheckedRol

    var isNameValid: Boolean by mutableStateOf(false)
    var errorName: String by mutableStateOf("")
    var isTelValid: Boolean by mutableStateOf(false)
    var errorTel: String by mutableStateOf("")
    var isDateValid: Boolean by mutableStateOf(false)
    var errorDate: String by mutableStateOf("")
    var isRegisterEnabled = false
    var user = UserModel()

    var childrenModel = ChildrenModel()

    fun register(user: UserModel) = viewModelScope.launch {
        registerResponse = Response.Loading
        val result = authUseCases.registerUseCase(user)
        registerResponse = result

        if (registerResponse is Response.Success) {
            childrenFactoryUsesCases.createChildren(childrenModel)
        }
    }

    fun onRegister() {
        user = UserModel(
            email = state.email,
            password = state.password,
            rol = state.rol
        )

        childrenModel = ChildrenModel(
            name = stateChildren.name,
            tel = stateChildren.tel,
            date = stateChildren.date,
            id = authUseCases.getCurrentUserUseCase()?.uid!!
        )
        register(user)
    }

    private fun enabledRegisterButton() {
        isRegisterEnabled =
            isNameValid && isTelValid && isDateValid
    }

    fun validateName() {
        val isValid = LibraryString.validUserName(state.userName)
        isNameValid = isValid
        errorName = if (isValid) emptyString else restrictionNameUserAccount
        enabledRegisterButton()
    }

    fun validateTel() {
        val isValid = LibraryString.validEmail(state.email)
        isTelValid = isValid
        errorTel = if (isValid) emptyString else invalidEmail
        enabledRegisterButton()
    }

    fun validateDate() {
        val isValid = LibraryString.validPassword(state.password)
        isDateValid = isValid
        errorDate = if (isValid) emptyString else RESTRICTION_PASSWORD_USER_ACCOUNT
        enabledRegisterButton()
    }


    fun createUser() = viewModelScope.launch {
        user.id = authUseCases.getCurrentUserUseCase()?.uid!!
        user.password = LibraryPassword.hashPassword(user.password)
        usersUseCase.createUserUseCase(user)

    }

    fun onNameInputChanged(name: String) {
        stateChildren = stateChildren.copy(name = name)
    }

    fun onTelInputChanged(tel: String) {
        stateChildren = stateChildren.copy(tel = tel)
    }

    fun onDateInputChanged(date: String) {
        stateChildren = stateChildren.copy(date = date)
    }


}