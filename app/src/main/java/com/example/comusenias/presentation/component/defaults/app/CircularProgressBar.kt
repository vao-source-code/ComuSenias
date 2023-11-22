package com.example.comusenias.presentation.component.defaults.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.ui.theme.SIZE16
import com.example.comusenias.presentation.ui.theme.SIZE3
import com.example.comusenias.presentation.ui.theme.SIZE50
import com.example.comusenias.presentation.ui.theme.primaryColorApp

@Composable
fun CircularProgressBar() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(SIZE50.dp)
                .padding(SIZE16.dp)
                .testTag("CircularProgressIndicator")
                .semantics { contentDescription = "CircularProgressBar" },
            color = primaryColorApp,
            strokeWidth = SIZE3.dp
        )
    }
}