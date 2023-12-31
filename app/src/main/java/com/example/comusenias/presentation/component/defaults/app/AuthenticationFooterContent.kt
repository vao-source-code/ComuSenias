package com.example.comusenias.presentation.component.defaults.app

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.constants.TestTag
import com.example.comusenias.presentation.ui.theme.SIZE14
import com.example.comusenias.presentation.ui.theme.SIZE5
import com.example.comusenias.presentation.ui.theme.blackColorApp
import com.example.comusenias.presentation.ui.theme.primaryColorApp

@Composable
fun AuthenticationFooterContent(
    textOne: String,
    textTwo: String,
    onClickText: () -> Unit = {},
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = textOne,
            fontSize = SIZE14.sp,
            fontWeight = FontWeight.Medium,
            color = blackColorApp
        )
        Spacer(modifier = Modifier.width(SIZE5.dp))
        Text(
            modifier = Modifier
                .testTag(TestTag.TAG_TEXT_CLICK_FOOTER)
                .clickable { onClickText() },
            text = textTwo,
            color = primaryColorApp,
            fontSize = SIZE14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}