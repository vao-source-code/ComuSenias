package com.example.comusenias.domain.models.users

import com.example.comusenias.presentation.ui.theme.EMPTY_STRING

data class SpecialistModel(
    var id: String = EMPTY_STRING,
    var name: String = EMPTY_STRING,
    var tel: String = EMPTY_STRING,
    var email: String = EMPTY_STRING,
    var date: String = EMPTY_STRING,
    var image: String? = EMPTY_STRING,
    var medicalLicense: String = EMPTY_STRING,
    var medicalLicenseExpiration: String = EMPTY_STRING,
    var speciality: String = EMPTY_STRING,
    var titleMedical: String = EMPTY_STRING,
    var childrenInCharge: List<ChildrenModel>? = listOf()
)
