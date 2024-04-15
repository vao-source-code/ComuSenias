package com.ars.comusenias.presentation.screen.camera

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ars.comusenias.presentation.component.defaults.ButtonDefault
import com.ars.comusenias.presentation.navigation.AppScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestCamera(navController: NavController) {
    Column (Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        ButtonDefault(text = "Record Camera Screen", onClick = {
            navController.navigate(AppScreen.PermissioRecordCameraScreen.route)
        }, icon = null)


    }
}