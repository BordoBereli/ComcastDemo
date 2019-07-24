package com.kutluoglu.remote.model


import com.google.gson.annotations.SerializedName

data class RemoteContent(
    @SerializedName("Abstract")
    val `abstract`: String,
    @SerializedName("AbstractSource")
    val abstractSource: String, // Wikipedia
    @SerializedName("AbstractText")
    val abstractText: String,
    @SerializedName("AbstractURL")
    val abstractURL: String, // https://en.wikipedia.org/wiki/The_Wire_characters
    @SerializedName("Answer")
    val answer: String,
    @SerializedName("AnswerType")
    val answerType: String,
    @SerializedName("Definition")
    val definition: String,
    @SerializedName("DefinitionSource")
    val definitionSource: String,
    @SerializedName("DefinitionURL")
    val definitionURL: String,
    @SerializedName("Entity")
    val entity: String,
    @SerializedName("Heading")
    val heading: String, // The Wire characters
    @SerializedName("Image")
    val image: String,
    @SerializedName("ImageHeight")
    val imageHeight: Int, // 0
    @SerializedName("ImageIsLogo")
    val imageIsLogo: Int, // 0
    @SerializedName("ImageWidth")
    val imageWidth: Int, // 0
    @SerializedName("Infobox")
    val infobox: String,
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("Redirect")
    val redirect: String,
    @SerializedName("RelatedTopics")
    val relatedTopics: List<RelatedTopic>,
    @SerializedName("Results")
    val results: List<Any>,
    @SerializedName("Type")
    val type: String // C
)