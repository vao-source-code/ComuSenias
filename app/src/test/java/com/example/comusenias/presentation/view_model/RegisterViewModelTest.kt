package com.example.comusenias.presentation.view_model

import com.example.comusenias.domain.models.Response
import com.example.comusenias.domain.models.User
import com.example.comusenias.domain.use_cases.auth.AuthUseCases
import com.example.comusenias.domain.use_cases.users.UsersUseCase
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
    private lateinit var authUseCases: AuthUseCases

    @Mock
    private lateinit var usersUseCase: UsersUseCase

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
    fun `register is successful`() = runBlocking {

        val user = User(
            id = "1",
            userName = "test",
            email = "aW@gmail.com",
            password = "123456",
        )

        val result = Response.Success(mockk<FirebaseUser>())

        coEvery { authUseCases.register(user) } returns result

        viewModel.register(user)

        assertEquals(viewModel.registerFlow.value, result)


    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}

