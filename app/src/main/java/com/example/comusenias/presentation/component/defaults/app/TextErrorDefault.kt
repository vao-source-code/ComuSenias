package com.example.comusenias.presentation.component.defaults.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.sp
import com.example.comusenias.constants.TestTag
import com.example.comusenias.presentation.ui.theme.size11

@Composable
fun TextErrorDefault(errorMsg: String) {
    Text(
        modifier = Modifier.testTag(TestTag.TAG_MESSAGE_ERROR),
        text = errorMsg,
        fontSize = size11.sp,
        color = MaterialTheme.colorScheme.error
    )
}