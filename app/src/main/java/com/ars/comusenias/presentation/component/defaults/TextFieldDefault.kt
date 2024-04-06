package com.ars.comusenias.presentation.component.defaults

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.ars.comusenias.presentation.component.defaults.app.TextErrorDefault

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldDefault(
    modifier: Modifier,
    value: String,
    onValueChange: (value: String) -> Unit,
    validateField: () -> Unit = {},
    label: String,
    icon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text,
    hideText: Boolean = false,
    errorMsg: String = ""
) {

    Column {
        OutlinedTextField(
            modifier = modifier,
            value = value,
            onValueChange = {
                onValueChange(it)
                validateField() },
            label = { Text(text = label) },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            leadingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = MaterialTheme.colorScheme.tertiary,
                )
            },
            visualTransformation = if (hideText) PasswordVisualTransformation() else VisualTransformation.None,
        )
        TextErrorDefault(errorMsg = errorMsg)
    }

}

@Preview
@Composable
fun TextFieldDefaultPreview() {
    TextFieldDefault(
        modifier = Modifier,
        value = "",
        onValueChange = {},
        validateField = {},
        label = "Label",
        icon = Icons.Default.ArrowForward,
        keyboardType = KeyboardType.Text,
        hideText = false,
        errorMsg = "Error"
    )
}