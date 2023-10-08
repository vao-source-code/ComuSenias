package com.example.comusenias.domain.use_cases.users

import com.example.comusenias.domain.repositories.UsersRepository
import java.io.File
import javax.inject.Inject

class SaveImageUserUseCase @Inject constructor(private val repository: UsersRepository){

    suspend operator fun invoke (file: File) = repository.saveImage(file)
}