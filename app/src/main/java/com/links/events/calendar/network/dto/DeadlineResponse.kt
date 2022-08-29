package com.links.events.calendar.network.dto

/**
 * Created by Antonio Vitiello
 */

/**
 * Params: date - date String in the format yyy/MM/dd  eg:"2022/09/25"
 */
class DeadlineResponse(
    val deadlines: List<Deadline>? = null
)