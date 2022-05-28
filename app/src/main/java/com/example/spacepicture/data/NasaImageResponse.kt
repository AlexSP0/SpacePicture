package com.example.spacepicture.data

import com.google.gson.annotations.SerializedName

data class NasaImageResponse(
    @SerializedName("copyright")
    val copyright: String?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("explanation")
    val explanation: String?,
    @SerializedName("media-type")
    val mediaType: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("hdurl")
    val hdurl: String?
)