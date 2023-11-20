import android.media.browse.MediaBrowser
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView


@Composable
fun VideoPlayer(){
   Text("HOla que tal")
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
   VideoPlayer()
}