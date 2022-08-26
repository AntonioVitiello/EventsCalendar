package com.links.events.calendar.model

/**
 * Created by Antonio Vitiello
 */

/**
 * Params: deadline - date String in the format yyy/MM/dd  eg:"2022/09/25"
 * descriptions - text descriptions of events in this deadline
 */
class DeadlineModel(
    val date: String,
    val descriptions: List<String> = listOf(),
    val reminder: String? = "Deadline reminder!"
)
