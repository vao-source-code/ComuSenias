package com.example.comusenias.presentation.view_model

import com.example.comusenias.domain.use_cases.auth.AuthUseCases
import com.example.comusenias.domain.use_cases.users.UsersUseCase
import io.mockk.MockKAnnotations
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.mockito.Mock

class LoginViewModelTest {

    @Mock
    private lateinit var authUseCases: AuthUseCases

    @Mock
    private lateinit var usersUseCase: UsersUseCase

    private lateinit var viewModel: LoginViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockKAnnotations.init(this)
        authUseCases = mockk()
        usersUseCase = mockk()
        viewModel = LoginViewModel(authUseCases = authUseCases)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }


}