package com.kutluoglu.remote.model


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("attribution")
    val attribution: Any?, // null
    @SerializedName("blockgroup")
    val blockgroup: Any?, // null
    @SerializedName("created_date")
    val createdDate: Any?, // null
    @SerializedName("description")
    val description: String, // Wikipedia
    @SerializedName("designer")
    val designer: Any?, // null
    @SerializedName("dev_date")
    val devDate: Any?, // null
    @SerializedName("dev_milestone")
    val devMilestone: String, // live
    @SerializedName("developer")
    val developer: List<Developer>,
    @SerializedName("example_query")
    val exampleQuery: String, // nikola tesla
    @SerializedName("id")
    val id: String, // wikipedia_fathead
    @SerializedName("is_stackexchange")
    val isStackexchange: Any?, // null
    @SerializedName("js_callback_name")
    val jsCallbackName: String, // wikipedia
    @SerializedName("live_date")
    val liveDate: Any?, // null
    @SerializedName("maintainer")
    val maintainer: Maintainer,
    @SerializedName("name")
    val name: String, // Wikipedia
    @SerializedName("perl_module")
    val perlModule: String, // DDG::Fathead::Wikipedia
    @SerializedName("producer")
    val producer: Any?, // null
    @SerializedName("production_state")
    val productionState: String, // online
    @SerializedName("repo")
    val repo: String, // fathead
    @SerializedName("signal_from")
    val signalFrom: String, // wikipedia_fathead
    @SerializedName("src_domain")
    val srcDomain: String, // en.wikipedia.org
    @SerializedName("src_id")
    val srcId: Int, // 1
    @SerializedName("src_name")
    val srcName: String, // Wikipedia
    @SerializedName("src_options")
    val srcOptions: SrcOptions,
    @SerializedName("src_url")
    val srcUrl: Any?, // null
    @SerializedName("status")
    val status: String, // live
    @SerializedName("tab")
    val tab: String, // About
    @SerializedName("topic")
    val topic: List<String>,
    @SerializedName("unsafe")
    val unsafe: Int // 0
)