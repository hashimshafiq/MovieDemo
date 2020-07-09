package com.hashimshafiq.moviedemo.data.remote

import com.hashimshafiq.moviedemo.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object Networking {

    private const val NETWORK_CALL_TIMEOUT = 60
    internal lateinit var API_KEY: String

    fun create(apiKey: String, baseUrl: String, cacheDir: File, cacheSize: Long): NetworkService {
        API_KEY = apiKey
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(
                        OkHttpClient.Builder()
                                .cache(Cache(cacheDir, cacheSize))
                                .addInterceptor(HttpLoggingInterceptor()
                                        .apply {
                                            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                                            else HttpLoggingInterceptor.Level.NONE
                                        })
                                .readTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                                .writeTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                                .build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NetworkService::class.java)
    }
}