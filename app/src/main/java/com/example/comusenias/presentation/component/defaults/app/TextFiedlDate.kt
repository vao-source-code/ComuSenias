package com.example.comusenias.presentation.component.defaults.app

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
import com.example.comusenias.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TextFieldDate(
    modifier: Modifier = Modifier,
    label: String,
    onValueChange: (String) -> Unit = {},
    validateField: () -> Unit = {},
) {
    val context = LocalContext.current
    val locale = Locale("es", "ES")
    Locale.setDefault(locale)
    var value by remember { mutableStateOf("") }
    val pattern = "yyyy-MM-dd"
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val date = if (value.isNotBlank()) LocalDate.parse(value, formatter) else LocalDate.now()
    val dialog =
        DatePickerDialog(
            context,
            R.style.ThemeOverlay_MyApp_Dialog,
            { _, year, month, dayOfMonth ->
                value = LocalDate.of(year, month + 1, dayOfMonth).toString()
                onValueChange(value)
                validateField()
            },
            date.year,
            date.monthValue - 1,
            date.dayOfMonth,
        )

    TextFieldApp(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        validateField = validateField,
        label = label,
        clickIcon = { dialog.show() },
        icon = Icons.Default.DateRange,
    )
}