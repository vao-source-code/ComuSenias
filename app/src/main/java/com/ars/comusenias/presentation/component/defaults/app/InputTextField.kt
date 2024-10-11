package com.ars.comusenias.presentation.component.defaults.app

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ars.comusenias.constants.TestTag
import com.ars.comusenias.presentation.ui.theme.backgroundColorTextField
import com.ars.comusenias.presentation.ui.theme.borderColorTextFieldFocused
import com.ars.comusenias.presentation.ui.theme.borderColorTextFieldUnfocused
import com.ars.comusenias.presentation.ui.theme.iconColorTextField
import com.ars.comusenias.presentation.ui.theme.placeholderTextColor


@SuppressLint("SuspiciousIndentation")
@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (value: String) -> Unit,
    validateField: () -> Unit = {},
    clickIcon: () -> Unit = {},
    label: String,
    icon: ImageVector?,
    keyboardType: KeyboardType = KeyboardType.Text,
    hideText: Boolean = false,
    errorMsg: String = "",
    readOnly: Boolean = false,
) {
    val maxChar = 32

    Column {
        OutlinedTextField(
            modifier = modifier
                .testTag(TestTag.TAG_TEXT_FIELD_APP)
                .fillMaxWidth()
                .background(backgroundColorTextField, shape = RoundedCornerShape(20.dp)),
            value = value,
            onValueChange = {
                if (it.length <= maxChar) {
                    onValueChange(it)
                    validateField()
                }
            },
            shape = RoundedCornerShape(20.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = borderColorTextFieldFocused,
                unfocusedBorderColor = borderColorTextFieldUnfocused,
            ),
            singleLine = true,
            placeholder = {
                Text(
                    text = label,
                    color = placeholderTextColor,
                    fontSize = 14.sp
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = ImeAction.Next
            ),
            trailingIcon = {
                if (icon != null) {
                    Icon(
                        modifier = Modifier.clickable { clickIcon() },
                        imageVector = icon,
                        contentDescription = label,
                        tint = iconColorTextField,
                    )
                }
            },
            readOnly = readOnly,
            visualTransformation = if (hideText) PasswordVisualTransformation() else VisualTransformation.None,
        )

        TextErrorDefault(errorMsg = errorMsg)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTextFieldApp() {
    // Estado simulado para el preview
    var value = "Sample Text"
    var errorMsg = ""

    InputTextField(
        value = value,
        onValueChange = { value = it }, // Actualiza el valor
        label = "Enter text",
        icon = Icons.Default.Search, // Reemplaza con el icono que desees
        keyboardType = KeyboardType.Text,
        hideText = false,
        errorMsg = errorMsg,
        readOnly = false,
    )
}