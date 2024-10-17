package com.ars.comusenias.presentation.screen.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.ars.comusenias.R
import com.ars.comusenias.presentation.activities.MainActivity
import com.ars.comusenias.presentation.component.bottomBar.ShowBottomBar
import com.ars.comusenias.presentation.component.defaults.ButtonDefault
import com.ars.comusenias.presentation.component.defaults.GetImage
import com.ars.comusenias.presentation.component.defaults.app.ButtonAppIcon
import com.ars.comusenias.presentation.component.home.TopBarHome
import com.ars.comusenias.presentation.navigation.AppScreen
import com.ars.comusenias.presentation.ui.theme.SIZE16
import com.ars.comusenias.presentation.ui.theme.SIZE20
import com.ars.comusenias.presentation.ui.theme.SIZE3
import com.ars.comusenias.presentation.ui.theme.logoApp
import com.ars.comusenias.presentation.ui.theme.size150

@Composable
fun SupportScreen(
    navController: NavController,
    modifier: Modifier
) {

    Scaffold(
        topBar = {
            Surface(shadowElevation = SIZE3.dp) {
                TopBarHome(
                    name = MainActivity.getChildrenProfileViewModel.userData.name,
                    image = MainActivity.getChildrenProfileViewModel.userData.image,
                    onClickNotification = { navController.navigate(AppScreen.NotificationScreen.route) },
                    onclickProfile = { navController.navigate(AppScreen.ChildrenProfileScreen.route) })
            }
        },
        bottomBar = {
            ShowBottomBar(navController = navController)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .padding(SIZE16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly

            ) {
                val context = LocalContext.current
                // Imagen del icono de la aplicación
                GetImage(
                    painter = R.drawable.comu_senias_with_text,
                    contentDescription = logoApp,
                    width = size150.dp,
                    height = size150.dp
                )

                // Texto
                Text(
                    text = "Comunicate con nosotros",
                    modifier = Modifier.padding(vertical = 16.dp)
                )

                // Botón para abrir Gmail
                ButtonAppIcon(
                    onClickButton = {
                        val intent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:comusenias@gmail.com")
                        }
                        startActivity(context, intent, null)
                    },
                    titleButton = "Gmail",
                    icon = Icons.Default.Email
                )

                Spacer(modifier = Modifier.height(SIZE20.dp))

                // Botón para abrir el navegador web
                ButtonAppIcon(
                    onClickButton = {
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse("http://www.comuseñas.com.ar")
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        }
                        val options = Bundle().apply {
                            putInt("android.support.customtabs.extra.TOOLBAR_COLOR", ContextCompat.getColor(context, R.color.primaryColorApp))
                        }
                        startActivity(context, intent, options)
                    }
                    ,
                    titleButton = "Sitio web",
                    icon = Icons.Default.Info
                )
            }
        }
    }


}
