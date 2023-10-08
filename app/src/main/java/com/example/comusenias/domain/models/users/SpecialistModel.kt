package com.example.comusenias.domain.models.users

data class SpecialistModel(
    var userModel: UserModel,
    var date: String,
    var medicalLicense: String,
    var medicalLicenseExpiration: String,
    var speciality: String,
    var childrenInCharge: List<ChildrenModel>?
)
