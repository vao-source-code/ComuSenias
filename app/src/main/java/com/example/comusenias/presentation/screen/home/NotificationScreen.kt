package com.example.comusenias.presentation.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.comusenias.R
import com.example.comusenias.domain.models.model.Notification
import com.example.comusenias.presentation.ui.theme.SIZE16
import com.example.comusenias.presentation.ui.theme.SIZE24

@Composable
fun NotificationScreen() {
    LazyColumn(modifier = Modifier.padding(SIZE16.dp)) {
        items(notificationList) { notification ->
            NotificationItem(notification)
            Spacer(modifier = Modifier.height(SIZE16.dp))
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(R.drawable.onboarding1),
                    contentDescription = null,
                    modifier = Modifier.size(SIZE24.dp)
                )
                Text(
                    text = "Suscribete ahora y desbloquea beneficios exclusivos",
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
                Button(
                    onClick = { /* Handle button click */ },
                ) {
                    val suscribe = "suscribirse"
                    Text(suscribe)
                }
            }
        }
    }
}
val notificationList = listOf(
    Notification(
        iconResId = R.drawable.baseline_arrow_back_24,
        time = " ",
        content = "AVISO"
    ),
    Notification(
        iconResId = R.drawable.noti3,
        content = "Marcela te ha pedido que corrijas su ejercicio",
        time = "Hace 5 horas"
    ),
    Notification(
        iconResId = R.drawable.noti1,
        content = "Natalia te ha pedido que corrijas su ejercicio",
        time = "Ayer 10:30"
    ),
    Notification(
        iconResId = R.drawable.noti4,
        content = "Daniel te ha pedido que corrijas su ejercicio",
        time = "anteayer 6:15"
    ),
    Notification(
        iconResId = R.drawable.noti2,
        content = "Nuevos Desafios semanales disponibles. ¡Animate a practicar!",
        time = "anteayer 12:30"
    ),
    // Add more notifications as needed
)

@Composable
fun NotificationItem(notification: Notification) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(SIZE16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (notification == notificationList.first()) {
                Image(
                    painter = painterResource(notification.iconResId),
                    contentDescription = null,
                    modifier = Modifier.size(SIZE16.dp)
                )
                Spacer(modifier = Modifier.width(30.dp).height(SIZE16.dp))
                Column {
                    Modifier.width(SIZE16.dp)
                    Text(
                        text = notification.content,
                        style = MaterialTheme.typography.subtitle1
                    )
                }
            } else {
                Image(
                    painter = painterResource(notification.iconResId),
                    contentDescription = null,
                    modifier = Modifier.size(SIZE16.dp)
                )
                Spacer(modifier = Modifier.width(SIZE16.dp))
                Column {
                    Text(
                        text = notification.content,
                        style = MaterialTheme.typography.subtitle1
                    )
                    Spacer(modifier = Modifier.height(SIZE16.dp))
                    Text(
                        text = notification.time,
                        style = MaterialTheme.typography.caption
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NotificationScreen()
}
