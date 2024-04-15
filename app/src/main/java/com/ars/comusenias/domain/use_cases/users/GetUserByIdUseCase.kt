package com.ars.comusenias.domain.use_cases.users

import com.ars.comusenias.domain.repositories.UsersRepository
import javax.inject.Inject

class GetUserByIdUseCase  @Inject constructor(private val usersRepository: UsersRepository) {

    operator fun invoke(id: String) = usersRepository.getUserById(id)
}