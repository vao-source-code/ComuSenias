package com.example.comusenias.constants

import kotlin.random.Random

object NumberConstants {

    enum class Number(val value: Int) {
        UNO(1), DOS(2), TRES(3), CUATRO(4), CINCO(5),
        SEIS(6), SIETE(7), OCHO(8), NUEVE(9), DIEZ(10);

        override fun toString(): String {
            return value.toString()
        }
    }

    fun getRandomNumber(): Number {
        val values = Number.values()
        val randomIndex = Random.nextInt(values.size)
        return values[randomIndex]
    }
}