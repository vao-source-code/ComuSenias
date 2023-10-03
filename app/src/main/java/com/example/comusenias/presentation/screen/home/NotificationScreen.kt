package com.example.comusenias.presentation.screen.home

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Color.BLACK
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.AbsoluteAlignment.CenterLeft
import androidx.compose.ui.AbsoluteAlignment.Left
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.comusenias.R
import com.example.comusenias.presentation.navigation.AppNavigation
import com.example.comusenias.presentation.ui.theme.ComuSeniasTheme
import com.example.comusenias.presentation.ui.theme.primaryColorApp
import dagger.hilt.android.AndroidEntryPoint


class NotificationScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
        ComuSeniasTheme {
            Surface(color = MaterialTheme.colorScheme.background)
            {
                Notifi()

            }
        }
    }
}
}

@Composable
fun Notifi() {

    val context = LocalContext.current
    val channelId = "Channel"
    val notificationId = 0

    val myBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.notifications)

    LaunchedEffect(Unit) {
        createNotificationChannel(channelId, context)
    }
    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally ) {
        Box(
            modifier = Modifier
                .background(color = androidx.compose.ui.graphics.Color.Transparent)
                .size(450.dp,50.dp))
                {
                    Text(
                        text = "Avisos",
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier
                            .background(color = androidx.compose.ui.graphics.Color.White)
                            .size(90.dp, 50.dp)
                            .align(Alignment.BottomCenter))
        }


        Button (
            modifier = Modifier
                .padding(10.dp)
                .height(120.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(primaryColorApp),
            shape = RoundedCornerShape(5),
            onClick = {
                textoLargoNotification(channelId = channelId, context = context, notificationId = notificationId,
                    textContent = "Daniel Te ha pedido que corrijas tu ejercicio",
                    textTitle = "Hace 5 horas")

            }) {
            Text(text = "Hace 5 horas",modifier = Modifier.align(Bottom).padding(16.dp))
            Text(text = "Daniel te pidio que corrijas su ejercicio",modifier = Modifier.align(CenterVertically).padding(16.dp))
            Image(painterResource(R.drawable.eclipse_specialist1),"content description",modifier = Modifier.align(CenterVertically).padding(16.dp))
        }



        Button(
            modifier = Modifier
                .padding(10.dp)
                .height(120.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(primaryColorApp),
            shape = RoundedCornerShape(5),
            onClick = {
                textoConIconoNotificacion(iconoGrande = myBitmap, channelId = channelId, context = context, notificationId = notificationId, textContent = "Revisa tus notificaciones", textTitle = "hace 2 horas",)

            }) {
            Text(text = "Ayer,21:50",modifier = Modifier.align(Bottom).padding(16.dp))
            Text(text = "Sara Bear te pidio que corrijas su ejercicio",modifier = Modifier.align(CenterVertically).padding(16.dp))
            Image(painterResource(R.drawable.eclipse_specialist1),"content description",modifier = Modifier.align(CenterVertically).padding(16.dp))

        }


        Button(
            modifier = Modifier
                .padding(10.dp)
                .height(120.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(primaryColorApp),
            shape = RoundedCornerShape(5),
            onClick = {
                imagenGrandeNotification(iconoGrande = myBitmap, channelId = channelId, context = context, notificationId = notificationId, textContent = "Notificación hazte premium", textTitle = "Actualiza a Premium",)

            }) {
            Text(text = "Ayer,22:50",modifier = Modifier.align(Bottom).padding(16.dp))
            Text(text = "Nuevos Desafios Semanales Disponibles! Animate a practicar con la comunidad",modifier = Modifier.align(CenterVertically).padding(16.dp))
            Image(painterResource(R.drawable.eclipse_specialist1),"content description",modifier = Modifier.align(CenterVertically).padding(16.dp))

        }
        Button(
            modifier = Modifier
                .padding(10.dp)
                .height(120.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(primaryColorApp),
            shape = RoundedCornerShape(5),
            onClick = {
                imagenGrandeNotification(iconoGrande = myBitmap, channelId = channelId, context = context, notificationId = notificationId, textContent = "Notificación hazte premium", textTitle = "Actualiza a Premium",)

            }) {
            Text(text = "Anteayer,9:39",modifier = Modifier.align(Bottom).padding(16.dp))
            Text(text = "Mohamed Fahy te ha pedido que corrijas su ejercicio",modifier = Modifier.align(CenterVertically).padding(16.dp))
            Image(painterResource(R.drawable.eclipse_specialist1),"content description",modifier = Modifier.padding(16.dp))

        }

    }
}



@SuppressLint("MissingPermission")
fun imagenGrandeNotification(
    context: Context,
    channelId: String,
    notificationId: Int,
    textTitle: String,
    textContent: String,
    iconoGrande: Bitmap,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {
    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.girl)
        .setContentTitle(textTitle)
        .setContentText(textContent)
        .setLargeIcon(iconoGrande)
        .setStyle(
            NotificationCompat.BigPictureStyle()
                .bigPicture(iconoGrande)
        )
        .setPriority(priority)
    with(NotificationManagerCompat.from(context)) {
        notify(notificationId, builder.build())
    }
}


@SuppressLint("MissingPermission")
fun textoConIconoNotificacion(
    context: Context,
    channelId: String,
    notificationId: Int,
    textTitle: String,
    textContent: String,
    iconoGrande: Bitmap,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {
    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.notifications)
        .setContentTitle(textTitle)
        .setContentText(textContent)
        .setLargeIcon(iconoGrande)
        .setStyle(
            NotificationCompat.BigTextStyle()
                .bigText(
                    textContent
                )
        )
        .setPriority(priority)
    with(NotificationManagerCompat.from(context)) {
        notify(notificationId, builder.build())
    }
}

@SuppressLint("MissingPermission")
fun textoLargoNotification(
    context: Context,
    channelId: String,
    notificationId: Int,
    textTitle: String,
    textContent: String,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {
    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.diagnostic_category)
        .setContentTitle(textTitle)
        .setContentText(textContent)
        .setStyle(
            NotificationCompat.BigTextStyle()
                .bigText(textContent)
        )
        .setPriority(priority)
    with(NotificationManagerCompat.from(context)) {
        notify(notificationId, builder.build())
    }
}




fun createNotificationChannel(channelId: String, context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "Name"
        val desc = "Desc"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, name, importance).apply {
            description = desc
        }
        val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComuSeniasTheme {
        Notifi()
    }
}