package com.example.comusenias.domain.use_cases.letters

import com.example.comusenias.domain.repositories.LetterImageRepository
import javax.inject.Inject

class GetImageUseCase @Inject constructor(private val repository: LetterImageRepository) {

    suspend operator fun invoke(letter: String ) = repository.getLetterImage(letter)
}