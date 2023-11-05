package com.example.comusenias.presentation.view_model.specialist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.domain.models.state.QRState
import com.example.comusenias.domain.models.users.ChildrenModel
import com.example.comusenias.domain.models.users.SpecialistModel
import com.example.comusenias.domain.repositories.QRRepository
import com.example.comusenias.domain.use_cases.auth.AuthFactoryUseCases
import com.example.comusenias.domain.use_cases.children.ChildrenFactory
import com.example.comusenias.domain.use_cases.specialist.SpecialistFactory
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
            if (!data.isNullOrBlank()) {
                try {
                    state = state.copy(details = data)
                    val children = ChildrenModel.fromJson(state.details)
                    stateChildren = children
                } catch (e: Exception) {
                    e.printStackTrace()
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