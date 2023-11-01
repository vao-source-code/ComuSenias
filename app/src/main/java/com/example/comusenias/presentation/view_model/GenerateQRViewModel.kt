package com.example.comusenias.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.models.state.QRState
import com.example.comusenias.domain.models.users.ChildrenModel
import com.example.comusenias.domain.repositories.QRRepository
import com.example.comusenias.domain.use_cases.auth.AuthFactoryUseCases
import com.example.comusenias.domain.use_cases.children.ChildrenFactory
import com.example.comusenias.domain.use_cases.shared_preferences.DataUserStorageFactory
import com.example.comusenias.domain.use_cases.specialist.SpecialistFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerateQRViewModel @Inject constructor(
    private val childrenUseCases: ChildrenFactory,
    private val specialistUseCases: SpecialistFactory,
    private val qrRepository: QRRepository,
    private val authUsesCases: AuthFactoryUseCases,
    val dataStorageRepository: DataUserStorageFactory
) : ViewModel() {

    var state by mutableStateOf(QRState())
    var userData by mutableStateOf(ChildrenModel())
        private set
    val currentUser = authUsesCases.getCurrentUserUseCase()


    init {
        startGenerate()
    }

    fun startGenerate() = viewModelScope.launch(IO) {
        currentUser?.let {
            childrenUseCases.getChildrenById(it.uid).collect { user ->
                userData = user
                qrRepository.starGenerate(userData).collect { data ->
                    state = state.copy(bitmap = data)

                }
            }
        }
    }

}