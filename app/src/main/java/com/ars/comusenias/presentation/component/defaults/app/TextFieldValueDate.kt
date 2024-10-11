package com.ars.comusenias.presentation.component.defaults.app

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.ars.comusenias.R
import com.ars.comusenias.presentation.ui.theme.EMPTY_STRING
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TextFieldValueDate(
    value: String = EMPTY_STRING,
    modifier: Modifier = Modifier,
    label: String,
    onValueChange: (String) -> Unit = {},
    validateField: () -> Unit = {},
) {
    val context = LocalContext.current
    val locale = Locale("es", "ES")
    Locale.setDefault(locale)
    var valueDate by remember { mutableStateOf("") }
    valueDate = value
    val pattern = "yyyy-MM-dd"
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val date =
        if (valueDate.isNotBlank()) LocalDate.parse(valueDate, formatter) else LocalDate.now()
    val dialog =
        DatePickerDialog(
            context,
            R.style.ThemeOverlay_MyApp_Dialog,
            { _, year, month, dayOfMonth ->
                valueDate = LocalDate.of(year, month + 1, dayOfMonth).toString()
                onValueChange(valueDate)
                validateField()
            },
            date.year,
            date.monthValue - 1,
            date.dayOfMonth,
        )

    InputTextField(
        modifier = modifier,
        value = valueDate,
        onValueChange = onValueChange,
        validateField = validateField,
        label = label,
        clickIcon = { dialog.show() },
        icon = Icons.Default.DateRange,
    )
}