package com.example.comusenias.presentation.component.specialist.observation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction.Companion.Done
import androidx.compose.ui.unit.dp
import com.example.comusenias.presentation.ui.theme.SIZE160
import com.example.comusenias.presentation.ui.theme.SIZE2
import com.example.comusenias.presentation.ui.theme.SIZE8
import com.example.comusenias.presentation.ui.theme.line_divisor
import com.example.comusenias.presentation.ui.theme.size08

@Composable
fun CustomMultilineHintTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    maxLines: Int = SIZE8
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(SIZE160.dp)
            .border(
                width = SIZE2.dp,
                color = line_divisor,
                shape = RoundedCornerShape(size08.dp)
            )
            .background(Color.White, RoundedCornerShape(SIZE8.dp)),
        shape = RoundedCornerShape(SIZE8.dp),
        value = value,
        onValueChange = onValueChanged,
        keyboardOptions = KeyboardOptions(imeAction = Done),
        maxLines = maxLines,
        minLines = maxLines,
        singleLine = false,
        textStyle = textStyle,
    )
}