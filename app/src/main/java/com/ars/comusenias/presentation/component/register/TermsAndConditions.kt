package com.ars.comusenias.presentation.component.register

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
import com.ars.comusenias.presentation.component.defaults.app.CheckBoxApp
import com.ars.comusenias.presentation.ui.theme.ACCEPT
import com.ars.comusenias.presentation.ui.theme.CONDITIONS
import com.ars.comusenias.presentation.ui.theme.SIZE10
import com.ars.comusenias.presentation.ui.theme.SIZE14
import com.ars.comusenias.presentation.ui.theme.SIZE3
import com.ars.comusenias.presentation.ui.theme.SIZE6
import com.ars.comusenias.presentation.ui.theme.TERMS
import com.ars.comusenias.presentation.ui.theme.Y
import com.ars.comusenias.presentation.ui.theme.blackColorApp
import com.ars.comusenias.presentation.ui.theme.primaryColorApp

@Composable
fun TermsAndConditions(
    onClickTerms: () -> Unit = {},
    onClickConditions: () -> Unit = {},
    onCheckChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = SIZE10.dp),
        horizontalArrangement = Arrangement.spacedBy(SIZE3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val isChecked = remember { mutableStateOf(false) }

        CheckBoxApp(isChecked = isChecked) { isCheckedValue ->
            onCheckChange(isCheckedValue)
        }
        Spacer(modifier = Modifier.width(SIZE6.dp))
        Text(
            text = ACCEPT,
            fontSize = SIZE14.sp,
            fontWeight = FontWeight.Medium,
            color = blackColorApp
        )
        Text(
            modifier = Modifier
                .clickable { onClickTerms() },
            text = TERMS,
            color = primaryColorApp,
            fontSize = SIZE14.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = Y,
            fontSize = SIZE14.sp,
            fontWeight = FontWeight.Medium,
            color = blackColorApp
        )
        Text(
            modifier = Modifier
                .clickable { onClickConditions() },
            text = CONDITIONS,
            color = primaryColorApp,
            fontSize = SIZE14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}