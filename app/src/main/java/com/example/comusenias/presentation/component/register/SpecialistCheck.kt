package com.example.comusenias.presentation.component.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.comusenias.presentation.ui.theme.ESPECIALIST
import com.example.comusenias.presentation.ui.theme.blackColorApp
import com.example.comusenias.presentation.ui.theme.size10
import com.example.comusenias.presentation.ui.theme.size14
import com.example.comusenias.presentation.ui.theme.size9

@Composable
fun SpecialistCheck(
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = size10.dp),
        horizontalArrangement = Arrangement.spacedBy(size9.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val isChecked = remember { mutableStateOf(false) }

        CheckBoxApp(isChecked = isChecked)
        Text(
            text = ESPECIALIST,
            fontSize = size14.sp,
            fontWeight = FontWeight.Medium,
            color = blackColorApp
        )
    }
}