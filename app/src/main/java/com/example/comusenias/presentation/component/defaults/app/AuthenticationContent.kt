package com.example.comusenias.presentation.component.defaults.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.ui.theme.SIZE20
import com.example.comusenias.presentation.ui.theme.SIZE30
import com.example.comusenias.presentation.ui.theme.SIZE90

@Composable
fun AuthenticationContent(
    content: @Composable () -> Unit = {},
    footer: @Composable () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = SIZE30.dp, end = SIZE30.dp, top = SIZE90.dp, bottom = SIZE20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        AuthenticationHeaderContent()
        content()
        footer()
    }
}