package com.example.comusenias.presentation.component.defaults.app

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.constants.TestTag
import com.example.comusenias.presentation.ui.theme.backgroundColorTextField
import com.example.comusenias.presentation.ui.theme.iconColorTextField
import com.example.comusenias.presentation.ui.theme.placeholderTextColor
import com.example.comusenias.presentation.ui.theme.size10
import com.example.comusenias.presentation.ui.theme.size14

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldApp(
        value: String,
        onValueChange: (value: String) -> Unit,
        validateField: () -> Unit = {},
        label: String,
        icon: ImageVector,
        keyboardType: KeyboardType = KeyboardType.Text,
        hideText: Boolean = false,
        errorMsg: String = "",
        readOnly: Boolean = false,
    ) {
    val MAX_CHAR = 32

        Column {
            OutlinedTextField(
                modifier = Modifier
                    .testTag(TestTag.TAG_TEXT_FIELD_APP)
                    .fillMaxWidth()
                    .background(backgroundColorTextField, shape = RoundedCornerShape(size10.dp)),
                onValueChange = {
                    if (it.length <= MAX_CHAR) {
                        onValueChange(it)
                        validateField()
                    }
                                },
                value = value,
                shape = RoundedCornerShape(size10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = backgroundColorTextField,
                    unfocusedBorderColor = backgroundColorTextField,
                ),
                singleLine = true,
                placeholder = {
                    Text(
                        text = label,
                        color = placeholderTextColor,
                        fontSize = size14.sp
                    )
                              },
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                trailingIcon = {
                    Icon(
                        modifier = Modifier.clickable {  },
                        imageVector = icon,
                        contentDescription = label,
                        tint = iconColorTextField,
                    )
                },
                readOnly= readOnly,
                visualTransformation = if (hideText) PasswordVisualTransformation() else VisualTransformation.None,
            )
            TextErrorDefault(errorMsg = errorMsg)
        }
}
@Preview(showBackground = true)
@Composable
fun PrevieTextField(){
    TextFieldApp(value = "gmail", onValueChange = {}, label = "Corre electrónico", icon = Icons.Default.Email)
}