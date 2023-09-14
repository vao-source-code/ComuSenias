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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.comusenias.presentation.ui.theme.ComuSeniasTheme
import com.example.comusenias.presentation.ui.theme.arrowBack
import com.example.comusenias.presentation.ui.theme.backgroundColorApp
import com.example.comusenias.presentation.ui.theme.primaryColorApp
import com.example.comusenias.presentation.ui.theme.size19
import com.example.comusenias.presentation.ui.theme.title

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopBar(
    title: String,
    upAvailable: Boolean = false,
    navHostController: NavHostController? = null
) {
    TopAppBar(
        title = {
            Text(text = title, fontSize = size19.sp, color = backgroundColorApp)
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            titleContentColor = Color.White,
            containerColor = primaryColorApp
        ),
        navigationIcon = {
            if (upAvailable) {
                IconButton(onClick = { navHostController?.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = arrowBack,
                        tint = backgroundColorApp
                    )
                }
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun DefaultTopBarPreview() {
    ComuSeniasTheme {
        DefaultTopBar(title = title, upAvailable = true)
    }
}