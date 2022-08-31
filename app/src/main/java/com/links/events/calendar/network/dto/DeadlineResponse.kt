package com.links.events.calendar.network.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by Antonio Vitiello
 */

/**
 * Params: date - date String in the format yyy/MM/dd  eg:"2022/09/25"
 */
class DeadlineResponse(
    @SerializedName("deadlines")
    val deadlines: List<Deadline>? = null
)