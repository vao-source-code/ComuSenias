package com.ars.comusenias.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ars.comusenias.domain.models.state.QRState
import com.ars.comusenias.domain.models.users.ChildrenModel
import com.ars.comusenias.domain.repositories.QRRepository
import com.ars.comusenias.domain.use_cases.auth.AuthFactoryUseCases
import com.ars.comusenias.domain.use_cases.children.ChildrenFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerateQRViewModel @Inject constructor(
    private val childrenUseCases: ChildrenFactory,
    private val qrRepository: QRRepository,
    private val authUsesCases: AuthFactoryUseCases,
) : ViewModel() {

    var state by mutableStateOf(QRState())
    private var userData by mutableStateOf(ChildrenModel())
        private set
    private val currentUser = authUsesCases.getCurrentUserUseCase()

    init {
        startGenerate()
    }

    private fun startGenerate() = viewModelScope.launch(IO) {
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