package com.ars.comusenias.presentation.view_model.specialist

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ars.comusenias.constants.PreferencesConstant.PREFERENCE_USER
import com.ars.comusenias.domain.library.LibraryPassword
import com.ars.comusenias.domain.library.LibraryString
import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.domain.models.state.RegisterState
import com.ars.comusenias.domain.models.users.SpecialistModel
import com.ars.comusenias.domain.models.users.UserModel
import com.ars.comusenias.domain.use_cases.auth.AuthFactoryUseCases
import com.ars.comusenias.domain.use_cases.shared_preferences.DataUserStorageFactory
import com.ars.comusenias.domain.use_cases.specialist.SpecialistFactory
import com.ars.comusenias.domain.use_cases.users.UsersFactoryUseCases
import com.ars.comusenias.presentation.ui.theme.EMPTY_STRING
import com.ars.comusenias.presentation.ui.theme.INVALID_DATE
import com.ars.comusenias.presentation.ui.theme.INVALID_PHONE
import com.ars.comusenias.presentation.ui.theme.RESTRICTION_NAME_USER_ACCOUNT
import com.ars.comusenias.presentation.ui.theme.RESTRICTION_SPECIALIST_ACCOUNT
import com.ars.comusenias.presentation.ui.theme.RESTRICTION_TITLE_MEDICAL_ACCOUNT
import com.ars.comusenias.presentation.ui.theme.URL_POLICY_PRIVACY
import com.ars.comusenias.presentation.ui.theme.URL_TERMS_CONDITIONS
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SpecialistRegisterViewModel @Inject constructor(
    private val authUseCases: AuthFactoryUseCases,
    private val usersUseCase: UsersFactoryUseCases,
    private val specialistUseCases: SpecialistFactory,
    private val dataUserStorageFactoryUseCases: DataUserStorageFactory,
) : ViewModel() {

    /*---------------------------------public variable ---------------------------------*/
    var registerResponse by mutableStateOf<Response<FirebaseUser>?>(null)
        private set
    var stateSpecialist by mutableStateOf(SpecialistModel())
        private set
    var state by mutableStateOf(RegisterState())
        private set
    var errorName: String by mutableStateOf("")
    var errorMedicalLicense: String by mutableStateOf("")
    var errorSpeciality: String by mutableStateOf("")
    var errorTitleMedical: String by mutableStateOf("")
    var errorTelephone: String by mutableStateOf("")
    var isRegisterEnabled: Boolean by mutableStateOf(false)
    var user = UserModel()

    /*---------------------------------private variable ---------------------------------*/
    private var isCheckedTermsPolicy: Boolean by mutableStateOf(false)
    private var isNameValid: Boolean by mutableStateOf(false)
    private var isMedicalLicenseValid: Boolean by mutableStateOf(false)
    private var isMedicalLicenseExpirationValid: Boolean by mutableStateOf(false)
    private var errorMedicalLicenseExpiration: String by mutableStateOf("")
    private var isSpecialityValid: Boolean by mutableStateOf(false)
    private var isTitleMedical: Boolean by mutableStateOf(false)
    private var isDate: Boolean by mutableStateOf(false)
    private var errorDate: String by mutableStateOf("")
    private var isTelValid: Boolean by mutableStateOf(false)
    private var specialistModel = SpecialistModel()

    init {
        init()
    }

    /*---------------------------------private function ---------------------------------*/
    private fun enabledRegisterButton() {
        isRegisterEnabled =
            isNameValid && isSpecialityValid && isMedicalLicenseExpirationValid &&
                    isMedicalLicenseValid && isTitleMedical && isDate && isTelValid && isCheckedTermsPolicy
    }

    /*---------------------------------public function ---------------------------------*/
    fun init() = viewModelScope.launch(Main) {
        user = dataUserStorageFactoryUseCases.getUserValue(PREFERENCE_USER)!!
        Log.d("SpecialistRegisterViewModel", "Usuario: ${user.toString()}")
    }

    fun register(user: UserModel) = viewModelScope.launch {
        registerResponse = Response.Loading
        val result = authUseCases.registerUseCase(user)
        registerResponse = result
    }

    fun onRegister() = viewModelScope.launch {
        user = dataUserStorageFactoryUseCases.getUserValue(PREFERENCE_USER)!!
        register(user)
    }

    fun createUser() = viewModelScope.launch {
        user.id = authUseCases.getCurrentUserUseCase()?.uid!!
        user.password = LibraryPassword.hashPassword(user.password)
        usersUseCase.createUserUseCase(user)
        specialistModel = SpecialistModel(
            id = authUseCases.getCurrentUserUseCase()?.uid!!,
            email = user.email,
            date = stateSpecialist.date,
            name = stateSpecialist.name,
            tel = stateSpecialist.tel,
            medicalLicense = stateSpecialist.medicalLicense,
            medicalLicenseExpiration = stateSpecialist.medicalLicenseExpiration,
            speciality = stateSpecialist.speciality,
            titleMedical = stateSpecialist.titleMedical,
        )
        specialistUseCases.createSpecialist(specialistModel)
    }


    /*---------------------------------validation ---------------------------------*/

    fun validateName() {
        val isValid = LibraryString.validUserName(stateSpecialist.name)
        isNameValid = isValid
        errorName = if (isValid) EMPTY_STRING else RESTRICTION_NAME_USER_ACCOUNT
        enabledRegisterButton()
    }

    fun validateSpeciality() {
        val isValid = stateSpecialist.speciality.isNotEmpty()
        isSpecialityValid = isValid
        errorSpeciality = if (isValid) EMPTY_STRING else RESTRICTION_SPECIALIST_ACCOUNT
        enabledRegisterButton()
    }

    fun validateMedicalLicense() {
        val isValid = stateSpecialist.medicalLicense.isNotEmpty()
        isMedicalLicenseValid = isValid
        errorMedicalLicenseExpiration = if (isValid) EMPTY_STRING else RESTRICTION_NAME_USER_ACCOUNT
        enabledRegisterButton()
    }

    fun validateTel() {
        val isValid = LibraryString.validPhone(stateSpecialist.tel)
        isTelValid = isValid
        errorTelephone = if (isValid) EMPTY_STRING else INVALID_PHONE
        enabledRegisterButton()
    }

    fun validateDate() {
        val isValid = LibraryString.validDateOfBirth(stateSpecialist.date)
        isDate = isValid
        errorDate = if (isValid) EMPTY_STRING else INVALID_DATE
        enabledRegisterButton()
    }

    fun validateMedicalLicenseExpirationValid() {
        val isValid =
            LibraryString.validateRegistrationExpirationDate(stateSpecialist.medicalLicenseExpiration)
        isMedicalLicenseExpirationValid = isValid
        errorMedicalLicenseExpiration = if (isValid) EMPTY_STRING else INVALID_DATE
        enabledRegisterButton()
    }

    fun validateTitleMedical() {
        val isValid = stateSpecialist.titleMedical.isNotEmpty()
        isTitleMedical = isValid
        errorTitleMedical = if (isValid) EMPTY_STRING else RESTRICTION_TITLE_MEDICAL_ACCOUNT
        enabledRegisterButton()
    }

    /*---------------------------------on change ---------------------------------*/

    fun onNameInputChanged(name: String) {
        stateSpecialist = stateSpecialist.copy(name = name)
    }

    fun onTelInputChanged(tel: String) {
        stateSpecialist = stateSpecialist.copy(tel = tel.trim())
    }

    fun onDateInputChanged(date: String) {
        stateSpecialist = stateSpecialist.copy(date = date)
    }

    fun onMedicalLicenseInputChanged(medicalLicense: String) {
        stateSpecialist = stateSpecialist.copy(medicalLicense = medicalLicense)
    }

    fun onMedicalLicenseExpirationInputChanged(medicalLicenseExpiration: String) {
        stateSpecialist = stateSpecialist.copy(medicalLicenseExpiration = medicalLicenseExpiration)
    }

    fun onSpecialityInputChanged(speciality: String) {
        stateSpecialist = stateSpecialist.copy(speciality = speciality)
    }

    fun onTitleMedicalInputChanged(titleMedical: String) {
        stateSpecialist = stateSpecialist.copy(titleMedical = titleMedical)
    }

    fun onClickTerms(openLink: ActivityResultLauncher<Intent>) {
        val uri = Uri.parse(URL_TERMS_CONDITIONS)
        val customTabsIntent = CustomTabsIntent.Builder()
            .setShowTitle(false) // Ocultar el título de la página
            .build()
        val intent = customTabsIntent.intent
        intent.data = uri
        openLink.launch(intent)
    }

    fun onClickConditions(openLink: ActivityResultLauncher<Intent>) {
        val uri = Uri.parse(URL_POLICY_PRIVACY)
        val customTabsIntent = CustomTabsIntent.Builder()
            .setShowTitle(false) // Ocultar el título de la página
            .build()
        val intent = customTabsIntent.intent
        intent.data = uri
        openLink.launch(intent)
    }

    fun onCheckTermsAndConditions(check: Boolean) {
        this.isCheckedTermsPolicy = check
        enabledRegisterButton()
    }

}