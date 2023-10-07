package com.example.comusenias.domain.models.state

import com.example.comusenias.presentation.ui.theme.EMPTY_STRING

data class ChangeProfileState(
    var userName: String = EMPTY_STRING,
    var image: String = EMPTY_STRING
)
