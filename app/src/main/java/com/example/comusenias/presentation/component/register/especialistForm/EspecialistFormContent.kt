package com.example.comusenias.presentation.component.register.especialistForm

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.component.defaults.app.ButtonApp
import com.example.comusenias.presentation.component.defaults.app.TextFieldApp
import com.example.comusenias.presentation.component.register.TermsAndConditions
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.CONTINUE
import com.example.comusenias.presentation.ui.theme.MEDICAL_TITLE
import com.example.comusenias.presentation.ui.theme.NAME
import com.example.comusenias.presentation.ui.theme.NUMBER_PHONE
import com.example.comusenias.presentation.ui.theme.PROFESSIONAL_REGISTRATION
import com.example.comusenias.presentation.ui.theme.SIZE160
import com.example.comusenias.presentation.ui.theme.SIZE2
import com.example.comusenias.presentation.ui.theme.SIZE20
import com.example.comusenias.presentation.ui.theme.SIZE64
import com.example.comusenias.presentation.ui.theme.SPECIALTY

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EspecialistFormContent(
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = SIZE20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(SIZE2.dp)
        ) {
            TextFieldApp(
                value = "",
                onValueChange = {},
                label = NAME,
                icon = Icons.Default.Person
            )
            TextFieldApp(
                value = "",
                onValueChange = {},
                label = NUMBER_PHONE,
                icon = Icons.Default.Phone
            )
           /* TextFieldDate(
                label = BIRTHDAY
            ) { birthday ->
                // Devuelve en formato string la fecha de nacimiento
            }*/
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(SIZE64.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextFieldApp(
                    modifier = Modifier.width(SIZE160.dp),
                    value = "",
                    onValueChange = {},
                    label = PROFESSIONAL_REGISTRATION,
                    icon = null
                )
                /*TextFieldDate(
                    modifier = Modifier.width(SIZE160.dp),
                    label = EXPIRY_OF_PROFESSIONAL_REGISTRATION,
                ) { expiry ->
                    //Devuelve en formato string la fecha de vencimiento
                }*/
            }
            TextFieldApp(
                value = "",
                onValueChange = {},
                label = MEDICAL_TITLE,
                icon = null
            )
            TextFieldApp(
                value = "",
                onValueChange = {},
                label = SPECIALTY,
                icon = null
            )
            TermsAndConditions { isCheck ->
                // Devuelva un boolean si es check
            }
        }
        ButtonApp(
            titleButton = CONTINUE,
            enabledButton = true,
            onClickButton = { navController.navigate(route = AppScreen.SpecialistScreen.route) }
        )
    }
}