package com.example.comusenias.presentation.screen.gallery

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
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
import androidx.compose.material.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.comusenias.R
import com.example.comusenias.presentation.navigation.AppScreen
import com.example.comusenias.presentation.view_model.GalleryViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@Composable
fun GalleryScreen(
    viewModel: GalleryViewModel = hiltViewModel(),
    navController: NavController,
    path: String,
) {


    val context = LocalContext.current

    val preferenceManager = remember { PreferenceManager(context) }

    var isChecked by remember { mutableStateOf(preferenceManager.getBoolean("key_boolean", false)) }

    val vowelsResponse = viewModel.vowelsResults.collectAsState()

    val defaultImage = painterResource(R.drawable.correct_image)

    val loadedImage = remember { mutableStateOf<Bitmap?>(null) }



    LaunchedEffect(path) {
        loadedImage.value = loadBitmapFromUri(context, Uri.parse(path))
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
            loadedImage.value?.let { bitmap ->

                val imageBitmap = bitmap.asImageBitmap()

                Image(
                    bitmap = imageBitmap,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize().rotate(270F),
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




        if (vowelsResponse.value == null) {
            Text("Letra: ")
        }
        else{
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
        var image = BitmapFactory.decodeFileDescriptor(fileDescriptor)

        // Obtén la orientación de la imagen del ExifInterface
        val exif = ExifInterface(fileDescriptor!!)
        val orientation = exif.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_UNDEFINED
        )
        val matrix = Matrix()
        when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> matrix.postRotate(90F)
            ExifInterface.ORIENTATION_ROTATE_180 -> matrix.postRotate(180F)
            ExifInterface.ORIENTATION_ROTATE_270 -> matrix.postRotate(270F)
        }
        // Gira la imagen según la orientación
        image = Bitmap.createBitmap(image, 0, 0, image.width, image.height, matrix, true)

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





