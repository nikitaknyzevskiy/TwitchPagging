/*
 * Copyright (c) 2020.
 * Nkita Knyazevkiy
 * UA
 */

package com.example.twitchgametest.network

class AppRest<T>(private val service: Class<T>)  {

    private val apiFactory: ApiFactory = ApiFactory()
    private lateinit var baseUrl: String

    fun addHeader(name: String, value: String): AppRest<T> {
        apiFactory.addHeader(name, value)
        return this
    }

    fun url(url: String): AppRest<T> {
        baseUrl = url
        return this
    }

    fun build(): T {
        return apiFactory
            .buildRetrofit(baseUrl)
            .create(service)
    }

}