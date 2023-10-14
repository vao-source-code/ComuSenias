package com.example.comusenias.presentation.component.register.childForm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.presentation.component.defaults.app.TextFieldApp
import com.example.comusenias.presentation.ui.theme.AGE
import com.example.comusenias.presentation.ui.theme.HOW_OLD_ARE_YOU
import com.example.comusenias.presentation.ui.theme.NAME
import com.example.comusenias.presentation.ui.theme.SIZE10
import com.example.comusenias.presentation.ui.theme.SIZE20
import com.example.comusenias.presentation.ui.theme.WHAT_IS_YOUR_NAME
import com.example.comusenias.presentation.ui.theme.primaryColorApp

@Composable
fun ChildFormContent(){
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(SIZE10.dp)
        ) {
            Text(
                text = WHAT_IS_YOUR_NAME,
                color = primaryColorApp,
                fontSize = SIZE20.sp,
                fontWeight = FontWeight.SemiBold
            )
            TextFieldApp(
                value = "",
                onValueChange = {},
                label = NAME,
                icon = null
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(SIZE10.dp)
        ) {
            Text(
                text = HOW_OLD_ARE_YOU,
                color = primaryColorApp,
                fontSize = SIZE20.sp,
                fontWeight = FontWeight.SemiBold
            )
            TextFieldApp(
                value = "",
                onValueChange = {},
                label = AGE,
                icon = null
            )
        }
    }
}