package com.ars.comusenias.core.di

import android.annotation.SuppressLint
import com.ars.comusenias.constants.RetrofitUrl.BASE_URL
import com.ars.comusenias.data.api.ApiService
import com.ars.comusenias.presentation.ui.theme.TLS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@InstallIn(SingletonComponent::class)
@Module
object RetrofitModule {
    /**
     * Proporciona un singleton OkHttpClient que permite el logging de todas las peticiones HTTP y las respuestas y
     * confía en todos los certificados SSL.
     *
     * @return OkHttpClient con logging y confiando en todos los certificados SSL.
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = getHttpLoggingInterceptor()
        val trustAllCerts: Array<TrustManager> = getTrustManager()
        val sslContext: SSLContext = getSSLContext(trustAllCerts)

        return getOkHttpClientBuilder(loggingInterceptor, sslContext, trustAllCerts)
    }

    /**
     * Crea y configura un OkHttpClient que permite el logging de todas las peticiones HTTP y las respuestas y
     * confía en todos los certificados SSL.
     *
     * @param loggingInterceptor Interceptor para registrar todas las peticiones y respuestas HTTP.
     * @param sslContext Contexto SSL para configurar el socket factory del cliente.
     * @param trustAllCerts Array de TrustManager para configurar el socket factory del cliente.
     * @return OkHttpClient configurado con el interceptor de logging y el socket factory del contexto SSL.
     */
    private fun getOkHttpClientBuilder(
        loggingInterceptor: HttpLoggingInterceptor,
        sslContext: SSLContext,
        trustAllCerts: Array<TrustManager>
    ) = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .sslSocketFactory(sslContext.socketFactory, trustAllCerts[0] as X509TrustManager)
        .hostnameVerifier { _, _ -> true }
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    /**
     * Crea e inicializa un contexto SSL que confía en todos los certificados SSL.
     *
     * @param trustAllCerts Un array de TrustManager configurado para confiar en todos los certificados SSL.
     * @return Un contexto SSL que confía en todos los certificados SSL.
     */
    private fun getSSLContext(trustAllCerts: Array<TrustManager>): SSLContext =
        SSLContext.getInstance(TLS).apply {
            init(null, trustAllCerts, SecureRandom())
        }

    /**
     * Crea y configura un array de TrustManager para confiar en todos los certificados SSL.
     * Este método es peligroso y sólo debe ser utilizado para propósitos de desarrollo.
     *
     * @return Array de TrustManager configurado para confiar en todos los certificados SSL.
     */
    private fun getTrustManager(): Array<TrustManager> = arrayOf(@SuppressLint("CustomX509TrustManager")
    object : X509TrustManager {
        override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) =
            Unit

        override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) =
            Unit

        override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
    })

    /**
     * Crea y configura un HttpLoggingInterceptor para registrar las solicitudes y respuestas HTTP.
     *
     * @return HttpLoggingInterceptor configurado para registrar el cuerpo de las solicitudes y respuestas HTTP.
     */
    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    /**
     * Proporciona una instancia de ApiService para realizar llamadas a la API.
     *
     * @param okHttpClient OkHttpClient para hacer las peticiones HTTP.
     * @return ApiService para hacer las llamadas a la API.
     */
    @Provides
    @Singleton
    fun provideApiService(okHttpClient: OkHttpClient): ApiService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
}