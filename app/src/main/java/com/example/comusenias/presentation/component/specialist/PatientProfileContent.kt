package com.example.comusenias.presentation.component.specialist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.comusenias.R
import com.example.comusenias.presentation.ui.theme.IMAGE_EDIT_BUTTON
import com.example.comusenias.presentation.ui.theme.SELECTED_IMAGE
import com.example.comusenias.presentation.ui.theme.SIZE140
import com.example.comusenias.presentation.ui.theme.SIZE20
import com.example.comusenias.presentation.ui.theme.SIZE24
import com.example.comusenias.presentation.ui.theme.blackColorApp

@Composable
fun PatientProfileContent() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ContentTopProfile()
        TabsPage()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ContentTopProfile(
    userImage: Any? = "",
    name: String = "Agusto Fernandez",
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageProfile(userImage = userImage)
        UserName(name = name)
    }
}

@Composable
fun ImageProfile(userImage: Any?, editable: Boolean = false) {
    val image = R.drawable.profile_avatar
    Column(
        modifier = Modifier
            .width(SIZE140.dp),
        horizontalAlignment = Alignment.End
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(SIZE140.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            model = userImage,
            contentDescription = SELECTED_IMAGE,
            placeholder = painterResource(id = image),
            error = painterResource(id = image)
        )
        if (editable) {
            Icon(
                modifier = Modifier
                    .size(SIZE24.dp)
                    .offset(y = (-SIZE20).dp),
                imageVector = Icons.Default.Edit,
                contentDescription = IMAGE_EDIT_BUTTON,
                tint = blackColorApp
            )
        }
    }
}

@Composable
fun UserName(name: String) {
    Text(
        text = name,
        style = TextStyle(
            fontSize = SIZE20.sp,
            fontWeight = FontWeight.Normal,
            color = blackColorApp
        )
    )
}
