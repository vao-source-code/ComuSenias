package com.example.comusenias.presentation.screen.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.comusenias.R
import com.example.comusenias.domain.models.model.Notification
import com.example.comusenias.presentation.ui.theme.Aviso
import com.example.comusenias.presentation.ui.theme.NOMBRE_NOTIFICATION1
import com.example.comusenias.presentation.ui.theme.NOMBRE_NOTIFICATION2
import com.example.comusenias.presentation.ui.theme.NOMBRE_NOTIFICATION3
import com.example.comusenias.presentation.ui.theme.NOMBRE_NOTIFICATION4
import com.example.comusenias.presentation.ui.theme.SIZE16
import com.example.comusenias.presentation.ui.theme.SIZE24
import com.example.comusenias.presentation.ui.theme.Suscribe
import com.example.comusenias.presentation.ui.theme.Suscribe_Ya

@Composable
fun NotificationScreen(navController: NavHostController) {

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
                    text = Suscribe_Ya,
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)
                )
                Button(
                    onClick = { /* Handle button click */ },
                ) {
                    Text(Suscribe)
                }
            }
        }
    }
}
val notificationList = listOf(
    Notification(
        iconResId = R.drawable.baseline_arrow_back_24,
        time = 1.0,
        content = Aviso
    ),
    Notification(
        iconResId = R.drawable.onboarding2,
        content = NOMBRE_NOTIFICATION1,
        time = 15.50
    ),
    Notification(
        iconResId = R.drawable.onboarding3,
        content = NOMBRE_NOTIFICATION2,
        time = 10.30
    ),
    Notification(
        iconResId = R.drawable.onboarding1,
        content = NOMBRE_NOTIFICATION3,
        time = 6.15
    ),
    Notification(
        iconResId = R.drawable.onboarding2,
        content = NOMBRE_NOTIFICATION4,
        time = 12.34
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
                }
            }
        }
    }
}

