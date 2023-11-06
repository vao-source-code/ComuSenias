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
import com.example.comusenias.domain.use_cases.auth.AuthFactoryUseCases
import com.example.comusenias.domain.use_cases.specialist.SpecialistFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpecialistViewModel @Inject constructor(
    private val specialistUseCases: SpecialistFactory,
    authUsesCases: AuthFactoryUseCases
) : ViewModel() {

    /*---------------------------------public variable ---------------------------------*/
    var childrenResponse by mutableStateOf<Response<List<ChildrenModel>>?>(Response.Loading)
    var state by mutableStateOf(QRState())
    var stateSpecialist by mutableStateOf(SpecialistModel())

    /*---------------------------------private variable ---------------------------------*/
    private val currentUser = authUsesCases.getCurrentUserUseCase()
    private var childrens by mutableStateOf(listOf<ChildrenModel>())

    init {
        getUserData()
        getChildrenBySpecialist()
    }

    private fun getUserData() = viewModelScope.launch {
        currentUser?.let {
            specialistUseCases.getSpecialistById(it.uid).collect { user ->
                stateSpecialist = user
            }
        }
    }

    private fun getChildrenBySpecialist() = viewModelScope.launch(IO) {
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

}