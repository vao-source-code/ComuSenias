package com.example.comusenias.core.di

import com.google.firebase.auth.FirebaseAuth
import com.example.comusenias.data.repositories.AuthRepositoryImpl
import com.example.comusenias.domain.repositories.AuthRepository
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