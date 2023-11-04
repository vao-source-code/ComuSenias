package com.example.comusenias.presentation.view_model.specialist

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.library.LibraryDate
import com.example.comusenias.domain.models.observation.ObservationModel
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.domain.models.state.ChangeProfileState
import com.example.comusenias.domain.models.users.ChildrenModel
import com.example.comusenias.domain.models.users.SpecialistModel
import com.example.comusenias.domain.use_cases.observation.ObservationFactory
import com.example.comusenias.domain.use_cases.specialist.SpecialistFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfilePatientViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    @ApplicationContext private val context: Context,
    private val observationUsesCase: ObservationFactory,
    private val specialistUsesCase: SpecialistFactory
) : ViewModel() {

    var state by mutableStateOf(ChangeProfileState())
        private set

    var stateObservation by mutableStateOf(ObservationModel())
        private set

    var listObservation by mutableStateOf(listOf<ObservationModel>())
        private set

    var observationResponse by mutableStateOf<Response<List<ObservationModel>>?>(Response.Loading)

    var specialist by mutableStateOf(SpecialistModel())
        private set

    val data = savedStateHandle.get<String>("pacient")
    val user = ChildrenModel.fromJson(data!!)

    init {
        getObservation()
        getSpecialist()
    }

    private fun getSpecialist() = viewModelScope.launch {
        specialistUsesCase.getSpecialistById(user.idSpecialist).collect { sp ->
            specialist = sp
        }
    }

    private fun getObservation() = viewModelScope.launch {
        observationUsesCase.getObservation(user.id).collect { observation ->
            observationResponse = observation
            if (observation is Response.Success) {
                listObservation = observation.data
            }
        }
    }


    fun updateObservation() = viewModelScope.launch {
        observationUsesCase.updateObservation(stateObservation)
    }

    fun createObservation() = viewModelScope.launch {
        onDateObservation(LibraryDate.getCurrentDateTimeAsString())
        onIdSpecialist(user.idSpecialist)
        onNameSpecialist(specialist.name)
        observationUsesCase.createObservation(stateObservation)
    }


    private fun onNameSpecialist(nameSpecialist: String) {
        stateObservation = stateObservation.copy(nameSpecialist = nameSpecialist)
    }

    fun onObservation(observation: String) {
        stateObservation = stateObservation.copy(observation = observation)
    }

    fun onDateObservation(dateObservation: String) {
        stateObservation = stateObservation.copy(dateObservation = dateObservation)
    }

    fun onIdChildren(idChildren: String) {
        stateObservation = stateObservation.copy(idChildren = idChildren)
    }

    fun onIdSpecialist(idSpecialist: String) {
        stateObservation = stateObservation.copy(idSpecialist = idSpecialist)
    }


}