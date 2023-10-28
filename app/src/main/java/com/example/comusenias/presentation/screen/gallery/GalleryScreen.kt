package com.example.comusenias.presentation.screen.gallery

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.comusenias.R
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.ui.theme.CHILD_FILE
import com.example.comusenias.presentation.ui.theme.ERROR_TAKE_PHOTO
import com.example.comusenias.presentation.ui.theme.FIELD_IMAGES
import com.example.comusenias.presentation.ui.theme.GALLERY_SCREEN
import com.example.comusenias.presentation.ui.theme.KEY_BOOLEAN
import com.example.comusenias.presentation.ui.theme.LETRA
import com.example.comusenias.presentation.ui.theme.MULTI_PART_FROM_DATA
import com.example.comusenias.presentation.ui.theme.SIZE16
import com.example.comusenias.presentation.ui.theme.TAKE_PHOTO
import com.example.comusenias.presentation.view_model.GalleryViewModel
import com.example.comusenias.presentation.view_model.LevelViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream

@Composable
fun GalleryScreen(
    levelViewModel: LevelViewModel,
    viewModel: GalleryViewModel = hiltViewModel(),
    navController: NavController,
    path: String,
) {
    val context = LocalContext.current
    val preferenceManager = remember { PreferenceManager(context) }
    val vowelsResponse = viewModel.vowelsResults.collectAsState()
    val defaultImage = painterResource(R.drawable.correct_image)
    val loadedImage = remember { mutableStateOf<Bitmap?>(null) }

    // Cargar la imagen desde el archivo al inicio
    LaunchedEffect(path) {
        loadedImage.value = loadBitmapFromUri(context, Uri.parse(path))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(SIZE16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(shape = MaterialTheme.shapes.medium)
                .background(color = Color.Gray)
                .padding(4.dp)
        ) {
            loadedImage.value?.let { bitmap ->
                val imageBitmap = bitmap.asImageBitmap()
                Image(
                    bitmap = imageBitmap,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
                BitmapToMapAndPostGallery(bitmap, viewModel)
            } ?: run {
                Image(
                    painter = defaultImage,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
            }
        }

        if (vowelsResponse.value?.letra == null) {
            Text("${LETRA}:")
        } else {
            Text("${LETRA}:${vowelsResponse.value?.letra}")
        }

        Button(
            onClick = {
                preferenceManager.saveBoolean(KEY_BOOLEAN, true)
                navController.navigate(AppScreen.CameraScreenPermission.route)
            },
            modifier = Modifier
                .padding(SIZE16.dp)
                .fillMaxWidth()
        ) {
            Text(TAKE_PHOTO)
        }
    }
}

/**
 * Función que maneja el proceso de convertir un [Bitmap] en un archivo y luego
 * lo publica en una galería utilizando un [GalleryViewModel].
 *
 * @param bitmap El [Bitmap] que se convertirá y publicará en la galería.
 * @param viewModel El [GalleryViewModel] que maneja la publicación de la imagen en la galería.
 *
 * Esta función realiza las siguientes acciones:
 * 1. Convierte el [Bitmap] en un archivo utilizando la función [bitmapToFile].
 * 2. Crea un [RequestBody] a partir del archivo, especificando "multipart/form-data" como el tipo de medio.
 * 3. Crea una [MultipartBody.Part] a partir del [RequestBody], utilizando "image" como el nombre de la parte
 *    y el nombre del archivo como el nombre del archivo.
 * 4. Llama a la función [postGallery] del [GalleryViewModel] con la [MultipartBody.Part] creada.
 *
 * Esta función es componible, por lo que puede utilizarse en el kit de herramientas de interfaz de usuario Compose
 * para realizar su funcionalidad como parte de la composición de la interfaz de usuario.
 */
@Composable
private fun BitmapToMapAndPostGallery(
    bitmap: Bitmap,
    viewModel: GalleryViewModel
) {
    bitmap.let {
        val file = bitmapToFile(bitmap, LocalContext.current)
        val requestFile =
            RequestBody.create(MULTI_PART_FROM_DATA.toMediaTypeOrNull(), file)
        val body =
            MultipartBody.Part.createFormData(FIELD_IMAGES, file.name, requestFile)
        viewModel.postGallery(body)
    }
}


/**
 * Función que carga un [Bitmap] a partir de un [Uri].
 *
 * @param context El [Context] usado para obtener el [ContentResolver].
 * @param uri El [Uri] de la imagen que se desea cargar.
 *
 * @return Un [Bitmap] si la imagen se carga correctamente, o un [Bitmap] vacío en caso de error.
 *
 * Esta función realiza las siguientes acciones:
 * 1. Obtiene una instancia de [ParcelFileDescriptor] a partir del [Uri] proporcionado.
 * 2. Decodifica la imagen usando [BitmapFactory.decodeFileDescriptor].
 * 3. Cierra el [ParcelFileDescriptor].
 * 4. Devuelve la imagen decodificada.
 *
 * En caso de error durante la carga de la imagen, se registra el error en Logcat bajo la etiqueta "GalleryScreen".
 */
fun loadBitmapFromUri(
    context: Context,
    uri: Uri
): Bitmap? {
    val contentResolver = context.contentResolver
    return try {
        val parcelFileDescriptor = contentResolver.openFileDescriptor(uri, "r")
        val fileDescriptor = parcelFileDescriptor?.fileDescriptor
        val image = BitmapFactory.decodeFileDescriptor(fileDescriptor)
        parcelFileDescriptor?.close()
        image
    } catch (e: Exception) {
        Log.e(GALLERY_SCREEN, "$ERROR_TAKE_PHOTO $uri", e)
        Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
    }
}

/**
 * Convierte un [Bitmap] en un [File].
 *
 * @param bitmap El [Bitmap] que se desea convertir en [File].
 * @param context El [Context] usado para obtener el directorio de archivos.
 *
 * @return Un [File] que representa la imagen.
 *
 * Esta función realiza las siguientes acciones:
 * 1. Crea un nuevo archivo en el directorio de archivos del contexto con el nombre "image.jpg".
 * 2. Abre un [FileOutputStream] para el nuevo archivo.
 * 3. Comprime el [Bitmap] en formato JPEG al 100% de calidad y escribe los bytes comprimidos en el [FileOutputStream].
 * 4. Limpia el [FileOutputStream] para asegurarse de que todos los bytes se escriben en el archivo.
 * 5. Cierra el [FileOutputStream].
 * 6. Devuelve el archivo creado.
 */
fun bitmapToFile(bitmap: Bitmap, context: Context): File {
    val file = File(context.filesDir, CHILD_FILE)
    FileOutputStream(file).use { outputStream ->
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream.flush()
    }
    return file
}





