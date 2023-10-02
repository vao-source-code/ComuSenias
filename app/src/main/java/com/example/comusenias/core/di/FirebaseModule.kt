package com.example.comusenias.core.di

import com.example.comusenias.constants.FirebaseConstants.LETTERS_COLLECTION
import com.example.comusenias.constants.FirebaseConstants.USERS_COLLECTION
import com.example.comusenias.data.repositories.AuthRepositoryImpl
import com.example.comusenias.data.repositories.LetterImageRepositoryImpl
import com.example.comusenias.data.repositories.UsersRepositoryImpl
import com.example.comusenias.domain.repositories.AuthRepository
import com.example.comusenias.domain.repositories.LetterImageRepository
import com.example.comusenias.domain.repositories.UsersRepository
import com.example.comusenias.domain.use_cases.auth.AuthUseCases
import com.example.comusenias.domain.use_cases.auth.GetCurrentUser
import com.example.comusenias.domain.use_cases.auth.Login
import com.example.comusenias.domain.use_cases.auth.Logout
import com.example.comusenias.domain.use_cases.auth.Register
import com.example.comusenias.domain.use_cases.letters.GetImage
import com.example.comusenias.domain.use_cases.letters.LettersUseCase
import com.example.comusenias.domain.use_cases.users.CreateUser
import com.example.comusenias.domain.use_cases.users.GetUserById
import com.example.comusenias.domain.use_cases.users.SaveImageUser
import com.example.comusenias.domain.use_cases.users.UpdateUser
import com.example.comusenias.domain.use_cases.users.UsersUseCase
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

    /*----------------------------- Auth --------------------------------------------------- */
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

    /*----------------------------- Firestore --------------------------------------------------- */

    @Provides
    fun providerFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    @Named(USERS_COLLECTION)
    fun providerUserRef(db: FirebaseFirestore): CollectionReference =
        db.collection(USERS_COLLECTION)


    @Provides
    fun providerUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl

    @Provides
    fun providerUsersUseCases(usersRepository: UsersRepository) = UsersUseCase(
        createUser = CreateUser(usersRepository),
        getUserById = GetUserById(usersRepository),
        updateUser = UpdateUser(usersRepository),
        saveImageUser = SaveImageUser(usersRepository)
    )

    @Provides
    fun providerLetterImageRepository(impl: LetterImageRepositoryImpl): LetterImageRepository = impl

    @Provides
    fun providerLettersUseCases(letterImageRepository: LetterImageRepository) = LettersUseCase(
        getImage = GetImage(letterImageRepository)
    )

    /*----------------------------- Storage --------------------------------------------------- */
    @Provides
    fun providerFirebaseStorage() : FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    @Named(USERS_COLLECTION)
    fun providerStorageRefUsers(storage : FirebaseStorage) = storage.reference.child(USERS_COLLECTION)

    @Provides
    @Named(LETTERS_COLLECTION)
    fun providerStorageRefLetters(storage : FirebaseStorage) = storage.reference.child(LETTERS_COLLECTION)


}