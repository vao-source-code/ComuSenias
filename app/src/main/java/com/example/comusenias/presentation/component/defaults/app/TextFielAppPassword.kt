package com.example.comusenias.presentation.component.defaults.app

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comusenias.R
import com.example.comusenias.constants.TestTag
import com.example.comusenias.presentation.ui.theme.backgroundColorTextField
import com.example.comusenias.presentation.ui.theme.iconColorTextField
import com.example.comusenias.presentation.ui.theme.placeholderTextColor
import com.example.comusenias.presentation.ui.theme.size10
import com.example.comusenias.presentation.ui.theme.size14
import com.example.comusenias.presentation.ui.theme.size50

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldAppPassword(
    value: String,
    onValueChange: (value: String) -> Unit,
    validateField: () -> Unit = {},
    label: String,
    errorMsg: String = ""
) {
    val isHideText = remember { mutableStateOf(true) }
    val  MAX_CHAR = 32

    Column {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(size50.dp)
                .background(backgroundColorTextField, shape = RoundedCornerShape(10.dp))
                .testTag(TestTag.TAG_TEXT_FIELD_APP_PASS),
            onValueChange = {
                if (it.length <= MAX_CHAR) {
                    onValueChange(it)
                    validateField()
                }},
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                Icon(
                    modifier = Modifier
                        .testTag(TestTag.TAG_ICON_IS_HIDEN + isHideText.value.toString())
                        .clickable { isHideText.value = !isHideText.value },
                    painter = if (isHideText.value) (painterResource(id = R.drawable.visibility_off)) else
                        (painterResource(id = R.drawable.visibility)),
                    contentDescription = label,
                    tint = iconColorTextField,
                )
            },
            visualTransformation = if (isHideText.value) PasswordVisualTransformation() else VisualTransformation.None,
        )
        TextErrorDefault(errorMsg = errorMsg)
    }
}