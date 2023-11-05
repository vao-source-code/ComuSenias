package com.example.comusenias.core.di

import com.example.comusenias.constants.FirebaseConstants.CHILDREN_COLLECTION
import com.example.comusenias.constants.FirebaseConstants.GAME_COLLECTION
import com.example.comusenias.constants.FirebaseConstants.LETTERS_COLLECTION
import com.example.comusenias.constants.FirebaseConstants.LEVEL_COLLECTION
import com.example.comusenias.constants.FirebaseConstants.OBSERVATION_COLLECTION
import com.example.comusenias.constants.FirebaseConstants.SPECIALIST_COLLECTION
import com.example.comusenias.constants.FirebaseConstants.SUB_LEVEL_COLLECTION
import com.example.comusenias.constants.FirebaseConstants.USERS_COLLECTION
import com.example.comusenias.data.repositories.ChildrenRepositoryImpl
import com.example.comusenias.data.repositories.ObservationRepositoryImpl
import com.example.comusenias.data.repositories.SpecialistRepositoryImpl
import com.example.comusenias.data.repositories.firebase.AuthRepositoryImpl
import com.example.comusenias.data.repositories.firebase.LetterImageRepositoryImpl
import com.example.comusenias.data.repositories.firebase.LevelRepositoryImpl
import com.example.comusenias.data.repositories.firebase.UsersRepositoryImpl
import com.example.comusenias.domain.repositories.AuthRepository
import com.example.comusenias.domain.repositories.ChildrenRepository
import com.example.comusenias.domain.repositories.LetterImageRepository
import com.example.comusenias.domain.repositories.LevelRepository
import com.example.comusenias.domain.repositories.ObservationRepository
import com.example.comusenias.domain.repositories.SpecialistRepository
import com.example.comusenias.domain.repositories.UsersRepository
import com.example.comusenias.domain.use_cases.auth.AuthFactoryUseCases
import com.example.comusenias.domain.use_cases.auth.GetCurrentUserUseCase
import com.example.comusenias.domain.use_cases.auth.LoginUseCase
import com.example.comusenias.domain.use_cases.auth.LogoutUseCase
import com.example.comusenias.domain.use_cases.auth.RegisterUseCase
import com.example.comusenias.domain.use_cases.auth.ResetPasswordUseCase
import com.example.comusenias.domain.use_cases.children.ChildrenFactory
import com.example.comusenias.domain.use_cases.children.CreateChildren
import com.example.comusenias.domain.use_cases.children.GetChildrenById
import com.example.comusenias.domain.use_cases.children.IntegrateChildrenWithSpecialist
import com.example.comusenias.domain.use_cases.children.SaveImageChildren
import com.example.comusenias.domain.use_cases.children.UpdateChildren
import com.example.comusenias.domain.use_cases.letters.GetImageUseCase
import com.example.comusenias.domain.use_cases.letters.LettersFactoryUseCases
import com.example.comusenias.domain.use_cases.letters.SearchImageLetterUseCase
import com.example.comusenias.domain.use_cases.level.GetLevels
import com.example.comusenias.domain.use_cases.level.LevelFactory
import com.example.comusenias.domain.use_cases.level.SearchLevelName
import com.example.comusenias.domain.use_cases.observation.CreateObservation
import com.example.comusenias.domain.use_cases.observation.GetObservation
import com.example.comusenias.domain.use_cases.observation.GetObservationById
import com.example.comusenias.domain.use_cases.observation.ObservationFactory
import com.example.comusenias.domain.use_cases.observation.UpdateObservation
import com.example.comusenias.domain.use_cases.specialist.CreateSpecialist
import com.example.comusenias.domain.use_cases.specialist.GetChildrenForSpecialistById
import com.example.comusenias.domain.use_cases.specialist.GetSpecialistById
import com.example.comusenias.domain.use_cases.specialist.SaveImageSpecialist
import com.example.comusenias.domain.use_cases.specialist.SpecialistFactory
import com.example.comusenias.domain.use_cases.specialist.UpdateSpecialist
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
            registerUseCase = RegisterUseCase(authRepository),
            resetPasswordUseCase = ResetPasswordUseCase(authRepository)
        )

    /*----------------------------- Firestore --------------------------------------------------- */

    @Provides
    fun providerFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    /*----------------------------- Firestore Collections ---------------------------------------- */
    @Provides
    @Named(USERS_COLLECTION)
    fun providerUserRef(db: FirebaseFirestore): CollectionReference =
        db.collection(USERS_COLLECTION)

    @Provides
    @Named(CHILDREN_COLLECTION)
    fun providerChildrenRef(db: FirebaseFirestore): CollectionReference =
        db.collection(CHILDREN_COLLECTION)

    @Provides
    @Named(LETTERS_COLLECTION)
    fun providerLettersRef(db: FirebaseFirestore): CollectionReference =
        db.collection(LETTERS_COLLECTION)

    @Provides
    @Named(LEVEL_COLLECTION)
    fun providerLevelRef(db: FirebaseFirestore): CollectionReference =
        db.collection(LEVEL_COLLECTION)

    @Provides
    @Named(SUB_LEVEL_COLLECTION)
    fun providerSubLevelRef(db: FirebaseFirestore): CollectionReference =
        db.collection(SUB_LEVEL_COLLECTION)

    @Provides
    @Named(GAME_COLLECTION)
    fun providerGameRef(db: FirebaseFirestore): CollectionReference = db.collection(GAME_COLLECTION)

    @Provides
    @Named(SPECIALIST_COLLECTION)
    fun providerSpecialistRef(db: FirebaseFirestore): CollectionReference =
        db.collection(SPECIALIST_COLLECTION)

    @Provides
    @Named(OBSERVATION_COLLECTION)
    fun providerObservationRef(db: FirebaseFirestore): CollectionReference =
        db.collection(OBSERVATION_COLLECTION)

    /*----------------------------- Repositories ------------------------------------------------ */

    @Provides
    fun providerUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl

    @Provides
    fun providerChildrenRepository(impl: ChildrenRepositoryImpl): ChildrenRepository = impl

    @Provides
    fun providerLetterImageRepository(impl: LetterImageRepositoryImpl): LetterImageRepository = impl

    @Provides
    fun providerLevelRepository(impl: LevelRepositoryImpl): LevelRepository = impl

    @Provides
    fun providerSpecialistRepository(impl: SpecialistRepositoryImpl): SpecialistRepository = impl

    @Provides
    fun providerObservationRepository(impl: ObservationRepositoryImpl): ObservationRepository = impl

    /*----------------------------- Use Cases --------------------------------------------------- */
    @Provides
    fun providerUsersUseCases(usersRepository: UsersRepository) = UsersFactoryUseCases(
        createUserUseCase = CreateUserUseCase(usersRepository),
        getUserByIdUseCase = GetUserByIdUseCase(usersRepository),
        updateUserUseCase = UpdateUserUseCase(usersRepository),
        saveImageUserUseCase = SaveImageUserUseCase(usersRepository)
    )

    @Provides
    fun providerLettersUseCases(letterImageRepository: LetterImageRepository) =
        LettersFactoryUseCases(
            getImageUseCase = GetImageUseCase(letterImageRepository),
            searchImageLetterUseCase = SearchImageLetterUseCase(letterImageRepository)
        )

    @Provides
    fun providerLevelUseCases(levelRepository: LevelRepository) =
        LevelFactory(
            getLevels = GetLevels(levelRepository),
            searchLevelName = SearchLevelName(levelRepository)
        )

    @Provides
    fun providerChildrenUseCases(usersRepository: ChildrenRepository) =
        ChildrenFactory(
            createChildren = CreateChildren(usersRepository),
            getChildrenById = GetChildrenById(usersRepository),
            saveImageChildren = SaveImageChildren(usersRepository),
            updateChildren = UpdateChildren(usersRepository),
            integrateChildrenWithSpecialist = IntegrateChildrenWithSpecialist(usersRepository)
        )

    @Provides
    fun providerSpecialistUseCases(usersRepository: SpecialistRepository) =
        SpecialistFactory(
            createSpecialist = CreateSpecialist(usersRepository),
            getSpecialistById = GetSpecialistById(usersRepository),
            saveImageSpecialist = SaveImageSpecialist(usersRepository),
            updateSpecialist = UpdateSpecialist(usersRepository),
            getChildrenForSpecialistById = GetChildrenForSpecialistById(usersRepository)

        )

    @Provides
    fun providerObservationUseCases(observationRepository: ObservationRepository) =
        ObservationFactory(
            createObservation = CreateObservation(observationRepository),
            getObservationById = GetObservationById(observationRepository),
            updateObservation = UpdateObservation(observationRepository),
            getObservation = GetObservation(observationRepository)
        )

    /*----------------------------- Storage ----------------------------------------------------- */
    @Provides
    fun providerFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    @Named(USERS_COLLECTION)
    fun providerStorageRefUsers(storage: FirebaseStorage) =
        storage.reference.child(USERS_COLLECTION)

    @Provides
    @Named(CHILDREN_COLLECTION)
    fun providerStorageRefChildren(storage: FirebaseStorage) =
        storage.reference.child(CHILDREN_COLLECTION)

    @Provides
    @Named(LETTERS_COLLECTION)
    fun providerStorageRefLetters(storage: FirebaseStorage) =
        storage.reference.child(LETTERS_COLLECTION)

    @Provides
    @Named(LEVEL_COLLECTION)
    fun providerStorageRefLevel(storage: FirebaseStorage) =
        storage.reference.child(LEVEL_COLLECTION)

    @Provides
    @Named(SUB_LEVEL_COLLECTION)
    fun providerStorageRefSubLevel(storage: FirebaseStorage) =
        storage.reference.child(SUB_LEVEL_COLLECTION)

    @Provides
    @Named(SPECIALIST_COLLECTION)
    fun providerStorageRefSpecialist(storage: FirebaseStorage) =
        storage.reference.child(SPECIALIST_COLLECTION)

}