import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.comusenias.presentation.component.gameAction.MatchLetter
import org.junit.Rule
import org.junit.Test


class MatchLetterComposableTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testWhenMatchLetterIsCorrect() {
        var isCorrect = false
        val singLetter = "A"
        val randomLetter = "B"

        composeTestRule.setContent {
            MatchLetter(
                singLetter = singLetter,
                randomLetter = randomLetter
            ) {
                isCorrect = it
            }
        }

        composeTestRule.onAllNodesWithText(singLetter).assertCountEquals(1)
        composeTestRule.onAllNodesWithText(randomLetter).assertCountEquals(1)
        composeTestRule.onNodeWithText(singLetter).performClick()

        assert(isCorrect)
    }

    @Test
    fun matchLetterIncorrectStateTest() {
        var isCorrect = true
        val singLetter = "A"
        val randomLetter = "B"

        composeTestRule.setContent {
            MatchLetter(
                singLetter = singLetter,
                randomLetter = randomLetter
            ) {
                isCorrect = it
            }
        }

        composeTestRule.onAllNodesWithText(singLetter).assertCountEquals(1)
        composeTestRule.onAllNodesWithText(randomLetter).assertCountEquals(1)
        composeTestRule.onNodeWithText(randomLetter).performClick()

        assert(!isCorrect)
    }
}


