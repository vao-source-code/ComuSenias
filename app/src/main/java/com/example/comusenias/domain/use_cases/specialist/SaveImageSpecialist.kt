package com.example.comusenias.domain.use_cases.specialist

import com.example.comusenias.domain.repositories.SpecialistRepository
import java.io.File
import javax.inject.Inject

class SaveImageSpecialist @Inject constructor(private val specialistRepository: SpecialistRepository) {
    suspend operator fun invoke(file: File) = specialistRepository.saveImageUserSpecialist(file)
}