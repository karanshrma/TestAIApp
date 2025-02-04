package com.example.bootcampapp3

import com.google.gson.JsonObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


import okhttp3.OkHttpClient
import retrofit2.http.GET
import retrofit2.http.Query

import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val BASE_URL = "https://py-api-neon-one.vercel.app/"


    private val client = OkHttpClient.Builder()
        .connectTimeout(100, TimeUnit.SECONDS)
        .readTimeout(100, TimeUnit.SECONDS)
        .writeTimeout(100, TimeUnit.SECONDS)
        .build()

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}