package com.example.comusenias.presentation.component.register.choseYourProfile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.comusenias.R
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.CHILD
import com.example.comusenias.presentation.ui.theme.CHOSE_YOU_PROFILE
import com.example.comusenias.presentation.ui.theme.ESPECIALIST
import com.example.comusenias.presentation.ui.theme.primaryColorApp
import com.example.comusenias.presentation.ui.theme.size20
import com.example.comusenias.presentation.ui.theme.SIZE30

@Composable
fun ChoseYourProfileContent(navController : NavHostController){
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SIZE30.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = CHOSE_YOU_PROFILE,
            color = primaryColorApp,
            fontSize = size20.sp,
            fontWeight = FontWeight.Bold
        )
        CardCategoryProfile(
            image = R.drawable.children_category,
            title = CHILD,
            onClickCard = { navController.navigate(route = AppScreen.ChildFormScreen.route) }
        )
        CardCategoryProfile(
            image = R.drawable.diagnostic_category,
            title = ESPECIALIST,
            onClickCard = { navController.navigate(route = AppScreen.EspecialistFormScreen.route) }
        )
    }
}