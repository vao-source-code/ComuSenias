package com.example.comusenias.core.di

import android.app.Application
import android.view.Surface
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import com.google.firebase.auth.FirebaseAuth
import com.example.comusenias.data.repositories.AuthRepositoryImpl
import com.example.comusenias.data.repositories.CustomCameraRepositoryImpl
import com.example.comusenias.domain.repositories.AuthRepository
import com.example.comusenias.domain.repositories.CustomCameraRepository
import com.example.comusenias.domain.use_cases.auth.AuthUseCases
import com.example.comusenias.domain.use_cases.auth.GetCurrentUser
import com.example.comusenias.domain.use_cases.auth.Login
import com.example.comusenias.domain.use_cases.auth.Logout
import com.example.comusenias.domain.use_cases.auth.Register
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object FirebaseModule {

    @Provides
    fun providerFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    fun providerFirebaseUser(firebaseAuth: FirebaseAuth) = firebaseAuth.currentUser

    @Provides
    fun providerAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun providerAuthUseCases(authRepository: AuthRepository) =
        AuthUseCases(
            getCurrentUser = GetCurrentUser(authRepository),
            login = Login(authRepository),
            logout = Logout(authRepository),
            register = Register(authRepository)
        )



}