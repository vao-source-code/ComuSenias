package com.example.comusenias.presentation.component.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.component.defaults.app.ButtonApp
import com.example.comusenias.presentation.ui.theme.UPDATE_DATA
import com.example.comusenias.presentation.ui.theme.size20

@Composable
fun ChildrenProfileFooterContent(onClickChangeProfile: () -> Unit?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(size20.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        ButtonApp(
            titleButton = UPDATE_DATA,
            onClickButton = {
                onClickChangeProfile()
            }
        )

        ButtonApp(titleButton = UPDATE_DATA,
            onClickButton = {})
    }
}