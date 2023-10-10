import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.comusenias.presentation.component.gameAction.MatchLetter
import org.junit.Rule
import org.junit.Test


class MatchLetterComposableTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    val letter = "a"
    val randomLetter = "b"

    @Test
    fun testWhenMatchLetterIsCorrect() {
        var isCorrect = false
        val singLetter = letter
        val randomSignLetter = randomLetter

        composeTestRule.setContent {
            MatchLetter(
                singLetter = singLetter,
                randomLetter = randomLetter
            ) {
                isCorrect = it
            }
        }

        composeTestRule.onAllNodesWithText(singLetter).assertCountEquals(1)
        composeTestRule.onAllNodesWithText(randomSignLetter).assertCountEquals(1)
        composeTestRule.onNodeWithText(singLetter).performClick()

        assert(isCorrect)
    }

    @Test
    fun matchLetterIncorrectStateTest() {
        var isCorrect = true
        val singLetter = letter
        val randomSignLetter = randomLetter

        composeTestRule.setContent {
            MatchLetter(
                singLetter = singLetter,
                randomLetter = randomSignLetter
            ) {
                isCorrect = it
            }
        }

        composeTestRule.onAllNodesWithText(singLetter).assertCountEquals(1)
        composeTestRule.onAllNodesWithText(randomSignLetter).assertCountEquals(1)
        composeTestRule.onNodeWithText(randomSignLetter).performClick()

        assert(!isCorrect)
    }
}


