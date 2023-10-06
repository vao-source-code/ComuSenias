package com.example.comusenias.domain.models.user

import com.example.comusenias.domain.models.games.Level
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class SpecialistTest {

    private lateinit var specialist: Specialist
    private lateinit var kid: Kid

    @Before
    fun setUp() {
        specialist = Specialist(
            "Pediatra",
            "123456",
            "",
            mutableListOf<Kid>(),
            ""
        )
        kid = Kid(
            "",
            mockk(),
            null,
            false,
            mockk(),
            listOf(),
               listOf(),
            null,
            listOf()
        )
    }

    @Test
    fun whenTheSpecialistAddsChildren_WhoIsNotOnTheList_ThenTheChildIsAdded() {

        val result = specialist.addKid(kid)

        assert(result)
    }


    @Test
    fun whenTheSpecialistAddsChildren_WhoIsAlreadyOnTheList_ThenTheChildIsNotAdded() {

        specialist.addKid(kid)
        val result = specialist.addKid(kid)

        assert(!result)
    }


    @Test
    fun whenTheSpecialistDeletesChildren_WhoIsOnTheList_ThenTheChildIsDeleted() {
        specialist.addKid(kid)

        val result = specialist.deleteKid(kid)

        assert(result)
    }

    @Test
    fun whenTheSpecialistDeletesChildren_WhoIsNotOnTheList_ThenTheChildIsNotDeleted() {

        val result = specialist.deleteKid(kid)

        assert(!result)
    }
}