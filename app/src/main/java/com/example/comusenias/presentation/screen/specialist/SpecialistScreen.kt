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
import com.example.comusenias.domain.models.users.ChildrenModel
import com.example.comusenias.presentation.component.specialist.CardHomeSpecialist
import com.example.comusenias.presentation.component.specialist.CardProfileUser
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.SIZE10
import com.example.comusenias.presentation.ui.theme.SIZE26
import com.example.comusenias.presentation.ui.theme.SIZE30
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
                        elevation = SIZE10.dp,
                        shape = RoundedCornerShape(bottomEnd = SIZE30.dp, bottomStart = SIZE30.dp)
                    )
                    .background(primaryColorApp)
            ) {

                CardHomeSpecialist(
                    image = R.drawable.diagnostic_category,
                    title = "Juan Perez",
                    subtitle = "Psicologo Infantil",
                    onClickCard = { navController.navigate(route = AppScreen.ChildrenProfileScreen.route) }
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
            Column(modifier = Modifier.padding(SIZE10.dp)) {
                CardExampleDos(
                    user = chapter,
                    navController = navController,
                    {}) //TODO deberia ir los datos del paciente en el {}
            }

        }
    }
}


@Composable
fun CardExampleDos(
    user: ChildrenModel,
    navController: NavHostController,
    onItemSelected: (ChildrenModel) -> Unit
) {
    CardProfileUser(image = R.drawable.profile_avatar,
        title = user.name,
        onClickCard = { navController.navigate(route = AppScreen.SpecialistDetailsScreen.route) })

}

fun getChapterItem(): List<ChildrenModel> {
    return listOf(
        ChildrenModel(id = "1", name = "Alberto Wirstes", image = ""),
        ChildrenModel(id = "2", name = "Sabrina Gomez", image = ""),
        ChildrenModel(id = "3", name = "Norma Gonzales", image = ""),
        ChildrenModel(id = "4", name = "Nicolas Orue", image = ""),
        ChildrenModel(id = "5", name = "Esther Segovia", image = ""),
        ChildrenModel(id = "6", name = "Juan Carnizo", image = ""),
        ChildrenModel(id = "7", name = "Victor Alvarez", image = ""),
        ChildrenModel(id = "8", name = "Karina Gomez", image = ""),
        ChildrenModel(id = "9", name = "Daniel Arribas", image = ""),

        )
}

@Preview
@Composable
fun SpecialistHomeExamplePreview() {
    SpecialistScreen(navController = rememberNavController(), modifier = Modifier)
}