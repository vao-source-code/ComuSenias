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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.comusenias.R
import com.example.comusenias.presentation.component.defaults.app.TextFieldApp
import com.example.comusenias.presentation.ui.theme.PROFILE_EMAIL
import com.example.comusenias.presentation.ui.theme.PROFILE_USER
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

                if (viewModel.userData.image != "") {
                    AsyncImage(
                        modifier = Modifier
                            .size(140.dp), contentScale = ContentScale.Crop,
                        model = viewModel.userData.image, contentDescription = "Seleted Image"
                    )
                }else{
                    Image(
                        modifier = Modifier.size(140.dp),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = R.drawable.profile_avatar),
                        contentDescription = "image avatar",

                        )
                }

            }



            Spacer(modifier = Modifier.height(55.dp))
            TextFieldApp(
                icon = Icons.Default.Person,
                value = viewModel.userData.userName,
                onValueChange = {},
                validateField = {},
                label = PROFILE_USER,
                keyboardType = KeyboardType.Text,
                hideText = false,
                readOnly = true,
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextFieldApp(
                icon = Icons.Default.Email,
                value = viewModel.userData.email,
                onValueChange = {},
                validateField = {},
                label = PROFILE_EMAIL,
                keyboardType = KeyboardType.Text,
                hideText = false,
                readOnly = true
            )
            Spacer(modifier = Modifier.height(10.dp))

        }
    }
}

