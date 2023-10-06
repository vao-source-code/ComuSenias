package com.example.comusenias.domain.models.user

import junit.framework.TestCase.assertNotNull
import org.junit.Test

class UserTest {

    @Test
    fun whenCreateUser_returnUserJson() {
        val user = User(
            "1",
            "userName",
            "email",
            "password",
            "image"
        )
        val jsonString = user.toJson()

        assertNotNull(jsonString)
    }
}