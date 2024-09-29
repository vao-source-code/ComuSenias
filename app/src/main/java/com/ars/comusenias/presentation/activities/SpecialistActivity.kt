package com.ars.comusenias.presentation.activities

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ars.comusenias.presentation.activities.AuthActivity.Companion
import com.ars.comusenias.presentation.ui.theme.ComuSeniasTheme
import com.ars.comusenias.presentation.navigation.*
import com.ars.comusenias.presentation.view_model.ChildrenProfileViewModel
import com.ars.comusenias.presentation.view_model.LevelViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpecialistActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComuSeniasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navController = rememberNavController()
                    SpecialistNavigation(navController = navController)
                }
            }
        }
    }

    companion object {
        lateinit var getChildrenProfileViewModel: ChildrenProfileViewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        getChildrenProfileViewModel.onClear()
    }
}