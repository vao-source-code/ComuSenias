package com.example.comusenias.presentation.screen.camera

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.comusenias.R
import com.example.comusenias.presentation.component.defaults.ButtonDefault
import com.example.comusenias.presentation.navigation.AppScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestCamera(navController: NavController) {
    Column (Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        ButtonDefault(text = "Camara", onClick = {
            navController.navigate(AppScreen.CameraScreenPermission.route)
        }, icon = null)
    }
}




