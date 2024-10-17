package com.ars.comusenias.presentation.view_model

import android.content.Intent
import android.net.Uri
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
import com.ars.comusenias.domain.models.users.Rol
import com.ars.comusenias.domain.models.users.UserModel
import com.ars.comusenias.domain.use_cases.auth.AuthFactoryUseCases
import com.ars.comusenias.domain.use_cases.shared_preferences.DataUserStorageFactory
import com.ars.comusenias.domain.use_cases.users.UsersFactoryUseCases
import com.ars.comusenias.presentation.ui.theme.EMPTY_STRING
import com.ars.comusenias.presentation.ui.theme.INVALID_EMAIL
import com.ars.comusenias.presentation.ui.theme.PASSWORD_DO_NOT_MATCH
import com.ars.comusenias.presentation.ui.theme.RESTRICTION_NAME_USER_ACCOUNT
import com.ars.comusenias.presentation.ui.theme.RESTRICTION_PASSWORD_USER_ACCOUNT
import com.ars.comusenias.presentation.ui.theme.URL_POLICY_PRIVACY
import com.ars.comusenias.presentation.ui.theme.URL_TERMS_CONDITIONS
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authUseCases: AuthFactoryUseCases,
    private val usersUseCase: UsersFactoryUseCases,
    private val dataUserStorageFactory: DataUserStorageFactory
) : ViewModel() {
    var registerResponse by mutableStateOf<Response<FirebaseUser>?>(null)
        private set

    var state by mutableStateOf(RegisterState())

    var isEmailValid: Boolean by mutableStateOf(false)
    var errorEmail: String by mutableStateOf("")
    var isPasswordValid: Boolean by mutableStateOf(false)
    var errorPassword: String by mutableStateOf("")
    var isConfirmPasswordValid: Boolean by mutableStateOf(false)
    var errorConfirmPassword: String by mutableStateOf("")

    var isSpecialistRole: Boolean by mutableStateOf(false)
    var isRegisterEnabled: Boolean by mutableStateOf(false)

    var isNameValid: Boolean by mutableStateOf(false)
    var errorName: String by mutableStateOf("")

    private var isCheckedTermsPolicy: Boolean by mutableStateOf(false)


    var user = UserModel()

    fun register(user: UserModel) = viewModelScope.launch {
        dataUserStorageFactory.putUserValue(PREFERENCE_USER, user)
    }

    fun onRegister() {
        user = UserModel(
            name= state.name , email = state.email, password = state.password, rol = state.rol
        )
        register(user)
    }

    fun enabledRegisterButton() {
        isRegisterEnabled =
            isEmailValid && isPasswordValid && isConfirmPasswordValid && isNameValid && isCheckedTermsPolicy
    }



    fun validateEmail() {
        val isValid = LibraryString.validEmail(state.email)
        isEmailValid = isValid
        errorEmail = if (isValid) EMPTY_STRING else INVALID_EMAIL
        enabledRegisterButton()
    }

    fun validatePassword() {
        val isValid = LibraryString.validPassword(state.password)
        isPasswordValid = isValid
        errorPassword = if (isValid) EMPTY_STRING else RESTRICTION_PASSWORD_USER_ACCOUNT
        enabledRegisterButton()
    }

    fun validateConfirmPassword() {
        val isValid = state.password == state.confirmPassword
        isConfirmPasswordValid = isValid
        errorConfirmPassword = if (isValid) EMPTY_STRING else PASSWORD_DO_NOT_MATCH
        enabledRegisterButton()
    }

    fun createUser() = viewModelScope.launch {
        user.id = authUseCases.getCurrentUserUseCase()?.uid!!
        user.password = LibraryPassword.hashPassword(user.password)
        usersUseCase.createUserUseCase(user)
    }

    fun onEmailInput(email: String) {
        state = state.copy(email = email.trim())
    }


    fun onPasswordInput(password: String) {
        state = state.copy(password = password.trim())
    }

    fun onConfirmPasswordInput(confirmPassword: String) {
        state = state.copy(confirmPassword = confirmPassword.trim())
    }

    fun onSpecialistRoleInput(isSpecialistRole: Boolean) {
        this.isSpecialistRole = isSpecialistRole
        state = if (isSpecialistRole) {
            state.copy(rol = Rol.SPECIALIST.toString())
        } else {
            state.copy(rol = Rol.CHILDREN.toString())
        }
    }

    fun validateName() {
        val isValid = LibraryString.validUserName(state.name)
        isNameValid = isValid
        errorName = if (isValid) EMPTY_STRING else RESTRICTION_NAME_USER_ACCOUNT
        enabledRegisterButton()
    }

    fun onNameInputChanged(name: String) {
        state = state.copy(name = name)
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