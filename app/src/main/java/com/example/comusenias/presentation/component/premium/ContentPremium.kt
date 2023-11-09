package com.example.comusenias.presentation.component.premium

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.comusenias.presentation.ui.theme.SIZE20
import com.example.comusenias.presentation.ui.theme.SIZE30

@Composable
fun ContentPremium(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(SIZE20.dp),
        verticalArrangement = spacedBy(SIZE30.dp)
    ) {
        HeaderPremium()
        ContentBenefitsPremium()
        FooterPremium()
    }
}