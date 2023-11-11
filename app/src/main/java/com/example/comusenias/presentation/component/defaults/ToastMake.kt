package com.example.comusenias.presentation.component.defaults

import android.content.Context
import android.widget.Toast
import es.dmoral.toasty.Toasty

object ToastMake {
    fun showInfo(context: Context, message: String) {
        Toasty.info(context, message, Toast.LENGTH_SHORT, true).show()
    }

    fun showInfo(context: Context, message: String, duration: Int) {
        Toasty.info(context, message, duration, true).show()
    }

    fun showSuccess(context: Context, message: String) {
        Toasty.success(context, message, Toast.LENGTH_SHORT, true).show()
    }

    fun showWarning(context: Context, message: String) {
        Toasty.warning(context, message, Toast.LENGTH_LONG, true).show()
    }

    fun showError(context: Context, message: String) {
        Toasty.error(context, message, Toast.LENGTH_LONG, true).show()
    }
}
