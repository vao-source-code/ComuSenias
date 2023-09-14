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
import com.example.comusenias.domain.models.ChangeProfileState
import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.User
import com.example.comusenias.domain.use_cases.users.UsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ChangeProfileViewModel @Inject constructor(
    private var savedStateHandle: SavedStateHandle,
    private val usersUseCase: UsersUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    var state by mutableStateOf(ChangeProfileState())
        private set

    var isUserNameValid: Boolean by mutableStateOf(false)
    var errorUserName: String by mutableStateOf("")

    val data = savedStateHandle.get<String>("user")
    val user = User.fromJson(data!!)

    var updateResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    val resultingActivityHandler = ResultingActivityHandler()
    var saveImageResponse by mutableStateOf<Response<String>?>(null)
        private set
    var file: File? = null

    init {
        // SET ARGUMENTS
        state = state.copy(
            userName = user.userName,
            image = user.image
        )

    }

    fun saveImage() = viewModelScope.launch (Dispatchers.IO){
        if (file != null) {
            saveImageResponse = Response.Loading
            val result = usersUseCase.saveImageUser(file!!)
            saveImageResponse = result
        }

        update(user = User(
            id = user.id,
            userName = state.userName,
            image = state.image
        ))
    }

    fun pickImage() = viewModelScope.launch (Dispatchers.IO){
        val result = resultingActivityHandler.getContent("image/*")
        if (result != null) {
            file = ComposeFileProvider.createFileFromUri(context, result)
            state = state.copy(image = result.toString())
        }
    }

    fun takePhoto() = viewModelScope.launch (Dispatchers.IO){
        val result = resultingActivityHandler.takePicturePreview()
        if (result != null) {
            state = state.copy(image = ComposeFileProvider.getPathFromBitmap(context, result))
            file = File(state.image)
        }
    }

    fun onUpdate(url: String) {
        val myUser = User(
            id = user.id,
            userName = state.userName,
            image = url
        )
        update(myUser)
    }

    fun update(user: User) = viewModelScope.launch (Dispatchers.IO){
        updateResponse = Response.Loading
        val result = usersUseCase.updateUser(user)
        updateResponse = result
    }

    fun onUsernameInput(username: String) {
        state = state.copy(userName = username)
    }


    fun validateUserName() {
            if (LibraryString.validUserName(state.userName)) {
                isUserNameValid = true
                errorUserName = ""
            } else {
                isUserNameValid = false
                errorUserName = "El nombre de usuario debe tener al menos 3 caracteres"
            }
        }

    }