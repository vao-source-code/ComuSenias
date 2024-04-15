package com.ars.comusenias.presentation.component.register

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
import com.ars.comusenias.presentation.component.defaults.app.CheckBoxApp
import com.ars.comusenias.presentation.ui.theme.ESPECIALIST
import com.ars.comusenias.presentation.ui.theme.SIZE10
import com.ars.comusenias.presentation.ui.theme.SIZE14
import com.ars.comusenias.presentation.ui.theme.SIZE9
import com.ars.comusenias.presentation.ui.theme.blackColorApp

@Composable
fun SpecialistCheck(onCheckChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = SIZE10.dp),
        horizontalArrangement = Arrangement.spacedBy(SIZE9.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val isChecked = remember { mutableStateOf(false) }

        CheckBoxApp(isChecked = isChecked) { isCheckedValue ->
            onCheckChange(isCheckedValue)
        }
        Text(
            text = ESPECIALIST,
            fontSize = SIZE14.sp,
            fontWeight = FontWeight.Medium,
            color = blackColorApp
        )
    }
}