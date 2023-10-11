package com.example.comusenias.domain.models.users

import org.junit.Assert.assertEquals
import org.junit.Test


class SpecialistModelTest {

    @Test
    fun shouldCreateASpecialistModelObjectWithAllProperties() {
        val userModel = UserModel("1", "Dr. Smith", "john.smith@example.com")
        val date = "2023-10-08"
        val medicalLicense = "1234567890"
        val medicalLicenseExpiration = "2024-10-08"
        val speciality = "Pediatrics"
        val childrenInCharge = listOf<ChildrenModel>()

        val specialistModel = SpecialistModel(
                userModel = userModel,
                date = date,
                medicalLicense = medicalLicense,
                medicalLicenseExpiration = medicalLicenseExpiration,
                speciality = speciality,
                childrenInCharge = childrenInCharge
        )

        assertEquals(userModel, specialistModel.userModel)
        assertEquals(date, specialistModel.date)
        assertEquals(medicalLicense, specialistModel.medicalLicense)
        assertEquals(medicalLicenseExpiration, specialistModel.medicalLicenseExpiration)
        assertEquals(speciality, specialistModel.speciality)
        assertEquals(childrenInCharge, specialistModel.childrenInCharge)
    }
}