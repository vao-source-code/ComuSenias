package com.example.comusenias.presentation.screen.specialist

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.component.home.StatusGame
import com.example.comusenias.presentation.component.specialist.home.Title
import com.example.comusenias.presentation.component.statitics.ContentBarChart
import com.example.comusenias.presentation.extensions.statitics.StatisticsCalculator.AttemptType.FAILURE
import com.example.comusenias.presentation.extensions.statitics.StatisticsCalculator.AttemptType.SUCCESS
import com.example.comusenias.presentation.extensions.statitics.StatisticsCalculator.createBarList
import com.example.comusenias.presentation.ui.theme.CORRECT
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.example.comusenias.presentation.ui.theme.INCORRECT
import com.example.comusenias.presentation.ui.theme.SIZE20
import com.example.comusenias.presentation.ui.theme.SIZE36
import com.example.comusenias.presentation.ui.theme.greenColorApp

@Composable
fun StatisticsScreen() {
    StatisticsComponent(levelModel = LevelModelMock.getMockLevels()[1])
}

@Composable
fun StatisticsComponent(levelModel: LevelModelMock) {
    val statistics = listOf(
        createBarList(
            levelModel.subLevel,
            greenColorApp,
            CORRECT,
            SUCCESS
        ),
        createBarList(
            levelModel.subLevel,
            Red,
            INCORRECT,
            FAILURE
        ),
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Spacer(modifier = Modifier.height(SIZE20.dp))
            Title(
                "Nivel: ${ levelModel.name}",
                Center
                )
        }
        item {
            ContentBarChart(statistics = statistics)
        }
        item {
            Spacer(modifier = Modifier.height(SIZE36.dp))
        }
    }
}

data class SubLevelModelMock(
    var name: String = EMPTY_STRING,
    var failures: Int = 0,
    var successes: Int = 0
)

data class LevelModelMock(
    var name: String = EMPTY_STRING,
    var subLevel: MutableList<SubLevelModelMock> = mutableListOf(),
    var isCompleted: StatusGame = StatusGame.IN_PROGRESS
) {
    companion object {
        fun getMockLevels(): List<LevelModelMock> {
            val levels = mutableListOf<LevelModelMock>()

            val level1 = LevelModelMock(
                name = "Vocales",
                subLevel = mutableListOf(
                    SubLevelModelMock(name = "A", failures = 2, successes = 5),
                    SubLevelModelMock(name = "E", failures = 1, successes = 8),
                    SubLevelModelMock(name = "I", failures = 4, successes = 10),
                    SubLevelModelMock(name = "O", failures = 2, successes = 10),
                    SubLevelModelMock(name = "U", failures = 5, successes = 7),
                ),
                isCompleted = StatusGame.IN_PROGRESS
            )

            val level2 = LevelModelMock(
                name = "Palabras",
                subLevel = mutableListOf(
                    SubLevelModelMock(name = "Hola", failures = 2, successes = 5),
                    SubLevelModelMock(name = "Chau", failures = 1, successes = 8),
                    SubLevelModelMock(name = "Permiso", failures = 4, successes = 10),
                    SubLevelModelMock(name = "Por Favor", failures = 2, successes = 10),
                    SubLevelModelMock(name = "Buen dia", failures = 5, successes = 7),
                ),
                isCompleted = StatusGame.BLOCKED
            )

            levels.add(level1)
            levels.add(level2)

            return levels
        }
    }
}