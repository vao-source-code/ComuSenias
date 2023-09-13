package com.example.comusenias.presentation.component.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.comusenias.R
import com.example.comusenias.presentation.component.defaults.app.TextFieldApp
import com.example.comusenias.presentation.ui.theme.ComuSeniasTheme
import com.example.comusenias.presentation.view_model.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(
    viewModel: ProfileViewModel = hiltViewModel()
) {


    Box(modifier = Modifier.padding(20.dp)) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(55.dp))
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape),
            ) {
                Image(
                    modifier= Modifier.size(140.dp),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = R.drawable.profile_avatar),
                    contentDescription = "image avatar",

                )

            }



            Spacer(modifier = Modifier.height(55.dp))
            TextFieldApp(

                label = "Nombre del usuario",
                value = viewModel.userData.userName,
                onValueChange = {},
                validateField = {},
                icon = Icons.Default.Edit,
                keyboardType = KeyboardType.Text,
                hideText = false,
                readOnly = true,
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextFieldApp(

                label = "Correo electrónico",
                value = viewModel.userData.email,
                onValueChange = {},
                validateField = {},
                icon = Icons.Default.Edit,
                keyboardType = KeyboardType.Text,
                hideText = false,
                readOnly = true
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