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
import com.example.comusenias.presentation.ui.theme.SIZE70
import com.example.comusenias.presentation.ui.theme.WHAT_IS_YOUR_NAME
import com.example.comusenias.presentation.ui.theme.primaryColorApp
import com.example.comusenias.presentation.ui.theme.size10
import com.example.comusenias.presentation.ui.theme.size20

@Composable
fun ChildFormContent(){
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(size20.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = WHAT_IS_YOUR_NAME,
                color = primaryColorApp,
                fontSize = size20.sp,
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
            verticalArrangement = Arrangement.spacedBy(size10.dp)
        ) {
            Text(
                text = HOW_OLD_ARE_YOU,
                color = primaryColorApp,
                fontSize = size20.sp,
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