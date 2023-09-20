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
import com.example.comusenias.presentation.ui.theme.primaryColorApp
import com.example.comusenias.presentation.ui.theme.size10
import com.example.comusenias.presentation.ui.theme.size3

//TODO debo una cervecita por esto
@Composable
fun SpecialistDetailsScreen(modifier: Modifier, navController: NavHostController) {


    Column(modifier = Modifier.padding(20.dp)) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomEnd = 40.dp, bottomStart = 40.dp))
                .background(color = primaryColorApp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Nombre niño",
                modifier = Modifier.padding(10.dp),
                color = Color.White,
                fontSize = 30.sp
            )
            Box(
                modifier = Modifier
                    .size(140.dp)
                        .clip(CircleShape),
            ) {

                Image(
                    modifier = Modifier.size(140.dp),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = R.drawable.profile_avatar),
                    contentDescription = "image avatar",

                    )

            }

        }

        Spacer(modifier = Modifier.size(10.dp))
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .size(3.dp),
            color = Color.Gray
        )

        textDetails(icono = R.drawable.icon_calendar, text = "6 años")
        textDetails(icono = R.drawable.icon_call, text = "1122334545")
        textDetails(icono = R.drawable.icon_email, text = "test@gmail.com")
        textDetails(icono = R.drawable.icon_map, text = "Ubicacion")

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .size(3.dp),
            color = Color.Gray
        )
        Spacer(modifier = Modifier.size(10.dp))

        CardWithTwoImages(imageLeft = R.drawable.details, imageRight = R.drawable.eclipse_specialist1, text = "Vocales")
        CardWithTwoImages(imageLeft =  R.drawable.details, imageRight =R.drawable.eclipse_specialist2 , text = "Números")
        CardWithTwoImages(imageLeft =  R.drawable.details, imageRight = R.drawable.eclipse_specialist3, text = "Letras")


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
            .padding(10.dp)      .shadow(
                elevation = size3.dp,
                shape = RoundedCornerShape(size10.dp),
                spotColor = Color(0x21000000),
                ambientColor = Color(0x21000000)
            ).background(Color.White, RoundedCornerShape(size10.dp)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),

            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = imageLeft),
                modifier = Modifier.size(70.dp).padding(5.dp),
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
                modifier = Modifier.size(70.dp).padding(5.dp),
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

