package com.example.comusenias.presentation.component.login

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.constants.TestTag
import com.example.comusenias.presentation.component.defaults.app.CheckBoxApp
import com.example.comusenias.presentation.ui.theme.blackColorApp
import com.example.comusenias.presentation.ui.theme.forgotPassword
import com.example.comusenias.presentation.ui.theme.primaryColorApp
import com.example.comusenias.presentation.ui.theme.rememberMe
import com.example.comusenias.presentation.ui.theme.size10
import com.example.comusenias.presentation.ui.theme.size14

@Preview(showBackground = true)
@Composable
fun RememberMeAndForgetMyPass() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = size10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        LabelledCheckbox()
        ForgetMyPass { }
    }
}
@Composable
fun ForgetMyPass(onClickText: () -> Unit = {}) {
    Text(
        modifier = Modifier
            .testTag(TestTag.TAG_FORGET_MY_PASS)
            .clickable { onClickText() },
        text = forgotPassword,
        color = primaryColorApp,
        fontSize = size14.sp,
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

        CheckBoxApp(isChecked = isChecked)
        Text(
            text = rememberMe,
            fontSize = size14.sp,
            fontWeight = FontWeight.Medium,
            color = blackColorApp
        )
    }
}

