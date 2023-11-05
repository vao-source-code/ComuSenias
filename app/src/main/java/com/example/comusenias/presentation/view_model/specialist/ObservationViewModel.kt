package com.example.comusenias.presentation.view_model.specialist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.library.LibraryDate
import com.example.comusenias.domain.models.observation.ObservationModel
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.domain.models.users.ChildrenModel
import com.example.comusenias.domain.models.users.SpecialistModel
import com.example.comusenias.domain.use_cases.children.ChildrenFactory
import com.example.comusenias.domain.use_cases.observation.ObservationFactory
import com.example.comusenias.presentation.ui.theme.CHILDREN_OBSERVATION
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ObservationViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val observationUsesCase: ObservationFactory,
    private val childrenUsesCase: ChildrenFactory
) : ViewModel() {

    var stateChildren by mutableStateOf(ChildrenModel())
        private set

    var sendObservationResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    var stateObservation by mutableStateOf(ObservationModel())
        private set

    var text by mutableStateOf("")


    var specialist by mutableStateOf(SpecialistModel())
        private set

    val data = savedStateHandle.get<String>(CHILDREN_OBSERVATION)
    val observation = ObservationModel.fromJson(data!!)

    init {
        getChildren()
    }

    fun getChildren() = viewModelScope.launch(IO) {
        childrenUsesCase.getChildrenById(observation.idChildren).collect {
            stateChildren = it
        }
    }


    fun createObservation() = viewModelScope.launch {
        sendObservationResponse = Response.Loading
        onDateObservation(LibraryDate.getCurrentDateTimeAsString())
        onDateTimeObservation(LibraryDate.getCurrentDateTimeAsLong())
        onIdSpecialist(observation.idSpecialist)
        onNameSpecialist(observation.nameSpecialist)
        onIdChildren(observation.idChildren)
        sendObservationResponse = observationUsesCase.createObservation(stateObservation)
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

    fun onDateTimeObservation(currentDateTimeAsLong: Long) {
        stateObservation = stateObservation.copy(timeDate = currentDateTimeAsLong)
    }

}