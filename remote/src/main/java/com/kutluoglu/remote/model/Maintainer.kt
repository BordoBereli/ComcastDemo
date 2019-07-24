package com.kutluoglu.remote.model


import com.google.gson.annotations.SerializedName

data class Maintainer(
    @SerializedName("github")
    val github: String // duckduckgo
)