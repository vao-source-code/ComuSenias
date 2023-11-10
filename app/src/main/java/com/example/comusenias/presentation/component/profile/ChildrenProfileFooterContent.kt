package com.example.comusenias.presentation.component.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.component.defaults.app.ButtonApp
import com.example.comusenias.presentation.ui.theme.LOGOUT
import com.example.comusenias.presentation.ui.theme.SIZE20
import com.example.comusenias.presentation.ui.theme.UPDATE_DATA

@Composable
fun ProfileFooterContent(onClickChangeProfile: () -> Unit?, onclickLogout: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(SIZE20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ButtonApp(
            titleButton = UPDATE_DATA,
            onClickButton = {
                onClickChangeProfile()
            }
        )
        Spacer(modifier = Modifier.height(10.dp))

        ButtonApp(
            titleButton = LOGOUT,
            onClickButton = {
                onclickLogout()
            }
        )

    }
}