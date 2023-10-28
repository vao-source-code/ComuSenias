package com.example.comusenias.presentation.view_model

import com.example.comusenias.domain.models.response.Response
import com.example.comusenias.domain.models.users.UserModel
import com.example.comusenias.domain.models.auth.AuthFactory
import com.example.comusenias.domain.use_cases.users.UsersFactoryUseCases
import com.google.firebase.auth.FirebaseUser
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class RegisterViewModelTest {

    @Mock
    private lateinit var authUseCases: AuthFactory

    @Mock
    private lateinit var usersUseCase: UsersFactoryUseCases

    private lateinit var viewModel: RegisterViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockKAnnotations.init(this)
        authUseCases = mockk()
        usersUseCase = mockk()
        viewModel = RegisterViewModel(authUseCases = authUseCases, usersUseCase = usersUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)

    }


    @Test
    fun registerIsSuccessful() = runBlocking {

        val user = UserModel(
            id = "1",
            userName = "test",
            email = "aW@gmail.com",
            password = "123456",
        )

        val result = Response.Success(mockk<FirebaseUser>())
        coEvery { authUseCases.registerUseCase(user) } returns result
        viewModel.register(user)
        assertEquals(viewModel.registerResponse, result)

    }


    @Test
    fun registerIsError() = runBlocking {

        val user = UserModel(
            id = "1",
            userName = "test",
            email = "",
        )

        val result = Response.Error(Exception("Error"))
        coEvery { authUseCases.registerUseCase(user) } returns result
        viewModel.register(user)
        assertEquals(viewModel.registerResponse, result)

    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}

