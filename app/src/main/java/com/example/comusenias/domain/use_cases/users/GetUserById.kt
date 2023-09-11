package com.example.comusenias.domain.use_cases.users

import com.example.comusenias.domain.repositories.UsersRepository
import javax.inject.Inject

class GetUserById  @Inject constructor(private val usersRepository: UsersRepository) {

    operator fun invoke(id: String) = usersRepository.getUserById(id)
}