package com.example.spacepicture.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaQueryDailyPicture {

    @GET("planetary/apod")
    fun loadDailyImage(@Query("api_key") apiKey: String): Call<NasaImageResponse>
}