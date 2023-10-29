package com.example.comusenias.domain.models.state

import com.example.comusenias.presentation.ui.theme.EMPTY_STRING

data class ChangeProfileState(
    var id: String = EMPTY_STRING,
    var name: String = EMPTY_STRING,
    var tel: String = EMPTY_STRING,
    var email: String = EMPTY_STRING,
    var image: String? = EMPTY_STRING,
    var date: String = EMPTY_STRING,
    var idSpecialist: String = EMPTY_STRING,
)
