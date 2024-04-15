package com.ars.comusenias.presentation.component.defaults.app

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.ars.comusenias.constants.TestTag
import com.ars.comusenias.presentation.ui.theme.iconColorTextField
import com.ars.comusenias.presentation.ui.theme.primaryColorApp

@Composable
fun CheckBoxApp(
    isChecked: MutableState<Boolean>,
    onCheckChange: (Boolean) -> Unit
) {
    Checkbox(
        modifier = Modifier
            .testTag(TestTag.TAG_CHECKBOX_APP)
            .height(12.dp)
            .width(12.dp),
        checked = isChecked.value,
        onCheckedChange = {
            isChecked.value = it
            onCheckChange(it)
        },
        enabled = true,
        colors = CheckboxDefaults.colors(
            checkedColor = primaryColorApp,
            uncheckedColor = iconColorTextField
        )
    )
}