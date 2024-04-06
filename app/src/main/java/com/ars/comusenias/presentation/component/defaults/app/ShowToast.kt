package com.ars.comusenias.presentation.component.defaults.app

import android.content.Context
import android.widget.Toast

fun showToast(context: Context, text: String, duration: Int) {
    Toast.makeText(context, text, duration).show()
}