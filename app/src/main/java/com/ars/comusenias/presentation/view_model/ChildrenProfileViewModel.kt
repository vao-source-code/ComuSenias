package com.ars.comusenias.presentation.view_model

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ars.comusenias.constants.PreferencesConstant
import com.ars.comusenias.domain.library.ComposeFileProvider
import com.ars.comusenias.domain.library.ResultingActivityHandler
import com.ars.comusenias.domain.models.game.LevelModel
import com.ars.comusenias.domain.models.game.SubLevelModel
import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.domain.models.state.ChangeProfileState
import com.ars.comusenias.domain.models.users.ChildrenModel
import com.ars.comusenias.domain.use_cases.auth.AuthFactoryUseCases
import com.ars.comusenias.domain.use_cases.children.ChildrenFactory
import com.ars.comusenias.domain.use_cases.shared_preferences.DataRolStorageFactory
import com.ars.comusenias.presentation.activities.MainActivity
import com.ars.comusenias.presentation.component.home.getAllLevels
import com.ars.comusenias.presentation.ui.theme.EMPTY_STRING
import com.ars.comusenias.presentation.ui.theme.PATH_IMAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ChildrenProfileViewModel @Inject constructor(
    private val authUsesCases: AuthFactoryUseCases,
    private val dataRolStorageFactory: DataRolStorageFactory,
    private val childrenUser: ChildrenFactory,
    @ApplicationContext private val context: Context
) : ViewModel() {

    /*---------------------------------Public Variables---------------------------------*/
    var userData by mutableStateOf(ChildrenModel())
    val currentUser = authUsesCases.getCurrentUserUseCase()

    val resultingActivityHandler = ResultingActivityHandler()

    var state by mutableStateOf(ChangeProfileState())
        private set
    var file: File? = null
    var updateResponse by mutableStateOf<Response<Boolean>?>(null)
        private set
    var saveImageResponse by mutableStateOf<Response<String>?>(null)

    /*--------------------------------- init ---------------------------------*/
    init {
        getUserData()
    }

    // Función para actualizar la lista de niveles sin sobrescribir ciertos campos
    fun updateLevels(newLevels: List<LevelModel>) {
        val updatedLevels = userData.levels.map { existingLevel ->
            newLevels.find { it.id == existingLevel.id }?.let { newLevel ->
                existingLevel.copy(
                    name = existingLevel.name,
                    subLevelId = existingLevel.subLevelId,
                    subLevel = existingLevel.subLevel
                )
            } ?: existingLevel
        }.toMutableList()

        // Añadir los nuevos niveles que no existían en la lista original
        val newLevelIds = updatedLevels.map { it.id }
        newLevels.filterNot { it.id in newLevelIds }.forEach { newLevel ->
            updatedLevels.add(newLevel)
        }

        this.userData.levels = updatedLevels
    }


    fun getUserData() = viewModelScope.launch {
        currentUser?.let {
            childrenUser.getChildrenById(it.uid).collect { user ->
                userData = user
            }
        }
    }

    fun update(user: ChildrenModel) = viewModelScope.launch(IO) {
        updateResponse = Response.Loading
        val result = childrenUser.updateChildren(user)
        updateResponse = result
    }

    /*--------------------------------- Public Methods ---------------------------------*/

    fun pickImage() = viewModelScope.launch(IO) {
        val result = resultingActivityHandler.getContent(PATH_IMAGE)
        result?.let {
            file = ComposeFileProvider.createFileFromUri(context, it)
            state = state.copy(image = it.toString())
        }
    }

    fun takePhoto() = viewModelScope.launch(IO) {
        val result = resultingActivityHandler.takePicturePreview()
        result?.let {
            state = state.copy(image = ComposeFileProvider.getPathFromBitmap(context, it))
            file = File(state.image)
        }
    }

    fun save() = viewModelScope.launch(IO) {
        file?.let {
            saveImageResponse = Response.Loading
            val result = childrenUser.saveImageChildren(it)
            saveImageResponse = result
        }
    }

    fun onUpdate(url: String) = viewModelScope.launch {
        val myUser = withContext(IO) { userData }
        val updatedUser = ChildrenModel(
            id = myUser.id,
            name = myUser.name,
            phone = myUser.phone,
            email = myUser.email,
            date = myUser.date,
            image = url,
        )
        update(updatedUser)
    }


    fun logout() = viewModelScope.launch(IO) {
        authUsesCases.logoutUseCase()
        dataRolStorageFactory.putRolValue(
            PreferencesConstant.PREFERENCE_ROL_CURRENT,
            EMPTY_STRING
        )
    }

    fun updateLevel() = viewModelScope.launch(IO) {
        val myUser = ChildrenModel(
            id = userData.id,
            levels = userData.levels,
        )
        childrenUser.updateLevelChildren(myUser)
    }
}