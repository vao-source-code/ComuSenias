package com.example.comusenias.presentation.view_model

import com.example.comusenias.domain.use_cases.auth.AuthFactoryUseCases
import com.example.comusenias.domain.use_cases.users.UsersFactoryUseCases
import io.mockk.MockKAnnotations
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.mockito.Mock

class LoginViewModelTest {

    @Mock
    private lateinit var authUseCases: AuthFactoryUseCases

    @Mock
    private lateinit var usersUseCase: UsersFactoryUseCases

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