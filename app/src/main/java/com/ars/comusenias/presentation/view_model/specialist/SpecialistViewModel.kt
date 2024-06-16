package com.ars.comusenias.presentation.view_model.specialist

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ars.comusenias.domain.library.ComposeFileProvider
import com.ars.comusenias.domain.library.ResultingActivityHandler
import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.domain.models.state.QRState
import com.ars.comusenias.domain.models.users.ChildrenModel
import com.ars.comusenias.domain.models.users.SpecialistModel
import com.ars.comusenias.domain.use_cases.auth.AuthFactoryUseCases
import com.ars.comusenias.domain.use_cases.specialist.SpecialistFactory
import com.ars.comusenias.presentation.ui.theme.PATH_IMAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class SpecialistViewModel @Inject constructor(
    private val specialistUseCases: SpecialistFactory,
    authUsesCases: AuthFactoryUseCases,
    @ApplicationContext private val context: Context
) : ViewModel() {

    /*---------------------------------public variable ---------------------------------*/
    var childrenResponse by mutableStateOf<Response<List<ChildrenModel>>?>(Response.Loading)
    var state by mutableStateOf(QRState())
    var stateSpecialist by mutableStateOf(SpecialistModel())
    val resultingActivityHandler = ResultingActivityHandler()
    var file: File? = null
    var updateResponse by mutableStateOf<Response<Boolean>?>(null)
        private set
    var saveImageResponse by mutableStateOf<Response<String>?>(null)

    /*---------------------------------private variable ---------------------------------*/
    private val currentUser = authUsesCases.getCurrentUserUseCase()
    private var childrens by mutableStateOf(listOf<ChildrenModel>())

    init {
        getUserData()
    }

    fun getUserData() = viewModelScope.launch {
        currentUser?.let {
            specialistUseCases.getSpecialistById(it.uid).collect { user ->
                stateSpecialist = user
                getChildrenBySpecialist()
            }
        }
    }

    fun getChildrenBySpecialist() = viewModelScope.launch(IO) {
        currentUser?.let {
            specialistUseCases.getChildrenForSpecialistById(it.uid).collect { children ->
                childrenResponse = children
                if (children is Response.Success) {
                    childrens = children.data
                    stateSpecialist.childrenInCharge = childrens
                }
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
            file = stateSpecialist.image?.let { it1 -> File(it1) }
        }
    }

}