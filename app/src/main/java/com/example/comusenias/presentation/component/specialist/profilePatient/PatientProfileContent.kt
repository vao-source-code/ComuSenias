package com.example.comusenias.presentation.component.specialist.profilePatient

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.comusenias.R
import com.example.comusenias.domain.models.users.ChildrenModel
import com.example.comusenias.presentation.screen.specialist.DataClassUtil
import com.example.comusenias.presentation.ui.theme.SELECTED_IMAGE
import com.example.comusenias.presentation.ui.theme.SIZE100
import com.example.comusenias.presentation.ui.theme.SIZE20
import com.example.comusenias.presentation.ui.theme.SIZE36

@Composable
fun PatientProfileContent(navController: NavController) {
    val patient = DataClassUtil.createSpecialistModelExample().childrenInCharge?.get(0)!!
    val observations = patient.observation!!

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ContentTopProfile(patient = patient)
        TabsPage(
            tabContent = listOf(
                {
                },
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
    patient: ChildrenModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = SIZE36.dp, start = SIZE20.dp, end = SIZE20.dp, bottom = SIZE20.dp),
        horizontalArrangement = Arrangement.spacedBy(SIZE20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ImageProfile(userImage = patient.image)
        ChildData(patient = patient)
    }
}

@Composable
fun ImageProfile(userImage: String?) {
    AsyncImage(
        modifier = Modifier
            .size(SIZE100.dp)
            .clip(CircleShape),
        model = userImage,
        contentScale = ContentScale.Crop,
        contentDescription = SELECTED_IMAGE,
        error = painterResource(id = R.drawable.profile_avatar)
    )
}
