package com.example.comusenias.presentation.view_model

import com.example.comusenias.constants.PreferencesConstant
import com.example.comusenias.domain.models.users.ChildrenModel
import com.example.comusenias.domain.use_cases.auth.AuthFactoryUseCases
import com.example.comusenias.domain.use_cases.auth.GetCurrentUserUseCase
import com.example.comusenias.domain.use_cases.children.ChildrenFactory
import com.example.comusenias.domain.use_cases.shared_preferences.DataRolStorageFactory
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.google.firebase.auth.FirebaseUser
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
class ChildrenProfileViewModelTest {
//
//    @get:Rule
//    val coroutineRule = CoroutineTestRule()
//
//    private lateinit var viewModel: ChildrenProfileViewModel
//
//    @RelaxedMockK
//    lateinit var authUsesCases: AuthFactoryUseCases
//
//    @RelaxedMockK
//    private lateinit var childrenUser: ChildrenFactory
//
//    @RelaxedMockK
//    private lateinit var getCurrentUserUseCase: GetCurrentUserUseCase
//
//    @RelaxedMockK
//    private lateinit var firebaseUser: FirebaseUser
//
//    @RelaxedMockK
//    lateinit var dataRolStorageFactory: DataRolStorageFactory
//
//    @OptIn(ExperimentalCoroutinesApi::class)
//    @Before
//    fun setup() {
//        MockKAnnotations.init(this)
//        Dispatchers.setMain(Dispatchers.Unconfined)
//        every { authUsesCases.getCurrentUserUseCase } returns mockk()
//        every { authUsesCases.getCurrentUserUseCase } returns getCurrentUserUseCase
//        every { getCurrentUserUseCase.invoke() } returns firebaseUser
//        every { firebaseUser.uid } returns "1234"
//        viewModel =
//            ChildrenProfileViewModel(authUsesCases, dataRolStorageFactory, childrenUser, mockk())
//    }
//
//    @Test
//    fun `get the user of the current Firebase connection`() = runTest {
//        val uid = "1234"
//        val user = ChildrenModel(id = uid)
//        coEvery { childrenUser.getChildrenById(uid) } returns flowOf(user)
//
//        viewModel.getUserData()
//
//        coVerify { childrenUser.getChildrenById(uid) }
//        assertEquals(viewModel.userData, user)
//    }
//
//    @Test
//    fun logoutSessionActual() = runTest {
//        viewModel.logout()
//
//        coVerify {
//            authUsesCases.logoutUseCase()
//            dataRolStorageFactory.putRolValue(
//                PreferencesConstant.PREFERENCE_ROL_CURRENT,
//                EMPTY_STRING
//            )
//        }
//    }
//
//    @Test
//    fun testUpdateLevel() = runTest {
//        viewModel.userData = ChildrenModel(id = "1234")
//
//        viewModel.updateLevel()
//
//        coVerify { childrenUser.updateLevelChildren(any()) }
//    }
//
//    @OptIn(ExperimentalCoroutinesApi::class)
//    @After
//    fun tearDown() {
//        clearAllMocks()
//        Dispatchers.resetMain()
//        coroutineRule.testDispatcher.cleanupTestCoroutines()
//    }
//}
//
//@ExperimentalCoroutinesApi
//class CoroutineTestRule(
//    val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
//) : TestWatcher() {
//
//    override fun starting(description: Description) {
//        super.starting(description)
//        Dispatchers.setMain(testDispatcher)
//    }
//
//    override fun finished(description: Description) {
//        super.finished(description)
//        Dispatchers.resetMain()
//        testDispatcher.cleanupTestCoroutines()
//    }
}