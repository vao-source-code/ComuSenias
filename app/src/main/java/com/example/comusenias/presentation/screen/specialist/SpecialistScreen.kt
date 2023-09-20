package com.example.comusenias.presentation.screen.specialist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.comusenias.R
import com.example.comusenias.domain.models.User
import com.example.comusenias.presentation.component.specialist.CardHomeSpecialist
import com.example.comusenias.presentation.component.specialist.CardProdileUser
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.SIZE26
import com.example.comusenias.presentation.ui.theme.primaryColorApp

@Composable
fun SpecialistScreen(navController: NavHostController, modifier: Modifier) {
    SpecialistHomeExample(
        navController = navController, modifier = modifier
    )
}


@Composable
fun SpecialistHomeExample(navController: NavHostController, modifier: Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Box(
                Modifier
                    .shadow(
                        elevation = 10.dp,
                        shape = RoundedCornerShape(bottomEnd = 30.dp, bottomStart = 30.dp)
                    )
                    .background(primaryColorApp)
            ) {

                CardHomeSpecialist(
                    image = R.drawable.diagnostic_category,
                    title = "Juan Perez",
                    subtitle = "Psicologo Infantil",
                    onClickCard = { navController.navigate(route = AppScreen.ProfileScreen.route) }
                )


            }

            ProfileView(modifier = modifier, navController = navController)
        }
    }
}


@Composable
fun ProfileView(modifier: Modifier, navController: NavHostController) {

    Text(

        textAlign = TextAlign.Start,
        text = "Pacientes",
        style = TextStyle(fontSize = SIZE26.sp, fontWeight = FontWeight.Bold)
    )

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(1),
    ) {
        items(getChapterItem()) { chapter ->
            Column(modifier = Modifier.padding(10.dp)) {
                CardExampleDos(
                    user = chapter,
                    navController = navController,
                    {}) //TODO deberia ir los datos del paciente en el {}
            }

        }
    }
}


@Composable
fun CardExampleDos(user: User, navController: NavHostController, onItemSelected: (User) -> Unit) {
        CardProdileUser(image = R.drawable.profile_avatar,
            title = user.userName,
            onClickCard = { navController.navigate(route = AppScreen.SpecialistDetailsScreen.route) })

}

fun getChapterItem(): List<User> {
    return listOf(
        User(id = "1", userName = "Alberto Wirstes", image = ""),
        User(id = "2", userName = "Sabrina Gomez", image = ""),
        User(id = "3", userName = "Norma Gonzales", image = ""),
        User(id = "4", userName = "Nicolas Orue", image = ""),
        User(id = "5", userName = "Esther Segovia", image = ""),
        User(id = "6", userName = "Juan Carnizo", image = ""),
        User(id = "7", userName = "Victor Alvarez", image = ""),
        User(id = "8", userName = "Karina Gomez", image = ""),
        User(id = "9", userName = "Daniel Arribas", image = ""),

        )
}

@Preview
@Composable
fun SpecialistHomeExamplePreview() {
    SpecialistScreen(navController = rememberNavController(), modifier = Modifier)
}