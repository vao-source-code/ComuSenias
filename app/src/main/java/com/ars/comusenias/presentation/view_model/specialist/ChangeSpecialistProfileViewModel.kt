package com.ars.comusenias.presentation.view_model.specialist

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ars.comusenias.domain.library.ComposeFileProvider
import com.ars.comusenias.domain.library.LibraryString
import com.ars.comusenias.domain.library.ResultingActivityHandler
import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.domain.models.users.SpecialistModel
import com.ars.comusenias.domain.use_cases.specialist.SpecialistFactory
import com.ars.comusenias.presentation.ui.theme.EMPTY_STRING
import com.ars.comusenias.presentation.ui.theme.INVALID_DATE
import com.ars.comusenias.presentation.ui.theme.PATH_IMAGE
import com.ars.comusenias.presentation.ui.theme.RESTRICTION_NAME_USER_ACCOUNT
import com.ars.comusenias.presentation.ui.theme.RESTRICTION_SPECIALIST_ACCOUNT
import com.ars.comusenias.presentation.ui.theme.RESTRICTION_TITLE_MEDICAL_ACCOUNT
import com.ars.comusenias.presentation.ui.theme.SPECIALIST_PROFILE
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ChangeSpecialistProfileViewModel @Inject constructor(
    private var savedStateHandle: SavedStateHandle,
    private val specialistUsesCase: SpecialistFactory,
    @ApplicationContext private val context: Context
) : ViewModel() {

    var state by mutableStateOf(SpecialistModel())
        private set
    val data = savedStateHandle.get<String>(SPECIALIST_PROFILE)
    val user = SpecialistModel.fromJson(data!!)
    var updateResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    val resultingActivityHandler = ResultingActivityHandler()
    var saveImageResponse by mutableStateOf<Response<String>?>(null)
        private set
    var file: File? = null
    var errorTitleMedical: String by mutableStateOf("")
    var errorMedicalLicense: String by mutableStateOf("")
    var errorSpeciality: String by mutableStateOf("")
    var errorMedicalLicenseExpiration: String by mutableStateOf("")


    private var isMedicalLicenseValid: Boolean by mutableStateOf(false)
    private var isMedicalLicenseExpirationValid: Boolean by mutableStateOf(false)
    private var isSpecialityValid: Boolean by mutableStateOf(false)
    private var isTitleMedical: Boolean by mutableStateOf(false)


    init {
        state = state.copy(
            id = user.id,
            name = user.name,
            image = user.image,
            medicalLicense = user.medicalLicense,
            medicalLicenseExpiration = user.medicalLicenseExpiration,
            titleMedical = user.titleMedical,
            speciality = user.speciality
        )
    }

    fun save() = viewModelScope.launch(IO) {

        if (file != null) {
            file?.let {
                saveImageResponse = Response.Loading
                val result = specialistUsesCase.saveImageSpecialist(it)
                saveImageResponse = result
            }
        }else{
            onUpdate(state.image!!)
        }
    }

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

    fun onUpdate(url: String) {
        val myUser = SpecialistModel(
            id = state.id,
            name = state.name,
            image = url,
            medicalLicense = state.medicalLicense,
            medicalLicenseExpiration = state.medicalLicenseExpiration,
            speciality = state.speciality,
            titleMedical = state.titleMedical
        )
        update(myUser)
    }

    fun update(user: SpecialistModel) = viewModelScope.launch(IO) {
        updateResponse = Response.Loading
        val result = specialistUsesCase.updateSpecialist(user)
        updateResponse = result
    }


    fun onMedicalLicenseInputChanged(medicalLicense: String) {
        state = state.copy(medicalLicense = medicalLicense)
    }

    fun onMedicalLicenseExpirationInputChanged(medicalLicenseExpiration: String) {
        state = state.copy(medicalLicenseExpiration = medicalLicenseExpiration)
    }

    fun onSpecialityInputChanged(speciality: String) {
        state = state.copy(speciality = speciality)
    }

    fun onTitleMedicalInputChanged(titleMedical: String) {
        state = state.copy(titleMedical = titleMedical)
    }


    fun validateMedicalLicense() {
        val isValid = state.medicalLicense.isNotEmpty()
        isMedicalLicenseValid = isValid
        errorMedicalLicenseExpiration = if (isValid) EMPTY_STRING else RESTRICTION_NAME_USER_ACCOUNT
    }

    fun validateMedicalLicenseExpirationValid() {
        val isValid =
            LibraryString.validateRegistrationExpirationDate(state.medicalLicenseExpiration)
        isMedicalLicenseExpirationValid = isValid
        errorMedicalLicenseExpiration = if (isValid) EMPTY_STRING else INVALID_DATE
    }

    fun validateTitleMedical() {
        val isValid = state.titleMedical.isNotEmpty()
        isTitleMedical = isValid
        errorTitleMedical = if (isValid) EMPTY_STRING else RESTRICTION_TITLE_MEDICAL_ACCOUNT
    }

    fun validateSpeciality() {
        val isValid = state.speciality.isNotEmpty()
        isSpecialityValid = isValid
        errorSpeciality = if (isValid) EMPTY_STRING else RESTRICTION_SPECIALIST_ACCOUNT
    }
}