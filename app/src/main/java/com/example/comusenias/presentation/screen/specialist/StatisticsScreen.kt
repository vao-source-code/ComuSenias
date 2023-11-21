package com.example.comusenias.presentation.screen.specialist

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.comusenias.domain.models.game.LevelModel
import com.example.comusenias.presentation.component.home.StatusGame
import com.example.comusenias.presentation.component.home.StatusGame.BLOCKED
import com.example.comusenias.presentation.component.home.StatusGame.IN_PROGRESS
import com.example.comusenias.presentation.component.statitics.StatisticsComponent
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.example.comusenias.presentation.ui.theme.SIZE10
import com.example.comusenias.presentation.ui.theme.SIZE36

@Composable
fun StatisticsScreen() {
    StatisticsContent()
}

@Composable
fun StatisticsContent(
    levelModelMock: List<LevelModel>? = null,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Spacer(modifier = Modifier.height(SIZE10.dp))
        }
        items(LevelModelMock.getMockLevels()) {
            StatisticsComponent(levelModel = it)
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
    var isCompleted: StatusGame = IN_PROGRESS
) {
    companion object {
        fun getMockLevels(): List<LevelModelMock> {
            val levels = mutableListOf<LevelModelMock>()

            val level1 = LevelModelMock(
                name = "Level 1: Vocales",
                subLevel = mutableListOf(
                    SubLevelModelMock(name = "A", failures = 7, successes = 7),
                    SubLevelModelMock(name = "E", failures = 5, successes = 7),
                    SubLevelModelMock(name = "I", failures = 12, successes = 5),
                    SubLevelModelMock(name = "O", failures = 9, successes = 8),
                    SubLevelModelMock(name = "U", failures = 5, successes = 7),
                ),
                isCompleted = IN_PROGRESS
            )

            val level2 = LevelModelMock(
                name = "Level 2: Palabras",
                subLevel = mutableListOf(
                    SubLevelModelMock(name = "Hola", failures = 2, successes = 5),
                    SubLevelModelMock(name = "Chau", failures = 1, successes = 8),
                    SubLevelModelMock(name = "Permiso", failures = 4, successes = 10),
                    SubLevelModelMock(name = "Por Favor", failures = 2, successes = 10),
                    SubLevelModelMock(name = "Buen dia", failures = 5, successes = 7),
                ),
                isCompleted = BLOCKED
            )

            levels.add(level1)
            levels.add(level2)

            return levels
        }
    }
}