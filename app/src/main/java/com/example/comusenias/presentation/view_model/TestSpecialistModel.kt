package com.example.comusenias.presentation.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.constants.PreferencesConstant.PREFERENCE_USER
import com.example.comusenias.domain.library.LibraryPassword
import com.example.comusenias.domain.library.LibraryString
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.state.RegisterState
import com.example.comusenias.domain.models.users.SpecialistModel
import com.example.comusenias.domain.models.users.UserModel
import com.example.comusenias.domain.use_cases.auth.AuthFactoryUseCases
import com.example.comusenias.domain.use_cases.test.DataUserStorageFactory
import com.example.comusenias.domain.use_cases.users.UsersFactoryUseCases
import com.example.comusenias.presentation.ui.theme.emptyString
import com.example.comusenias.presentation.ui.theme.restrictionNameUserAccount
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TestSpecialistModel @Inject constructor(
    private val authUseCases: AuthFactoryUseCases,
    private val usersUseCase: UsersFactoryUseCases,
    private val dataUserStorageFactoryUseCases: DataUserStorageFactory,
) : ViewModel() {
    var registerResponse by mutableStateOf<Response<FirebaseUser>?>(null)
        private set

    var stateSpecialist by mutableStateOf(SpecialistModel())
        private set

    var state by mutableStateOf(RegisterState())
        private set


    private val _isCheckedRol = mutableStateOf(false)
    val isCheckedRol: State<Boolean> = _isCheckedRol

    var isNameValid: Boolean by mutableStateOf(false)
    var errorName: String by mutableStateOf("")

    var isMedicalLicenseValid: Boolean by mutableStateOf(false)
    var errorMedicalLicense: String by mutableStateOf("")

    var isMedicalLicenseExpirationValid: Boolean by mutableStateOf(false)
    var errormedicalLicenseExpiration: String by mutableStateOf("")

    var isSpecialityValid: Boolean by mutableStateOf(false)
    var errorSpeciality: String by mutableStateOf("")

    var isTitleMedical: Boolean by mutableStateOf(false)
    var errorTitleMedical: String by mutableStateOf("")

    var isDate: Boolean by mutableStateOf(false)
    var errorDate: String by mutableStateOf("")

    var isTelephone: Boolean by mutableStateOf(false)
    var errorTelephone: String by mutableStateOf("")


    var isRegisterEnabled = false
    var userSpecialist = SpecialistModel()
    var user = UserModel()

    init {
        init()
    }


    fun init() = viewModelScope.launch(IO) {
        user = dataUserStorageFactoryUseCases.getUserValue(PREFERENCE_USER)!!
    }

    fun register(user: UserModel) = viewModelScope.launch {
        registerResponse = Response.Loading
        val result = authUseCases.registerUseCase(user)
        registerResponse = result

        if (result is Response.Success) {

        }
    }

    fun onRegister() {
        user = UserModel(
            email = state.email,
            password = state.password,
            rol = state.rol
        )
        register(user)
    }

    private fun enabledRegisterButton() {
        isRegisterEnabled =
            isNameValid && isNameValid && isSpecialityValid && isMedicalLicenseExpirationValid &&
                    isMedicalLicenseValid && isTitleMedical && isDate && isTelephone
    }

    fun validateName() {
        val isValid = LibraryString.validUserName(stateSpecialist.name)
        isNameValid = isValid
        errorName = if (isValid) emptyString else restrictionNameUserAccount
        enabledRegisterButton()
    }


    fun createUser() = viewModelScope.launch {
        user.id = authUseCases.getCurrentUserUseCase()?.uid!!
        user.password = LibraryPassword.hashPassword(user.password)
        usersUseCase.createUserUseCase(user)
    }


}