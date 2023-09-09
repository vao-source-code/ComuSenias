package com.example.comusenias.presentation.component.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.presentation.component.defaults.app.CheckBoxApp
import com.example.comusenias.presentation.ui.theme.blackColorApp
import com.example.comusenias.presentation.ui.theme.primaryColorApp

@Composable
fun TermsAndConditionsContent(onClickTerms: () -> Unit = {},onClickConditions: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        verticalAlignment = Alignment.CenterVertically
        ){
        val isChecked = remember { mutableStateOf(false) }

        CheckBoxApp(isChecked = isChecked )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = "Acepto",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = blackColorApp
        )
        Text(
                modifier = Modifier
                    .clickable { onClickTerms() },
                text = "Términos",
                color = primaryColorApp,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
        )
        Text(
            text = "y",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = blackColorApp
        )
        Text(
            modifier = Modifier
                .clickable { onClickConditions() },
            text = "Condiciones",
            color = primaryColorApp,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}