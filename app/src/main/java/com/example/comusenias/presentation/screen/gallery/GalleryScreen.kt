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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

    var isChecked by remember { mutableStateOf(preferenceManager.getBoolean("key_boolean", false)) }


    val vowelsResponse = viewModel.vowelsResults.collectAsState()

    // Estado para almacenar la imagen por defecto
    val defaultImage = painterResource(R.drawable.correct_image)

    // Estado para almacenar la imagen cargada desde el archivo
    val loadedImage = remember { mutableStateOf<Bitmap?>(null) }


    // Cargar la imagen desde el archivo al inicio

    LaunchedEffect(path) {
        loadedImage.value = loadBitmapFromUri(context, Uri.parse(path))
    }


    // Estado para determinar si se debe mostrar la imagen por defecto o la cargada desde el archivo

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // ImageView para mostrar la imagen seleccionada o capturada.
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

                bitmap.let { bitmap ->
                    val file = bitmapToFile(bitmap, LocalContext.current)
                    val requestFile =
                        RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
                    val body = MultipartBody.Part.createFormData("image", file.name, requestFile)

                    viewModel.postGallery(body)
                }


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
            Text("Letra:")
        } else {
            Text("Letra:${vowelsResponse.value?.letra}")
        }


        Button(
            onClick = {
                isChecked = true
                preferenceManager.saveBoolean("key_boolean",isChecked)
                navController.navigate(AppScreen.CameraScreenPermission.route)
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("Sacar Foto")
        }
    }
}


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
        Log.e("GalleryScreen", "Error al cargar la imagen desde URI: $uri", e)
        null
    }
}


fun bitmapToFile(bitmap: Bitmap, context: Context): File {
    val filesDir = context.filesDir
    val file = File(filesDir, "image.jpg")
    val outputStream = FileOutputStream(file)
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
    outputStream.flush()
    outputStream.close()
    return file
}





