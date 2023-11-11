package com.example.comusenias.presentation.view_model.specialist

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.constants.PreferencesConstant.PREFERENCE_ROL_CURRENT
import com.example.comusenias.domain.library.ComposeFileProvider
import com.example.comusenias.domain.library.ResultingActivityHandler
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.domain.models.users.SpecialistModel
import com.example.comusenias.domain.use_cases.auth.AuthFactoryUseCases
import com.example.comusenias.domain.use_cases.shared_preferences.DataRolStorageFactory
import com.example.comusenias.domain.use_cases.specialist.SpecialistFactory
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.example.comusenias.presentation.ui.theme.PATH_IMAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import id.zelory.compressor.Compressor
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class SpecialistProfileViewModel @Inject constructor(
    private val specialistUseCases: SpecialistFactory,
    private val authUsesCases: AuthFactoryUseCases,
    private val dataRolStorageFactory: DataRolStorageFactory,
    @ApplicationContext private val context: Context
) : ViewModel() {

    /*---------------------------------public variable ---------------------------------*/
    var stateSpecialist by mutableStateOf(SpecialistModel())
    val resultingActivityHandler = ResultingActivityHandler()
    var file: File? = null
    var saveImageResponse by mutableStateOf<Response<String>?>(null)

    /*---------------------------------private variable ---------------------------------*/
    private val currentUser = authUsesCases.getCurrentUserUseCase()

    init {
        getUserData()
    }

    private fun getUserData() = viewModelScope.launch {
        currentUser?.let {
            specialistUseCases.getSpecialistById(it.uid).collect { user ->
                stateSpecialist = user
            }
        }
    }


    fun pickImage() = viewModelScope.launch(IO) {
        val result = resultingActivityHandler.getContent(PATH_IMAGE)
        result?.let {
            file = ComposeFileProvider.createFileFromUri(context, it)
            stateSpecialist = stateSpecialist.copy(image = it.toString())
        }
    }

    fun takePhoto() = viewModelScope.launch(IO) {
        val result = resultingActivityHandler.takePicturePreview()
        result?.let {
            stateSpecialist =
                stateSpecialist.copy(image = ComposeFileProvider.getPathFromBitmap(context, it))
            file = File(stateSpecialist.image)
        }
    }

    fun saveImage() = viewModelScope.launch(IO) {
        file?.let {
            saveImageResponse = Response.Loading
            val compressedImageFile = Compressor.compress(context = context, it)
            val result = specialistUseCases.saveImageSpecialist(compressedImageFile)
            saveImageResponse = result
        }
    }

    fun logout() = viewModelScope.launch(IO) {
        authUsesCases.logoutUseCase()
        dataRolStorageFactory.putRolValue(PREFERENCE_ROL_CURRENT, EMPTY_STRING)
    }
}