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
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ChangeProfileViewModel @Inject constructor(
    private var savedStateHandle: SavedStateHandle,
    private val usersUseCase: UsersUseCase,
    @ApplicationContext private val context: Context
) :
    ViewModel() {

    var state by mutableStateOf(ChangeProfileState())
        private set

    var isUserNameValid: Boolean by mutableStateOf(false)
    var errorUserName: String by mutableStateOf("")

    val data = savedStateHandle.get<String>("user")
    val user = User.fromJson(data!!)

    var updateResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    var imageUri by mutableStateOf<String>("")
    val resultingActivityHandler = ResultingActivityHandler()
    var saveImageResponse by mutableStateOf<Response<String>?>(null)
        private set
    var file : File? = null

    init {
        state = state.copy(userName = user.userName)
    }

    fun saveImage() = viewModelScope.launch {
        if(file != null){
            saveImageResponse = Response.Loading
            val result = usersUseCase.saveImageUser(file!!)
            saveImageResponse = result
        }

    }
    fun pickImage() = viewModelScope.launch {
        val result = resultingActivityHandler.getContent("image/*")
        if (result != null) {
            file = ComposeFileProvider.createFileFromUri(context, result)
            imageUri = result.toString()
        }
    }

    fun takePhoto() = viewModelScope.launch {
        val result = resultingActivityHandler.takePicturePreview()
        if (result != null) {
            imageUri = ComposeFileProvider.getPathFromBitmap(context, result)
            file = File(imageUri)
        }

    }

    fun updateUser(user: User) = viewModelScope.launch {
        updateResponse = Response.Loading
        val result = usersUseCase.updateUser(user)
        updateResponse = result
    }

    fun onUpdate(uri : String) {
        val user = User(id = user.id, userName = state.userName, image = uri)
        updateUser(user)
    }

    fun onUserNameChange(userName: String) {
        state = state.copy(userName = userName)
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