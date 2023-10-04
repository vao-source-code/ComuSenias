package com.example.comusenias.presentation.component.gameAction

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.comusenias.presentation.ui.theme.size08
import com.example.comusenias.presentation.ui.theme.size1
import com.example.comusenias.presentation.ui.theme.size30

@Composable
fun GameAction(navController: NavController) {
    val endPage = 4
    val pageStar = 1
    val secondPage = 2
    val treePage = 3
    val currentPage = remember { mutableStateOf(pageStar) }
    var isButtonVisible by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(size30.dp)
    ) {
        TopSectionGameAction()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(size08),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (currentPage.value) {
                pageStar -> {
                    ContentLetterType(letter = "a")
                }
                secondPage -> {
                    isButtonVisible = false
                    MatchLetter(singLetter = "A", randomLetter = "u") {
                        isButtonVisible = true
                        Log.d(" MatchLetterResponse", "${it}")
                        Log.d(" isButtonVisible", "${isButtonVisible}")
                    }
                }
                else -> {
                    // Código si no hay coincidencia con ningún caso
                }
            }
        }
        BottomSectionGameAction(
            titleButton = "Continuar",
            buttonVisible = isButtonVisible
        ) {
            if (currentPage.value == treePage) {

            }else {
                val newPage = currentPage.value + size1
                if (newPage < endPage) {
                    currentPage.value = newPage
                }
            }
        }
    }
}


