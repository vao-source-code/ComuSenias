package com.example.comusenias.domain.library

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.content.FileProvider
import com.example.comusenias.R
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.UUID

class ComposeFileProvider : FileProvider(R.xml.file_paths) {

    companion object {

        fun createFileFromUri(context: Context, uri: Uri): File {
            try {
                val stream = context.contentResolver.openInputStream(uri)
                val file = File.createTempFile(
                    "${System.currentTimeMillis()}",
                    ".png",
                    context.cacheDir
                )
                FileUtils.copyInputStreamToFile(stream, file)
                return file
            } catch (e: Exception) {
                e.printStackTrace()
                // Devuelve un archivo temporal vacío si la función no pudo crear un archivo
                return File.createTempFile(
                    "${System.currentTimeMillis()}",
                    ".png",
                    context.cacheDir
                )
            }
        }

        fun getImageUri(context: Context): Uri {
            try {
                val directory = File(context.cacheDir, "images")
                directory.mkdirs()
                val file = File.createTempFile(
                    "selected_image_",
                    ".jpg",
                    directory
                )
                val authority = context.packageName + ".fileprovider"
                return getUriForFile(
                    context,
                    authority,
                    file
                )

            } catch (e: IOException) {
                e.printStackTrace()
            }
            // Devuelve un Uri vacío si la función no pudo crear un archivo
            return Uri.EMPTY
        }

        fun getPathFromBitmap(context: Context, bitmap: Bitmap): String {
            val wrapper = ContextWrapper(context)
            var file = wrapper.getDir("Images", Context.MODE_PRIVATE)
            file = File(file, "${UUID.randomUUID()}.jpg")
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
            return file.path
        }
    }
}