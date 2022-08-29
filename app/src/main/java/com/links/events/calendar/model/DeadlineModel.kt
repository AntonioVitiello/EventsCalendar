package com.links.events.calendar.model

/**
 * Created by Antonio Vitiello
 */

/**
 * Params: deadline - date String in the format yyy/MM/dd  eg:"2022/09/25"
 * descriptions - text descriptions of events in this deadline
 */
class DeadlineModel(
    val id: String,
    var date: String,
    val titles: List<String> = listOf(),
    val desc: String? = "Deadline reminder!"
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DeadlineModel

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

}
