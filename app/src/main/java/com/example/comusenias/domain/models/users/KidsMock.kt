package com.example.comusenias.domain.models.users

import com.example.comusenias.R
import com.example.comusenias.domain.models.game.LevelModel
import com.example.comusenias.domain.models.game.SubLevelModel
import com.example.comusenias.presentation.component.home.StatusGame

class KidsMock {

    val subLevel = mutableListOf(
        SubLevelModel(
            "id",
            "A",
            image = R.drawable.letra_a.toString(),
            imageOnly = R.drawable.letra_a_solo.toString(),
            randomLetter = "O",
            randomImage = R.drawable.sign_o.toString(),

        ),
        SubLevelModel(
            "id",
            "O",
            image = R.drawable.sign_o.toString(),
            imageOnly = R.drawable.sign_o.toString(),
            randomLetter = "A",
            randomImage = R.drawable.letra_a_solo.toString(),
            isCompleted = StatusGame.IN_PROGRESS
        )
    )

    val levelList: List<LevelModel> = listOf(
        LevelModel(
            "Vocales",
            "Vocales",
            subLevel = subLevel,
        ),
    )

    val userModel = UserModel(
        id = "1",
        //userName = "Pablo Carballo",
        email = "a@a.com",
        password = "Unlam123",
        //image = null,
        //numberPhone = "123456789"
    )

    val kidMock = ChildrenModel(
        //userModel = userModel,
        date = "12/12/2021",
        specialist = null,
        levelActual = 1,
        subLevelActual = 1,
        isPremium = false,
        levels = levelList,
        id = "1",
        name = "Pablo Carballo",
        tel = "123456789",
        idSpecialist = "1"
    )

    companion object {
        val instance = KidsMock()
    }

}