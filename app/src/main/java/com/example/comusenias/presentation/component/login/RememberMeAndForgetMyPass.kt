package com.example.comusenias.presentation.component.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import com.example.comusenias.presentation.ui.theme.blackColorApp
import com.example.comusenias.presentation.ui.theme.iconColorTextField
import com.example.comusenias.presentation.ui.theme.primaryColorApp

@Preview(showBackground = true)
@Composable
fun RememberMeAndForgetMyPass() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
        ) {
        LabelledCheckbox()
        ForgetMyPass()
    }
}

@Composable
fun ForgetMyPass(onClickText: () -> Unit = {}){
    Text(
        modifier = Modifier.clickable { onClickText() },
        text = "Olvide mi contraseña",
        color = primaryColorApp,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun LabelledCheckbox() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val isChecked = remember { mutableStateOf(false) }

        Checkbox(
            modifier = Modifier
                .height(12.dp)
                .width(12.dp),
            checked = isChecked.value,
            onCheckedChange = { isChecked.value = it },
            enabled = true,
            colors = CheckboxDefaults.colors(
                checkedColor = primaryColorApp,
                uncheckedColor = iconColorTextField
            )
        )
        Text(
            text = "Recuerdame",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = blackColorApp
        )
    }
}