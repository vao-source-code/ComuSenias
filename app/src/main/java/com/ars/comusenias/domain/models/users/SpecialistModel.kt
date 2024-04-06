package com.ars.comusenias.domain.models.users

import com.ars.comusenias.presentation.ui.theme.EMPTY_STRING
import com.google.gson.Gson

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

) {
    fun toJson(): String = Gson().toJson(
        SpecialistModel(
            id = id,
            name = name,
            tel = tel,
            email = email,
            image = image,
            date = date,
            medicalLicense = medicalLicense,
            medicalLicenseExpiration = medicalLicenseExpiration,
            speciality = speciality,
            titleMedical = titleMedical,
        )
    )

    companion object {
        fun fromJson(data: String): SpecialistModel =
            Gson().fromJson(data, SpecialistModel::class.java)
    }

}
