package com.example.comusenias.presentation.gallery

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.comusenias.R
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.view_model.CameraViewModel
import com.example.comusenias.presentation.view_model.GalleryViewModel
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream

@Composable
fun GalleryScreen(
    viewModel: GalleryViewModel = hiltViewModel(),
    navController: NavController,
) {

    val vowelsResponse = viewModel.vowelsResults.collectAsState()

    val base64Image = remember { mutableStateOf<Bitmap?>(null) }

    /*LaunchedEffect(vowelsResponse.value?.image_base64) {
        // Actualiza la imagen base64 en la vista
        base64Image.value = decodeBase64ToBitmap(vowelsResponse.value?.image_base64)
    }*/


    // Estado para almacenar la URI de la imagen seleccionada o capturada.
    val imageUri = remember { mutableStateOf<Uri?>(null) }

    val getContent =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
            // Here you can do something with the selected URI, like updating the image in your UI
            imageUri.value = uri
        }
    // Estado para almacenar la imagen por defecto
    val defaultImage = painterResource(R.drawable.correct_image)


    LaunchedEffect(vowelsResponse.value?.image_base64) {
        // Decodificar la cadena base64 a un objeto Bitmap y asignarla al estado base64Image
        base64Image.value = decodeBase64ToBitmap(vowelsResponse.value?.image_base64)
    }

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

            imageUri.value?.let { uri ->
                val bitmap = loadBitmapFromUri(uri)
                bitmap?.let { bitmap ->
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
            if (base64Image.value != null) {
                Image(
                    bitmap = base64Image.value!!.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
            } else {
                // Utiliza la imagen por defecto si no hay imagen base64
                Image(
                    painter = defaultImage,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
            }


        }


        if(vowelsResponse.value?.letra == null){
            Text("Letra:")
        }else{
            Text("Letra:${vowelsResponse.value?.letra}")
        }


        // Botón para seleccionar una imagen de la galería.
        Button(
            onClick = {
                getContent.launch("image/*")
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("Seleccionar de la galería")
        }

        /* Button(
             onClick = {
                 navController.navigate(AppScreen.CameraScreen.route)
             },
             modifier = Modifier
                 .padding(16.dp)
                 .fillMaxWidth()
         ) {
             Text("Sacar Foto")
         }*/
    }
}

@Composable
fun loadBitmapFromUri(uri: Uri): Bitmap? {
    val context = LocalContext.current
    val contentResolver = context.contentResolver
    return try {
        val parcelFileDescriptor = contentResolver.openFileDescriptor(uri, "r")
        val fileDescriptor = parcelFileDescriptor?.fileDescriptor
        val image = android.graphics.BitmapFactory.decodeFileDescriptor(fileDescriptor)
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

fun decodeBase64ToBitmap(input: String?): Bitmap? {
    return try {
        val decodedBytes = Base64.decode(input, Base64.DEFAULT)
        BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    } catch (e: Exception) {
        Log.e("GalleryScreen", "Error al decodificar Base64 a Bitmap", e)
        null
    }
}


