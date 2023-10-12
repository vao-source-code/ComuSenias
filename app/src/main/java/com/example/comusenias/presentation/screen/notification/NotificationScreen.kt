package com.example.comusenias.presentation.screen.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.comusenias.R
import com.example.comusenias.domain.models.model.Notification
import com.example.comusenias.presentation.component.defaults.DefaultTopBar
import com.example.comusenias.presentation.ui.theme.NOMBRE_NOTIFICATION1
import com.example.comusenias.presentation.ui.theme.NOMBRE_NOTIFICATION2
import com.example.comusenias.presentation.ui.theme.NOMBRE_NOTIFICATION3
import com.example.comusenias.presentation.ui.theme.NOMBRE_NOTIFICATION4
import com.example.comusenias.presentation.ui.theme.SIZE50

@Composable
fun NotificationScreen() {
    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Aviso",
                upAvailable = true
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn(modifier = Modifier.padding(16.dp)) {
                items(notificationList) { notification ->
                    NotificationItem(notification)

                }
            }
        }
    }
}
val notificationList = listOf(
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
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(notification.iconResId),
                contentDescription = null,
                modifier = Modifier
                    .size(SIZE50.dp)
                    .aspectRatio(1f)
                    .clip(MaterialTheme.shapes.medium)
                    .scale(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = notification.content,
                    style = MaterialTheme.typography.subtitle1
                )
                Text(
                    text = notification.time.toString(),
                    style = MaterialTheme.typography.caption,
                    textAlign = TextAlign.End
                )
            }
        }
        Divider(color = Color.Gray, thickness = 0.5.dp)
        Spacer(modifier = Modifier.height(16.dp))
    }
}
@Preview(showBackground = true)
@Composable
fun NotificationScreenPreview() {
    NotificationScreen()
}

