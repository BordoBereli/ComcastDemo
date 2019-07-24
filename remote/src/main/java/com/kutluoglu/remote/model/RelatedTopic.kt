package com.kutluoglu.remote.model


import com.google.gson.annotations.SerializedName

data class RelatedTopic(
    @SerializedName("FirstURL")
    val firstURL: String, // https://duckduckgo.com/Ziggy_Sobotka
    @SerializedName("Icon")
    val icon: Icon,
    @SerializedName("Result")
    val result: String, // <a href="https://duckduckgo.com/Ziggy_Sobotka">Ziggy Sobotka</a><br>Chester Karol "Ziggy" Sobotka is a fictional character on the HBO drama The Wire, played by actor James Ransone.
    @SerializedName("Text")
    val text: String // Ziggy Sobotka - Chester Karol "Ziggy" Sobotka is a fictional character on the HBO drama The Wire, played by actor James Ransone.
)