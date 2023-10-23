package com.example.comusenias.presentation.gameAction

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.comusenias.R
import com.example.comusenias.constants.TestTag
import com.example.comusenias.presentation.component.gameAction.MatchSign
import com.example.comusenias.presentation.screen.gameAction.Sign
import com.example.comusenias.presentation.view_model.LevelViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchSignComposableTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val letter = "a"
    private val randomLetter = "o"

    @Mock
    private lateinit var levelViewModel: LevelViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
       levelViewModel = Mockito.mock(LevelViewModel::class.java)
    }

    @Test
    fun  testWhenMatchSignIsIncorrect() {
        var isCorrect = true
        val sign = Sign(imageResource = R.drawable.letra_a_solo.toString(), letter = letter)
        val randomSign = Sign(imageResource = R.drawable.sign_o.toString(), letter = randomLetter)

        composeTestRule.setContent {
            MatchSign(
                sign = sign,
                randomSign = randomSign,
            ) {
                isCorrect = it
            }
        }
        composeTestRule.onNodeWithTag(TestTag.TAG_MATCH_SIGN + randomLetter).assertExists()
        composeTestRule.onNodeWithTag(TestTag.TAG_MATCH_SIGN + letter).assertExists()
        composeTestRule.onNodeWithTag(TestTag.TAG_MATCH_SIGN + randomLetter).performClick()
        assert(!isCorrect)
    }

    @Test
    fun testWhenMatchSignCorrect() {
        var isCorrect = false
        val sign = Sign(imageResource = R.drawable.letra_a_solo.toString(), letter = letter)
        val randomSign = Sign(imageResource = R.drawable.sign_o.toString(), letter = randomLetter)

        composeTestRule.setContent {
            MatchSign(
                sign = sign,
                randomSign = randomSign,
            ) {
                isCorrect = it
            }
        }
        composeTestRule.onNodeWithTag(TestTag.TAG_MATCH_SIGN + randomLetter).assertExists()
        composeTestRule.onNodeWithTag(TestTag.TAG_MATCH_SIGN + letter).assertExists()
        composeTestRule.onNodeWithTag(TestTag.TAG_MATCH_SIGN + randomLetter).performClick()
        assert(!isCorrect)
    }
}