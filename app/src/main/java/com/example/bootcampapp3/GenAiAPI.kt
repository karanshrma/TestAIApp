package com.example.bootcampapp3

import com.google.gson.JsonObject
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GenAiAPI {


    @GET("/api/genai/{message}")
    fun getResponse(@Path("message") message: String): retrofit2.Call<JsonObject>
}
