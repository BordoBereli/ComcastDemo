package com.kutluoglu.remote.model


import com.google.gson.annotations.SerializedName

data class Developer(
    @SerializedName("name")
    val name: String, // DDG Team
    @SerializedName("type")
    val type: String, // ddg
    @SerializedName("url")
    val url: String // http://www.duckduckhack.com
)