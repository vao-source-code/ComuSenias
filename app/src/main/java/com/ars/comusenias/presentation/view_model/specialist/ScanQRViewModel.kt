package com.ars.comusenias.presentation.view_model.specialist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ars.comusenias.domain.models.response.Response
import com.ars.comusenias.domain.models.state.QRState
import com.ars.comusenias.domain.models.users.ChildrenModel
import com.ars.comusenias.domain.models.users.SpecialistModel
import com.ars.comusenias.domain.repositories.QRRepository
import com.ars.comusenias.domain.use_cases.auth.AuthFactoryUseCases
import com.ars.comusenias.domain.use_cases.children.ChildrenFactory
import com.ars.comusenias.domain.use_cases.specialist.SpecialistFactory
import com.ars.comusenias.presentation.ui.theme.EMPTY_STRING
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScanQRViewModel @Inject constructor(
    private val childrenUseCases: ChildrenFactory,
    private val specialistUseCases: SpecialistFactory,
    private val qrRepository: QRRepository,
    authUsesCases: AuthFactoryUseCases
) : ViewModel() {

    /*---------------------------------public variable ---------------------------------*/
    var state by mutableStateOf(QRState())
    var stateChildren by mutableStateOf(ChildrenModel())
    var addChildrenResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    var scanningChildrenResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    /*---------------------------------private variable ---------------------------------*/
    private var stateSpecialist by mutableStateOf(SpecialistModel())
    private val currentUser = authUsesCases.getCurrentUserUseCase()

    init {
        getUserData()
    }

    /*---------------------------------private function ---------------------------------*/
    private fun getUserData() = viewModelScope.launch {
        currentUser?.let {
            specialistUseCases.getSpecialistById(it.uid).collect { user ->
                stateSpecialist = user
            }
        }
    }

    fun startScanning() = viewModelScope.launch(IO) {
        qrRepository.startScanning().collect { data ->
            scanningChildrenResponse = Response.Loading
            if (!data.isNullOrBlank()) {
                try {
                    state = state.copy(details = data)
                    val children = ChildrenModel.fromJson(state.details)
                    stateChildren = children
                    scanningChildrenResponse = Response.Success(true)
                } catch (e: Exception) {
                    state = state.copy(details = EMPTY_STRING)
                    scanningChildrenResponse = Response.Error(e)
                }
            }
        }

    }

    /*---------------------------------public function ---------------------------------*/
    fun updateChildrenBySpecialist() = viewModelScope.launch(IO) {
        addChildrenResponse = Response.Loading
        stateChildren.idSpecialist = stateSpecialist.id
        addChildrenResponse = childrenUseCases.integrateChildrenWithSpecialist(stateChildren)
    }

}