package com.example.comusenias.core.di

import com.example.comusenias.constants.FirebaseConstants.LETTERS_COLLECTION
import com.example.comusenias.constants.FirebaseConstants.USERS_COLLECTION
import com.example.comusenias.data.repositories.AuthRepositoryImpl
import com.example.comusenias.data.repositories.LetterImageRepositoryImpl
import com.example.comusenias.data.repositories.UsersRepositoryImpl
import com.example.comusenias.domain.repositories.AuthRepository
import com.example.comusenias.domain.repositories.LetterImageRepository
import com.example.comusenias.domain.repositories.UsersRepository
import com.example.comusenias.domain.use_cases.auth.AuthFactoryUseCases
import com.example.comusenias.domain.use_cases.auth.GetCurrentUserUseCase
import com.example.comusenias.domain.use_cases.auth.LoginUseCase
import com.example.comusenias.domain.use_cases.auth.LogoutUseCase
import com.example.comusenias.domain.use_cases.auth.RegisterUseCase
import com.example.comusenias.domain.use_cases.letters.GetImageUseCase
import com.example.comusenias.domain.use_cases.letters.LettersFactoryUseCases
import com.example.comusenias.domain.use_cases.letters.SearchImageLetterUseCase
import com.example.comusenias.domain.use_cases.users.CreateUserUseCase
import com.example.comusenias.domain.use_cases.users.GetUserByIdUseCase
import com.example.comusenias.domain.use_cases.users.SaveImageUserUseCase
import com.example.comusenias.domain.use_cases.users.UpdateUserUseCase
import com.example.comusenias.domain.use_cases.users.UsersFactoryUseCases
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@InstallIn(SingletonComponent::class)
@Module
object FirebaseModule {

    /*----------------------------- Auth -------------------------------------------------------- */
    @Provides
    fun providerFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    fun providerFirebaseUser(firebaseAuth: FirebaseAuth) = firebaseAuth.currentUser

    @Provides
    fun providerAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun providerAuthUseCases(authRepository: AuthRepository) =
        AuthFactoryUseCases(
            getCurrentUserUseCase = GetCurrentUserUseCase(authRepository),
            loginUseCase = LoginUseCase(authRepository),
            logoutUseCase = LogoutUseCase(authRepository),
            registerUseCase = RegisterUseCase(authRepository)
        )

    /*----------------------------- Firestore --------------------------------------------------- */

    @Provides
    fun providerFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    @Named(USERS_COLLECTION)
    fun providerUserRef(db: FirebaseFirestore): CollectionReference =
        db.collection(USERS_COLLECTION)

    @Provides
    @Named(LETTERS_COLLECTION)
    fun providerLettersRef(db: FirebaseFirestore): CollectionReference =
        db.collection(LETTERS_COLLECTION)


    @Provides
    fun providerUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl

    @Provides
    fun providerUsersUseCases(usersRepository: UsersRepository) = UsersFactoryUseCases(
        createUserUseCase = CreateUserUseCase(usersRepository),
        getUserByIdUseCase = GetUserByIdUseCase(usersRepository),
        updateUserUseCase = UpdateUserUseCase(usersRepository),
        saveImageUserUseCase = SaveImageUserUseCase(usersRepository)
    )

    @Provides
    fun providerLetterImageRepository(impl: LetterImageRepositoryImpl): LetterImageRepository = impl

    @Provides
    fun providerLettersUseCases(letterImageRepository: LetterImageRepository) = LettersFactoryUseCases(
        getImageUseCase = GetImageUseCase(letterImageRepository),
        searchImageLetterUseCase = SearchImageLetterUseCase(letterImageRepository)
    )

    /*----------------------------- Storage ----------------------------------------------------- */
    @Provides
    fun providerFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    @Named(USERS_COLLECTION)
    fun providerStorageRefUsers(storage: FirebaseStorage) =
        storage.reference.child(USERS_COLLECTION)

    @Provides
    @Named(LETTERS_COLLECTION)
    fun providerStorageRefLetters(storage: FirebaseStorage) =
        storage.reference.child(LETTERS_COLLECTION)

}