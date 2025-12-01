package org.example.app.data.remote

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.example.app.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Simple provider for networking singletons without DI frameworks.
 * Ensures base URL points to emulator loopback by default (http://10.0.2.2:3001/).
 */
object NetworkModule {

    private val moshi: Moshi by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    private val logging: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor { message -> Log.d("HTTP", message) }
            .apply { level = HttpLoggingInterceptor.Level.BASIC }
    }

    private val headersInterceptor = Interceptor { chain ->
        val req = chain.request()
            .newBuilder()
            .addHeader("Accept", "application/json")
            .build()
        chain.proceed(req)
    }

    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(headersInterceptor)
            .addInterceptor(logging)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private val retrofit: Retrofit by lazy {
        // Determine base URL. Prefer BuildConfig if available; otherwise fallback to emulator loopback.
        val configuredBase = try {
            BuildConfig.BASE_URL
        } catch (t: Throwable) {
            "http://10.0.2.2:3001/"
        }
        // Defensive: ensure baseUrl ends with slash for Retrofit
        val base = if (configuredBase.endsWith("/")) configuredBase else (configuredBase + "/")
        Retrofit.Builder()
            .baseUrl(base)
            .client(okHttp)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    // PUBLIC_INTERFACE
    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
