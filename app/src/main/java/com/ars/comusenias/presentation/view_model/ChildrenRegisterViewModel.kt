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
import com.ars.comusenias.constants.PreferencesConstant
import com.ars.comusenias.domain.library.LibraryPassword
import com.ars.comusenias.domain.library.LibraryString
import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.domain.models.state.RegisterState
import com.ars.comusenias.domain.models.users.ChildrenModel
import com.ars.comusenias.domain.models.users.UserModel
import com.ars.comusenias.domain.use_cases.auth.AuthFactoryUseCases
import com.ars.comusenias.domain.use_cases.children.ChildrenFactory
import com.ars.comusenias.domain.use_cases.shared_preferences.DataUserStorageFactory
import com.ars.comusenias.domain.use_cases.users.UsersFactoryUseCases
import com.ars.comusenias.presentation.activities.MainActivity.Companion.getLevelViewModel
import com.ars.comusenias.presentation.ui.theme.EMPTY_STRING
import com.ars.comusenias.presentation.ui.theme.INVALID_DATE
import com.ars.comusenias.presentation.ui.theme.INVALID_PHONE
import com.ars.comusenias.presentation.ui.theme.RESTRICTION_NAME_USER_ACCOUNT
import com.ars.comusenias.presentation.ui.theme.URL_POLICY_PRIVACY
import com.ars.comusenias.presentation.ui.theme.URL_TERMS_CONDITIONS
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


    fun createUser() = viewModelScope.launch {
        user.id = authUseCases.getCurrentUserUseCase()?.uid!!
        user.password = LibraryPassword.hashPassword(user.password)
        usersUseCase.createUserUseCase(user)
        childrenModel = ChildrenModel(
            id = authUseCases.getCurrentUserUseCase()?.uid!!,
            name = user.name,
            email = user.email,
            levels = getLevelViewModel.levels,
        )
        childrenFactoryUsesCases.createChildren(childrenModel)
    }

}