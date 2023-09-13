package com.example.comusenias.domain.library

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.example.comusenias.R
import java.io.File

class ComposeFileProvider : FileProvider(R.xml.file_paths) {

    companion object {
        fun getImageUrl(context: Context): Uri {
            val dir = File(context.cacheDir, "images")
            dir.mkdirs()
            val file = File.createTempFile(
                "selected_image_",
                ".jpg",
                dir
            )
            val auth = context.packageName + ".fileprovider"
            return getUriForFile(
                context, auth, file
            )

        }
    }
}