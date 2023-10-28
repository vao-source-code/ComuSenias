package com.example.comusenias.presentation.component.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
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
import com.example.comusenias.presentation.ui.theme.EMPTY_STRING
import com.example.comusenias.presentation.ui.theme.IMAGE_AVATAR
import com.example.comusenias.presentation.ui.theme.PROFILE_BIRTHDATE
import com.example.comusenias.presentation.ui.theme.PROFILE_EMAIL
import com.example.comusenias.presentation.ui.theme.PROFILE_NAME_SURNAME
import com.example.comusenias.presentation.ui.theme.SELECTED_IMAGE
import com.example.comusenias.presentation.ui.theme.SIZE140
import com.example.comusenias.presentation.ui.theme.SIZE55
import com.example.comusenias.presentation.ui.theme.size20
import com.example.comusenias.presentation.view_model.ProfileViewModel

@Composable
fun ProfileContent(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    Box(modifier = Modifier.padding(size20.dp)) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(SIZE55.dp))
            Box(
                modifier = Modifier
                    .size(SIZE140.dp)
                    .clip(CircleShape),
            ) {

                if (viewModel.userData.image != EMPTY_STRING) {
                    AsyncImage(
                        modifier = Modifier
                            .size(SIZE140.dp), contentScale = ContentScale.Crop,
                        model = viewModel.userData.image, contentDescription = SELECTED_IMAGE
                    )
                } else {
                    Image(
                        modifier = Modifier.size(SIZE140.dp),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = R.drawable.profile_avatar),
                        contentDescription = IMAGE_AVATAR,
                    )
                }
            }
            Spacer(modifier = Modifier.height(SIZE55.dp))

            Spacer(modifier = Modifier.height(size20.dp))
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
            TextFieldApp(
                icon = Icons.Default.Email,
                value = viewModel.userData.name,
                onValueChange = {},
                validateField = {},
                label = PROFILE_NAME_SURNAME,
                keyboardType = KeyboardType.Text,
                hideText = false,
                readOnly = true
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextFieldApp(
                icon = Icons.Default.Email,
                value = viewModel.userData.date,
                onValueChange = {},
                validateField = {},
                label = PROFILE_BIRTHDATE,
                keyboardType = KeyboardType.Text,
                hideText = false,
                readOnly = true
            )
        }
    }
}

