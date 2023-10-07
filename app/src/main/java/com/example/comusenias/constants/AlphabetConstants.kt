package com.example.comusenias.constants

import kotlin.random.Random

object AlphabetConstants {

    enum class Letter(private val letter: Char) {
        A('A'), B('B'), C('C'), D('D'), E('E'), F('F'),
        G('G'), H('H'), I('I'), J('J'),
        K('K'), L('L'), M('M'), N('N'), Ñ('Ñ'), O('O'),
        P('P'), Q('Q'), R('R'), S('S'), T('T'),
        U('U'), V('V'), W('W'), X('X'), Y('Y'), Z('Z');

        override fun toString(): String {
            return letter.toString()
        }
    }

    fun getRandomLetter(): Letter {
        val values = Letter.values()
        val randomIndex = Random.nextInt(values.size)
        return values[randomIndex]
    }
}