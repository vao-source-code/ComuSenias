package com.example.comusenias.presentation.view_model

import com.example.comusenias.domain.models.users.Rol
import com.example.comusenias.domain.models.users.UserModel
import com.example.comusenias.domain.use_cases.auth.AuthFactoryUseCases
import com.example.comusenias.domain.use_cases.shared_preferences.DataUserStorageFactory
import com.example.comusenias.domain.use_cases.users.UsersFactoryUseCases
import com.example.comusenias.presentation.ui.theme.INVALID_EMAIL
import com.example.comusenias.presentation.ui.theme.RESTRICTION_PASSWORD_USER_ACCOUNT
import com.example.comusenias.presentation.ui.theme.emptyString
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class RegisterViewModelTest {

    @RelaxedMockK
    private lateinit var authUseCases: AuthFactoryUseCases

    @RelaxedMockK
    private lateinit var usersUseCase: UsersFactoryUseCases

    @RelaxedMockK
    private lateinit var dataUserStorageFactory: DataUserStorageFactory

    private lateinit var viewModel: RegisterViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = RegisterViewModel(
            authUseCases = authUseCases,
            usersUseCase = usersUseCase,
            dataUserStorageFactory = dataUserStorageFactory
        )
        Dispatchers.setMain(Dispatchers.Unconfined)
    }


    @Test
    fun validateEmail() {

        // Email válido
        viewModel.onEmailInput("email@ejemplo.com")
        viewModel.validateEmail()
        assertTrue(viewModel.isEmailValid)
        assertEquals(emptyString, viewModel.errorEmail)

        // Email inválido
        viewModel.onEmailInput("email_sin_arroba.com")
        viewModel.validateEmail()
        assertFalse(viewModel.isEmailValid)
        assertEquals(INVALID_EMAIL, viewModel.errorEmail)
    }

    // Test para validar la contraseña
    @Test
    fun validatePassword() {

        // Contraseña válida
        viewModel.onPasswordInput("Contraseña1234")
        viewModel.validatePassword()
        assertTrue(viewModel.isPasswordValid)
        assertEquals(emptyString, viewModel.errorPassword)

        // Contraseña inválida
        viewModel.onPasswordInput("1234")
        viewModel.validatePassword()
        assertFalse(viewModel.isPasswordValid)
        assertEquals(RESTRICTION_PASSWORD_USER_ACCOUNT, viewModel.errorPassword)
    }

    // Test para validar la confirmación de la contraseña
    @Test
    fun validateConfirmPassword() {

        // Cont contraseñas coincidentes
        viewModel.onPasswordInput("contraseña1234")
        viewModel.onConfirmPasswordInput("contraseña1234")
        viewModel.validateConfirmPassword()
        assertTrue(viewModel.isConfirmPasswordValid)
        assertEquals(emptyString, viewModel.errorConfirmPassword)

        // Cont contraseñas no coincidentes
        viewModel.onConfirmPasswordInput("contraseña_incorrecta")
        viewModel.validateConfirmPassword()
        assertFalse(viewModel.isConfirmPasswordValid)
        assertEquals("Las contraseñas no coinciden", viewModel.errorConfirmPassword)
    }

    // Test para habilitar el botón de registro
    @Test
    fun enabledRegisterButton() {

        // Botón deshabilitado
        viewModel.isEmailValid = false
        viewModel.isPasswordValid = false
        viewModel.isConfirmPasswordValid = false
        viewModel.enabledRegisterButton()
        assertFalse(viewModel.isRegisterEnabled)

        // Botón habilitado
        viewModel.isEmailValid = true
        viewModel.isPasswordValid = true
        viewModel.isConfirmPasswordValid = true
        viewModel.enabledRegisterButton()
        assertTrue(viewModel.isRegisterEnabled)
    }

    // Test para crear un usuario
    @Test
    fun createUser() {

        // Crear un usuario
        viewModel.user = UserModel(
            email = "email@ejemplo.com",
            password = "contraseña1234",
            rol = Rol.CHILDREN.toString()
        )
        viewModel.createUser()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}

