package com.example.comusenias.presentation.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.constants.PreferencesConstant
import com.example.comusenias.domain.library.LibraryPassword
import com.example.comusenias.domain.library.LibraryString
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.state.RegisterState
import com.example.comusenias.domain.models.users.ChildrenModel
import com.example.comusenias.domain.models.users.UserModel
import com.example.comusenias.domain.use_cases.auth.AuthFactoryUseCases
import com.example.comusenias.domain.use_cases.children.ChildrenFactory
import com.example.comusenias.domain.use_cases.shared_preferences.DataUserStorageFactory
import com.example.comusenias.domain.use_cases.users.UsersFactoryUseCases
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.example.comusenias.presentation.ui.theme.INVALID_DATE
import com.example.comusenias.presentation.ui.theme.INVALID_PHONE
import com.example.comusenias.presentation.ui.theme.RESTRICTION_NAME_USER_ACCOUNT
import com.example.comusenias.presentation.ui.theme.emptyString
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChildrenRegisterViewModel @Inject constructor(
    private val authUseCases: AuthFactoryUseCases,
    private val usersUseCase: UsersFactoryUseCases,
    private val childrenFactoryUsesCases: ChildrenFactory,
    private val dataUserStorageFactoryUseCases: DataUserStorageFactory
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

    init {
        init()
    }

    fun init() = viewModelScope.launch(Dispatchers.IO) {
        user = dataUserStorageFactoryUseCases.getUserValue(PreferencesConstant.PREFERENCE_USER)!!
    }

    fun register(user: UserModel) = viewModelScope.launch {
        registerResponse = Response.Loading
        val result = authUseCases.registerUseCase(user)
        registerResponse = result
    }

    fun onRegister() = viewModelScope.launch {
        user = dataUserStorageFactoryUseCases.getUserValue(PreferencesConstant.PREFERENCE_USER)!!
        register(user)
    }

    private fun enabledRegisterButton() {
        isRegisterEnabled = isNameValid && isTelValid && isDateValid
    }

    fun validateName() {
        val isValid = LibraryString.validUserName(stateChildren.name)
        isNameValid = isValid
        errorName = if (isValid) emptyString else RESTRICTION_NAME_USER_ACCOUNT
        enabledRegisterButton()
    }

    fun validateTel() {
        val isValid = LibraryString.validPhone(stateChildren.tel)
        isTelValid = isValid
        errorTel = if (isValid) EMPTY_STRING else INVALID_PHONE
        enabledRegisterButton()
    }

    fun validateDate() {
        val isValid = stateChildren.date.isNotEmpty()
        isDateValid = isValid
        errorDate = if (isValid) emptyString else INVALID_DATE
        enabledRegisterButton()
    }


    fun createUser() = viewModelScope.launch {
        user.id = authUseCases.getCurrentUserUseCase()?.uid!!
        user.password = LibraryPassword.hashPassword(user.password)
        usersUseCase.createUserUseCase(user)
        childrenModel = ChildrenModel(
            email = user.email,
            name = stateChildren.name,
            tel = stateChildren.tel,
            date = stateChildren.date,
            id = authUseCases.getCurrentUserUseCase()?.uid!!
        )
        childrenFactoryUsesCases.createChildren(childrenModel)

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