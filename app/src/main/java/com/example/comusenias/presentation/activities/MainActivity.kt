package com.example.comusenias.presentation.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.comusenias.demo_camera.DemoIACamera
import com.example.comusenias.domain.library.LibraryDebug
import com.example.comusenias.presentation.navigation.AppNavigation
import com.example.comusenias.presentation.ui.theme.ComuSeniasTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(LibraryDebug.appIsDebuggable()){
            val i: Intent = Intent(
                this@MainActivity,
                DemoIACamera::class.java
            )
            startActivity(i)

        }else{
            setContent {
                ComuSeniasTheme() {
                    // A surface container usin
                    //g the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        navController = rememberNavController()
                        AppNavigation(navController = navController)
                    }
                }
            }
        }

    }
}