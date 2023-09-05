package com.example.comusenias.presentation.component.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.comusenias.R
import com.example.comusenias.presentation.component.defaults.ButtonDefault
import com.example.comusenias.presentation.component.defaults.TextFieldDefault
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.ComuSeniasTheme
import com.example.comusenias.presentation.view_model.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(
    navController: NavHostController? = null,
    modifier: Modifier? = null,
    viewModel: ProfileViewModel? = null
) {


    Box(modifier = modifier!!.padding(20.dp)) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(55.dp))
            Image(
                modifier = Modifier
                    .size(130.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = ""
            )




            Spacer(modifier = Modifier.height(55.dp))
            TextFieldDefault(
                modifier = Modifier
                    .fillMaxWidth(),
                label = "Nombre del usuario",
                value = "Test name",
                onValueChange = {},
                validateField = {},
                icon = Icons.Default.Edit,
                keyboardType = KeyboardType.Text,
                hideText = false,
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextFieldDefault(
                modifier = Modifier
                    .fillMaxWidth(),
                label = "Correo electrónico",
                value = "Test@correo.com",
                onValueChange = {},
                validateField = {},
                icon = Icons.Default.Edit,
                keyboardType = KeyboardType.Text,
                hideText = false,
            )
            Spacer(modifier = Modifier.height(10.dp))

        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewProfileContent() {

    ComuSeniasTheme() {
        ProfileContent(

        )
    }


}