package com.example.comusenias.presentation.component.specialist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.comusenias.R
import com.example.comusenias.presentation.ui.theme.IMAGE_AVATAR
import com.example.comusenias.presentation.ui.theme.LETTERS
import com.example.comusenias.presentation.ui.theme.NAME_KID
import com.example.comusenias.presentation.ui.theme.NUMBERS
import com.example.comusenias.presentation.ui.theme.SIZE140
import com.example.comusenias.presentation.ui.theme.VOWELS
import com.example.comusenias.presentation.ui.theme.primaryColorApp
import com.example.comusenias.presentation.ui.theme.size10
import com.example.comusenias.presentation.ui.theme.size20
import com.example.comusenias.presentation.ui.theme.size3
import com.example.comusenias.presentation.ui.theme.SIZE30
import com.example.comusenias.presentation.ui.theme.size40

@Composable
fun SpecialistDetailsScreen(modifier: Modifier, navController: NavHostController) {
    Column(modifier = Modifier.padding(size20.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomEnd = size40.dp, bottomStart = size40.dp))
                .background(color = primaryColorApp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = NAME_KID,
                modifier = Modifier.padding(size10.dp),
                color = Color.White,
                fontSize = SIZE30.sp
            )
            Box(
                modifier = Modifier
                    .size(SIZE140.dp)
                    .clip(CircleShape),
            ) {

                Image(
                    modifier = Modifier.size(SIZE140.dp),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = R.drawable.profile_avatar),
                    contentDescription = IMAGE_AVATAR,
                )
            }
        }

        Spacer(modifier = Modifier.size(size10.dp))
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .size(size3.dp),
            color = Color.Gray
        )

        textDetails(icono = R.drawable.icon_calendar, text = "6 años")
        textDetails(icono = R.drawable.icon_call, text = "1122334545")
        textDetails(icono = R.drawable.icon_email, text = "test@gmail.com")
        textDetails(icono = R.drawable.icon_map, text = "Ubicacion")

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .size(size3.dp),
            color = Color.Gray
        )
        Spacer(modifier = Modifier.size(size10.dp))

        CardWithTwoImages(
            imageLeft = R.drawable.details,
            imageRight = R.drawable.eclipse_specialist1,
            text = VOWELS
        )
        CardWithTwoImages(
            imageLeft = R.drawable.details,
            imageRight = R.drawable.eclipse_specialist2,
            text = NUMBERS
        )
        CardWithTwoImages(
            imageLeft = R.drawable.details,
            imageRight = R.drawable.eclipse_specialist3,
            text = LETTERS
        )


    }
}

@Composable
fun textDetails(icono: Int, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        // Icono.
        Icon(
            painter = painterResource(id = icono),
            contentDescription = "Icono de inicio"
        )

        // Texto.
        Text(
            text = text,
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}


@Composable
fun CardWithTwoImages(
    imageLeft: Int,
    imageRight: Int,
    text: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .shadow(
                elevation = size3.dp,
                shape = RoundedCornerShape(size10.dp),
                spotColor = Color(0x21000000),
                ambientColor = Color(0x21000000)
            )
            .background(Color.White, RoundedCornerShape(size10.dp)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),

            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = imageLeft),
                modifier = Modifier
                    .size(70.dp)
                    .padding(5.dp),
                contentDescription = "image left"
            )

            Text(
                text = text,
                modifier = Modifier
                    .padding(16.dp),
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = imageRight),
                modifier = Modifier
                    .size(70.dp)
                    .padding(5.dp),
                contentDescription = "image right"
            )
        }
    }
}

@Composable
@Preview
fun SpecialistDetailsScreenPreview() {
    SpecialistDetailsScreen(modifier = Modifier, navController = rememberNavController())
}

