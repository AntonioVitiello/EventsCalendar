package com.links.events.calendar.network.dto

import com.google.gson.annotations.SerializedName

class Deadline(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("date")
    var date: String? = null,
    @SerializedName("titles")
    val titles: List<String>? = null,
    @SerializedName("desc")
    val desc: String? = null
)
