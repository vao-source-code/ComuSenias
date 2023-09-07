package com.example.comusenias.presentation.component.defaults

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.ui.theme.iconColorTextField
import com.example.comusenias.presentation.ui.theme.primaryColorApp

@Composable
fun CheckBoxApp(isChecked: MutableState<Boolean>) {
    Checkbox(
        modifier = Modifier
            .testTag("check login")
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
}