package com.example.comusenias.domain.use_cases.specialist


data class SpecialistFactory(
    val createSpecialist: CreateSpecialist,
    val getSpecialistById: GetSpecialistById,
    val updateSpecialist: UpdateSpecialist,
    val saveImageSpecialist: SaveImageSpecialist,
    val getChildrenForSpecialistById: GetChildrenForSpecialistById
)
