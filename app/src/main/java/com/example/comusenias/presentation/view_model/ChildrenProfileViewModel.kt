package com.example.comusenias.presentation.view_model

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.library.ComposeFileProvider
import com.example.comusenias.domain.library.ResultingActivityHandler
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.domain.models.state.ChangeProfileState
import com.example.comusenias.domain.models.users.ChildrenModel
import com.example.comusenias.domain.use_cases.auth.AuthFactoryUseCases
import com.example.comusenias.domain.use_cases.children.ChildrenFactory
import com.example.comusenias.domain.use_cases.users.UsersFactoryUseCases
import com.example.comusenias.presentation.ui.theme.PATH_IMAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ChildrenProfileViewModel @Inject constructor(
    private val authUsesCases: AuthFactoryUseCases, private val useCases: UsersFactoryUseCases,
    private val childrenUser: ChildrenFactory,
    @ApplicationContext private val context: Context

) : ViewModel() {

    var userData by mutableStateOf(ChildrenModel())
        private set
    val currentUser = authUsesCases.getCurrentUserUseCase()

    val resultingActivityHandler = ResultingActivityHandler()

    var state by mutableStateOf(ChangeProfileState())
        private set
    var file: File? = null
    var updateResponse by mutableStateOf<Response<Boolean>?>(null)
        private set
    var saveImageResponse by mutableStateOf<Response<String>?>(null)

    init {
        getUserData()
    }

    private fun getUserData() = viewModelScope.launch {
        currentUser?.let {
            childrenUser.getChildrenById(it.uid).collect { user ->
                userData = user
            }
        }
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

    fun saveImage() = viewModelScope.launch(Dispatchers.IO) {
        file?.let {
            saveImageResponse = Response.Loading
            val result = childrenUser.saveImageChildren(it)
            saveImageResponse = result
        }
    }

    fun onUpdate(url: String) {
        val myUser = ChildrenModel(
            id = userData.id,
            name = userData.name,
            phone = userData.phone,
            email = userData.email,
            date = userData.date,
            image = url,
        )
        update(myUser)
    }

    fun update(user: ChildrenModel) = viewModelScope.launch(Dispatchers.IO) {
        updateResponse = Response.Loading
        val result = childrenUser.updateChildren(user)
        updateResponse = result
    }

    fun logout() {
        authUsesCases.logoutUseCase()
    }
}