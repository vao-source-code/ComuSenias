package com.example.comusenias.presentation.component.defaults

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.ui.theme.SIZE16
import com.example.comusenias.presentation.ui.theme.arrowBack
import com.example.comusenias.presentation.ui.theme.blackColorApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopBar(
    title: String,
    upAvailable: Boolean = false,
    navHostController: NavHostController? = null
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = SIZE16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = blackColorApp
                    )
                )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            titleContentColor = blackColorApp,
            containerColor = Color.White
        ),
        navigationIcon = {
            if (upAvailable) {
                IconButton(onClick = { navHostController?.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = arrowBack,
                        tint = blackColorApp
                    )
                }
            }
        }
    )
}