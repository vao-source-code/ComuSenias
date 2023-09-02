package com.example.comusenias.presentation.view_model

import androidx.lifecycle.ViewModel
import com.example.comusenias.domain.use_cases.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val authUsesCases : AuthUseCases): ViewModel() {

    fun logout() {
        authUsesCases.logout()
    }
}