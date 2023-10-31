package com.example.comusenias.presentation.component.specialist.profilePatient

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.comusenias.R
import com.example.comusenias.presentation.screen.specialist.DataClassUtil
import com.example.comusenias.presentation.ui.theme.IMAGE_EDIT_BUTTON
import com.example.comusenias.presentation.ui.theme.SELECTED_IMAGE
import com.example.comusenias.presentation.ui.theme.SIZE140
import com.example.comusenias.presentation.ui.theme.SIZE16
import com.example.comusenias.presentation.ui.theme.SIZE20
import com.example.comusenias.presentation.ui.theme.SIZE24
import com.example.comusenias.presentation.ui.theme.SIZE26
import com.example.comusenias.presentation.ui.theme.SIZE36
import com.example.comusenias.presentation.ui.theme.blackColorApp

@Composable
fun PatientProfileContent(navController: NavController) {
    val patient = DataClassUtil.createSpecialistModelExample().childrenInCharge?.get(0)!!
    val observations = patient.observation!!

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ContentTopProfile(
            userImage = "patient.userModel.image",
            name = "patient.userModel.userName"
        )
        Spacer(modifier = Modifier.height(SIZE36.dp))
        TabsPage(
            tabContent = listOf(
                { ChildData(patient = patient) },
                { },
                {
                    ObservationsScreen(
                        observations = observations,
                        navController = navController
                    )
                }
            )
        )
    }
}

@Composable
fun ContentTopProfile(
    userImage: String?,
    name: String ,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = SIZE26.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(SIZE16.dp)
    ) {
        ImageProfile(userImage = userImage)
        UserName(name = name)
    }
}

@Composable
fun ImageProfile(userImage: String?, editable: Boolean = false) {
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
            model = userImage,
            contentScale = ContentScale.Crop,
            contentDescription = SELECTED_IMAGE,
            error = painterResource(id = R.drawable.profile_avatar)
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
            fontWeight = FontWeight.Medium,
            color = blackColorApp
        )
    )
}
