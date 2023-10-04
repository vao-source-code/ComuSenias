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
import com.example.comusenias.R
@Composable
fun NotificationScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        NotificationItem(
            iconResId = R.drawable.baseline_arrow_back_24,
            content = "Aviso",
            time = " "
        )
        Spacer(modifier = Modifier.height(16.dp))
        NotificationItem(
            iconResId = R.drawable.eclipse_specialist1,
            content = "Daniel te ha pedido que corrijas su ejercicio",
            time = "Hace 5 horas"
        )
        Spacer(modifier = Modifier.height(16.dp))
        NotificationItem(
            iconResId = R.drawable.eclipse_specialist2,
            content = "Natalia te ha pedido que corrijas su ejercicio",
            time = "Ayer 10:30 am"
        )
        Spacer(modifier = Modifier.height(16.dp))
        NotificationItem(
            iconResId = R.drawable.onboarding3,
            content = "Nuevos Desafios semanales disponibles. ¡Animate a practicar!",
            time = "Anteayer 9:30 am"
        )
        Spacer(modifier = Modifier.height(16.dp))
        NotificationItem(
            iconResId = R.drawable.onboarding2,
            content = "Mohamed Fathy te pidio que corrijas su ejercicio",
            time = "Anteayer 12:30 am"
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(R.drawable.baseline_diversity_3_24),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Text(text = "Suscribete ahora y desbloquea beneficios esclusivos", style = MaterialTheme.typography.subtitle1,textAlign = TextAlign.Center)
        Button(onClick = { /* Handle button click */ },modifier = Modifier.align(Alignment.End)) {
            Text(text = "Subscribe")
        }
    }
}

@Composable
fun NotificationItem(iconResId: Int, content: String, time: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(iconResId),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = content, style = MaterialTheme.typography.subtitle1)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = time, style = MaterialTheme.typography.caption)
            }
        }
    }
}

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        NotificationScreen()
    }
