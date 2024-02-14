package com.example.comusenias

import android.app.Application
import android.content.Context
import com.facebook.soloader.SoLoader
import dagger.hilt.android.HiltAndroidApp
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import okhttp3.OkHttpClient

@HiltAndroidApp
class ComuseniasApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initFlipper(this)
        buildOkHttp(this)
    }

    private fun initFlipper(context : Context) {
        SoLoader.init(this, false)
        val networkPlugin = NetworkFlipperPlugin()
        AndroidFlipperClient.getInstance(this).run {
            addPlugin(networkPlugin)
            addPlugin(InspectorFlipperPlugin(context, DescriptorMapping.withDefaults()))
            start()
        }
    }
}
private fun buildOkHttp(appContext: Context): OkHttpClient {
    val plugin = AndroidFlipperClient
        .getInstance(appContext)
        .getPluginByClass(NetworkFlipperPlugin::class.java)
    return OkHttpClient.Builder()
        .addNetworkInterceptor(FlipperOkhttpInterceptor(plugin))
        .build()
}
