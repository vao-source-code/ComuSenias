package com.example.comusenias.presentation.component.addPatient

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.comusenias.R
import com.example.comusenias.presentation.ui.theme.PROFILE_USER
import com.example.comusenias.presentation.ui.theme.SIZE12
import com.example.comusenias.presentation.ui.theme.SIZE130
import com.example.comusenias.presentation.ui.theme.SIZE16
import com.example.comusenias.presentation.ui.theme.SIZE20

@Composable
fun UserProfileContent(
    image: String?,
    name: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = SIZE16.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(SIZE130.dp)
                .align(Alignment.CenterHorizontally),
            model = image,
            error = painterResource(R.drawable.profile_avatar),
            contentDescription = PROFILE_USER
        )
        Text(
            text = name,
            style = TextStyle(fontSize = SIZE20.sp),
            modifier = Modifier.padding(bottom = SIZE12.dp)
        )
    }
}