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
            return try {
                val file = createTempFile(context)
                copyStreamToFile(context, uri, file)
                file
            } catch (e: Exception) {
                e.printStackTrace()
                // Devuelve un archivo temporal vacío si la función no pudo crear un archivo
                createTempFile(context)
            }
        }

        fun getImageUri(context: Context): Uri {
            try {
                val directory = createDirectory(context)
                val file = createTempFile(directory)
                return getUriForFile(context, file)

            } catch (e: IOException) {
                e.printStackTrace()
            }
            // Devuelve un Uri vacío si la función no pudo crear un archivo
            return Uri.EMPTY
        }

        fun getPathFromBitmap(context: Context, bitmap: Bitmap): String {
            val directory = getDirectory(context)
            val file = createFile(directory)
            compressBitmapToFile(bitmap, file)
            return file.path
        }

        private fun createTempFile(context: Context): File {
            return File.createTempFile(
                "${System.currentTimeMillis()}",
                ".png",
                context.cacheDir
            )
        }

        private fun copyStreamToFile(context: Context, uri: Uri, file: File) {
            val stream = context.contentResolver.openInputStream(uri)
            FileUtils.copyInputStreamToFile(stream, file)
        }

        private fun createDirectory(context: Context): File {
            val directory = File(context.cacheDir, "images")
            directory.mkdirs()
            return directory
        }

        private fun createTempFile(directory: File): File {
            return File.createTempFile(
                "selected_image_",
                ".jpg",
                directory
            )
        }

        private fun getUriForFile(context: Context, file: File): Uri {
            val authority = context.packageName + ".fileprovider"
            return getUriForFile(
                context,
                authority,
                file
            )
        }

        private fun getDirectory(context: Context): File {
            val wrapper = ContextWrapper(context)
            var file = wrapper.getDir("Images", Context.MODE_PRIVATE)
            return file
        }

        private fun createFile(directory: File): File {
            return File(directory, "${UUID.randomUUID()}.jpg")
        }

        private fun compressBitmapToFile(bitmap: Bitmap, file: File) {
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
        }
    }
}