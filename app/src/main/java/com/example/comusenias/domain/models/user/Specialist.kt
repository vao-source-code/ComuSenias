package com.example.comusenias.domain.models.user

data class Specialist(
    val medicalLicense: String,
    val medicalLicenseExpiration: String,
    val speciality: String,
    val kidInCharge: MutableList<Kid>,
    val numberPhone: String

) : User() {
    fun addKid(kid: Kid): Boolean {
        return if (kidInCharge.contains(kid)) {
            false
        } else {
            kidInCharge.add(kid)
            true
        }
    }

    fun deleteKid(kid: Kid): Boolean = kidInCharge.remove(kid)

    fun viewProfileKid(idKid: String) {
        //TODO: implement
    }

    fun viewStatisticsKid(idKid: String) {
        //TODO: implement
    }

    fun makeObservation(idKid: String, observation: String) {
        val kid = kidInCharge.find { it.id == idKid }
        kid?.let {
            it.observation = observation
        }
    }

    fun deleteProfile() {
        //TODO implement with firebase
    }
}