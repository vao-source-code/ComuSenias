package com.example.comusenias.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comusenias.domain.models.state.QRState
import com.example.comusenias.domain.repositories.QRRepository
import com.example.comusenias.domain.use_cases.children.ChildrenFactory
import com.example.comusenias.domain.use_cases.specialist.SpecialistFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QRViewModel @Inject constructor(
    private val childrenUseCases: ChildrenFactory,
    private val specialistUseCases: SpecialistFactory,
    private val qrRepository: QRRepository
) : ViewModel() {

    var state by mutableStateOf(QRState())

    fun startScanning() = viewModelScope.launch(IO) {
        qrRepository.startScanning().collect { data ->
            if (!data.isNullOrBlank()) {
                state = state.copy(details = data)
            }
        }
    }

}