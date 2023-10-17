package com.example.comusenias.presentation.component.register.especialistForm

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.presentation.component.defaults.app.CheckBoxApp
import com.example.comusenias.presentation.ui.theme.ACEPT_TERMS
import com.example.comusenias.presentation.ui.theme.blackColorApp
import com.example.comusenias.presentation.ui.theme.size10
import com.example.comusenias.presentation.ui.theme.size14

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AcceptTerm() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = size10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(size10.dp)
    ) {
        val isChecked = remember { mutableStateOf(false) }

        CheckBoxApp(isChecked = isChecked)
        Text(
            modifier = Modifier.padding(top = size14.dp),
            text = ACEPT_TERMS,
            fontSize = size14.sp,
            fontWeight = FontWeight.Medium,
            color = blackColorApp
        )
    }
}