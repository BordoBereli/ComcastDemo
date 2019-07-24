package com.kutluoglu.remote.model


import com.google.gson.annotations.SerializedName

data class Icon(
    @SerializedName("Height")
    val height: String,
    @SerializedName("URL")
    val uRL: String, // https://duckduckgo.com/i/19447f47.jpg
    @SerializedName("Width")
    val width: String
)