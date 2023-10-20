package com.example.comusenias.presentation.view_model

import com.example.comusenias.domain.repositories.AuthRepository
import com.example.comusenias.domain.use_cases.auth.AuthFactoryUseCases
import com.example.comusenias.domain.use_cases.auth.GetCurrentUserUseCase
import com.example.comusenias.domain.use_cases.shared_preferences.DataRolStorageFactory
import com.example.comusenias.domain.use_cases.users.UsersFactoryUseCases
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class LoginViewModelTest {

    @RelaxedMockK
    private lateinit var authUseCases: AuthFactoryUseCases

    @RelaxedMockK
    private lateinit var usersUseCase: UsersFactoryUseCases

    @RelaxedMockK
    private lateinit var userRepository: AuthRepository

    @RelaxedMockK
    private lateinit var dataRolStorageFactory: DataRolStorageFactory

    private lateinit var viewModel: LoginViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockKAnnotations.init(this)
        authUseCases = mockk()
        usersUseCase = mockk()

        coEvery { authUseCases.getCurrentUserUseCase } returns GetCurrentUserUseCase(userRepository)
        viewModel = LoginViewModel(
            authUseCases = authUseCases,
            dataRolStorageFactory = dataRolStorageFactory,
            usersUseCase = usersUseCase
        )
        Dispatchers.setMain(Dispatchers.Unconfined)
    }


    @Test
    fun testValidEmail() = runBlocking {
        // Iniciar sesión con credenciales válidas
        viewModel.onEmailInput("email@ejemplo.com")
        viewModel.validateEmail()
        viewModel.onPasswordInput("contraseña1234")
        viewModel.login()
        assertEquals(viewModel.isEmailValid, true)

        // Iniciar sesión con credenciales inválidas
        viewModel.onEmailInput("email_incorrecto")
        viewModel.onPasswordInput("contraseña_incorrecta")
        viewModel.validateEmail()
        viewModel.login()
        assertEquals(viewModel.isEmailValid, false)
    }

    @Test
    fun testValidPassword() = runBlocking {
        // Iniciar sesión con credenciales válidas
        viewModel.onEmailInput("email@ejemplo.com")
        viewModel.onPasswordInput("Contraseña1234")
        viewModel.validatePassword()
        viewModel.login()
        assertEquals(viewModel.isPasswordValid, true)

        // Iniciar sesión con credenciales inválidas
        viewModel.onEmailInput("email@ejemplo.com")
        viewModel.onPasswordInput("12345")
        viewModel.validatePassword()
        viewModel.login()
        assertEquals(viewModel.isPasswordValid, false)
    }

}



