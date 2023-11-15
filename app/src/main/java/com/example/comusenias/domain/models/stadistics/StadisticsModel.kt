package com.example.comusenias.domain.models.stadistics

data class StadisticsModel(
    var chooseLetter : MutableMap<String, String> = mutableMapOf(),
    var chooseSign : MutableMap<String, String> = mutableMapOf(),
    var stadisticId: String = "",
    var cantidaDeIntentos: Int = 200,
    var cantidadDeErrores: Int = 100,
)