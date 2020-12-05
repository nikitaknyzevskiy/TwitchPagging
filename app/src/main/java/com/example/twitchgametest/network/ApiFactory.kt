/*
 * Copyright (c) 2020.
 * Nkita Knyazevkiy
 * UA
 */

package com.example.twitchgametest.network

import okhttp3.CacheControl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiFactory {

    private var builder: OkHttpClient.Builder? = null

    init {
        builder = OkHttpClient.Builder()
        builder?.connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
        builder?.writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
        builder?.readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)

        builder?.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .header("platform", "android")
                .method(original.method, original.body)

            val request = requestBuilder
                .cacheControl(CacheControl.Builder().noCache().build())
                .build()

            chain.proceed(request)
        }
    }

    fun addHeader(name: String, value: String) {
        builder?.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .addHeader(name, value)

            val request = requestBuilder
                .cacheControl(CacheControl.Builder().noCache().build())
                .build()

            chain.proceed(request)
        }
    }

    fun buildRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(builder!!.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        var TAG: String = ApiFactory::class.java.simpleName

        private const val DEFAULT_TIMEOUT = 15
        const val CONNECT_TIMEOUT = DEFAULT_TIMEOUT
        const val WRITE_TIMEOUT = DEFAULT_TIMEOUT
        const val READ_TIMEOUT = DEFAULT_TIMEOUT
    }
}