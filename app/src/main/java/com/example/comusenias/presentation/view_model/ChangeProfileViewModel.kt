package com.example.comusenias.presentation.view_model

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.library.ComposeFileProvider
import com.example.comusenias.domain.library.LibraryString
import com.example.comusenias.domain.library.ResultingActivityHandler
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.domain.models.users.UserModel
import com.example.comusenias.domain.models.state.ChangeProfileState
import com.example.comusenias.domain.use_cases.users.UsersFactoryUseCases
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.example.comusenias.presentation.ui.theme.PATH_IMAGE
import com.example.comusenias.presentation.ui.theme.restrictionNameUserAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ChangeProfileViewModel @Inject constructor(
    private var savedStateHandle: SavedStateHandle,
    private val usersUseCase: UsersFactoryUseCases,
    @ApplicationContext private val context: Context
) : ViewModel() {

    var state by mutableStateOf(ChangeProfileState())
        private set

    private var isUserNameValid: Boolean by mutableStateOf(false)
    var errorUserName: String by mutableStateOf("")

    val data = savedStateHandle.get<String>("user")
    val user = UserModel.fromJson(data!!)

    var updateResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    val resultingActivityHandler = ResultingActivityHandler()
    var saveImageResponse by mutableStateOf<Response<String>?>(null)
        private set
    var file: File? = null

    init {
        state = state.copy(
            userName = user.userName, image = user.image
        )
    }

    fun saveImage() = viewModelScope.launch(Dispatchers.IO) {
        file?.let {
            saveImageResponse = Response.Loading
            val result = usersUseCase.saveImageUserUseCase(it)
            saveImageResponse = result
        }
        update(
            user = UserModel(
                id = user.id, userName = state.userName, image = state.image
            )
        )
    }

    fun pickImage() = viewModelScope.launch(Dispatchers.IO) {
        val result = resultingActivityHandler.getContent(PATH_IMAGE)
        result?.let {
            file = ComposeFileProvider.createFileFromUri(context, it)
            state = state.copy(image = it.toString())
        }
    }

    fun takePhoto() = viewModelScope.launch(Dispatchers.IO) {
        val result = resultingActivityHandler.takePicturePreview()
        result?.let {
            state = state.copy(image = ComposeFileProvider.getPathFromBitmap(context, it))
            file = File(state.image)
        }
    }

    fun onUpdate(url: String) {
        val myUser = UserModel(
            id = user.id, userName = state.userName, image = url
        )
        update(myUser)
    }

    fun update(user: UserModel) = viewModelScope.launch(Dispatchers.IO) {
        updateResponse = Response.Loading
        val result = usersUseCase.updateUserUseCase(user)
        updateResponse = result
    }

    fun onUsernameInput(username: String) {
        state = state.copy(userName = username)
    }

    fun validateUserName() {
        if (LibraryString.validUserName(state.userName)) {
            isUserNameValid = true
            errorUserName = EMPTY_STRING
        } else {
            isUserNameValid = false
            errorUserName = restrictionNameUserAccount
        }
    }
}