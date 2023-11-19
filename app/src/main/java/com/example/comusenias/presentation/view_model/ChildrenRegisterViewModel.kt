package com.example.comusenias.presentation.view_model

import android.content.Intent
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.constants.PreferencesConstant
import com.example.comusenias.domain.library.LibraryPassword
import com.example.comusenias.domain.library.LibraryString
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.domain.models.state.RegisterState
import com.example.comusenias.domain.models.users.ChildrenModel
import com.example.comusenias.domain.models.users.UserModel
import com.example.comusenias.domain.use_cases.auth.AuthFactoryUseCases
import com.example.comusenias.domain.use_cases.children.ChildrenFactory
import com.example.comusenias.domain.use_cases.shared_preferences.DataUserStorageFactory
import com.example.comusenias.domain.use_cases.users.UsersFactoryUseCases
import com.example.comusenias.presentation.activities.MainActivity.Companion.getLevelViewModel
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.example.comusenias.presentation.ui.theme.INVALID_DATE
import com.example.comusenias.presentation.ui.theme.INVALID_PHONE
import com.example.comusenias.presentation.ui.theme.RESTRICTION_NAME_USER_ACCOUNT
import com.example.comusenias.presentation.ui.theme.URL_POLICY_PRIVACY
import com.example.comusenias.presentation.ui.theme.URL_TERMS_CONDITIONS
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
    var isCheckedTermsPolicy: Boolean by mutableStateOf(false)
    var isNameValid: Boolean by mutableStateOf(false)
    var errorName: String by mutableStateOf("")
    var isTelValid: Boolean by mutableStateOf(false)
    var errorTel: String by mutableStateOf("")
    var isDateValid: Boolean by mutableStateOf(false)
    var errorDate: String by mutableStateOf("")
    var isRegisterEnabled: Boolean by mutableStateOf(false)
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
        isRegisterEnabled = isNameValid && isTelValid && isDateValid && isCheckedTermsPolicy
    }

    fun validateName() {
        val isValid = LibraryString.validUserName(stateChildren.name)
        isNameValid = isValid
        errorName = if (isValid) EMPTY_STRING else RESTRICTION_NAME_USER_ACCOUNT
        enabledRegisterButton()
    }

    fun validateTel() {
        val isValid = LibraryString.validPhone(stateChildren.phone)
        isTelValid = isValid
        errorTel = if (isValid) EMPTY_STRING else INVALID_PHONE
        enabledRegisterButton()
    }

    fun validateDate() {
        val isValid = stateChildren.date.isNotEmpty()
        isDateValid = isValid
        errorDate = if (isValid) EMPTY_STRING else INVALID_DATE
        enabledRegisterButton()
    }

    fun createUser() = viewModelScope.launch {
        user.id = authUseCases.getCurrentUserUseCase()?.uid!!
        user.password = LibraryPassword.hashPassword(user.password)
        usersUseCase.createUserUseCase(user)
        childrenModel = ChildrenModel(
            id = authUseCases.getCurrentUserUseCase()?.uid!!,
            name = stateChildren.name,
            phone = stateChildren.phone,
            email = user.email,
            date = stateChildren.date,
            levels = getLevelViewModel.levels,
        )
        childrenFactoryUsesCases.createChildren(childrenModel)
    }

    fun onNameInputChanged(name: String) {
        stateChildren = stateChildren.copy(name = name)
    }

    fun onTelInputChanged(tel: String) {
        stateChildren = stateChildren.copy(phone = tel)
    }

    fun onDateInputChanged(date: String) {
        stateChildren = stateChildren.copy(date = date)
    }

    fun onClickTerms(openLink: ActivityResultLauncher<Intent>) {
        val uri = Uri.parse(URL_TERMS_CONDITIONS)
        val customTabsIntent = CustomTabsIntent.Builder().setShowTitle(false).build()
        val intent = customTabsIntent.intent
        intent.data = uri
        openLink.launch(intent)
    }

    fun onClickConditions(openLink: ActivityResultLauncher<Intent>) {
        val uri = Uri.parse(URL_POLICY_PRIVACY)
        val customTabsIntent = CustomTabsIntent.Builder().setShowTitle(false).build()
        val intent = customTabsIntent.intent
        intent.data = uri
        openLink.launch(intent)
    }

    fun onCheckTermsAndConditions(check: Boolean) {
        this.isCheckedTermsPolicy = check
        enabledRegisterButton()
    }
}