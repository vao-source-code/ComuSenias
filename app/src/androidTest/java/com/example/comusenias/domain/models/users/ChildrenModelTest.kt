package com.example.comusenias.domain.models.users

import org.junit.Before
import org.junit.Test


class ChildrenModelTest {

    private lateinit var userModel: UserModel
    private lateinit var specialist: SpecialistModel
    private lateinit var childrenModel: ChildrenModel

    @Before
    fun setUp() {
        userModel = UserModel(
            userName = "name",
            password = "password",
            email = "email",
        )

        specialist = SpecialistModel(
            userModel,
            "12.12.2021",
            "license",
            "12.12.2021",
            "speciality",
            listOf()
        )


        childrenModel = ChildrenModel(
            userModel = userModel,
            date = "12.12.2021",
            specialist = specialist,
            isPremium = false,
            levels = listOf(),
            observation = listOf()
        )
    }

    @Test
    fun `when`() {
      val chidrenModel = ChildrenModel(
          userModel = userModel,
          date = "12.12.2021",
          specialist = specialist,
          isPremium = false,
          levels = listOf(),
          observation = listOf()
      )

        childrenModel.date = "12.12.2021"
    }

}