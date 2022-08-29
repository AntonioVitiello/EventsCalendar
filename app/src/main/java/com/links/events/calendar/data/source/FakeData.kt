package com.links.events.calendar.data.source

import com.links.events.calendar.network.dto.Deadline
import com.links.events.calendar.network.dto.DeadlineResponse
import com.links.events.calendar.tools.zeroPad

/**
 * Created by Antonio Vitiello
 */
private val descriptions = listOf(
    "SCADENZA A", "SCADENZA B", "SCADENZA C", "SCADENZA D", "SCADENZA E", "SCADENZA F", "SCADENZA G",
    "SCADENZA H", "SCADENZA I", "SCADENZA L", "SCADENZA M", "SCADENZA N", "SCADENZA O", "SCADENZA P",
    "SCADENZA Q", "SCADENZA R", "SCADENZA S", "SCADENZA T", "SCADENZA U", "SCADENZA V", "SCADENZA Z"
)

private val reminders = listOf(
    "Lorem ipsum dolor sit amet, ",
    "consectetur adipiscing elit.",
    "Quisque eget purus a odio rhoncus pulvinar commodo sit amet nulla.",
    "Ut euismod rhoncus erat at egestas.",
    "Sed vulputate eleifend turpis id pulvinar.",
    "Ut a hendrerit lectus.",
    "Ut condimentum vel eros vitae placerat."
)

fun getDeadlinesByDate(data: String): DeadlineResponse {
    val split = data.split("/")
    val year = split[0]
    val month = split[1]
    return DeadlineResponse(
        listOf(
            Deadline(
                id = "123",
                date = "$year/$month/${randomDay()}",
                titles = listOf(descriptions.random(), descriptions.random(), descriptions.random()),
                desc = reminders.random()
            ),
            Deadline(
                id = "124",
                date = "$year/$month/${randomDay()}",
                titles = listOf(descriptions.random(), descriptions.random(), descriptions.random()),
                desc = reminders.random()
            ),
            Deadline(
                id = "125",
                date = "$year/$month/${randomDay()}",
                titles = listOf(descriptions.random(), descriptions.random(), descriptions.random()),
                desc = reminders.random()
            ),
            Deadline(
                id = "126",
                date = "$year/$month/${randomDay()}",
                titles = listOf(descriptions.random(), descriptions.random(), descriptions.random()),
                desc = reminders.random()
            ),
            Deadline(
                id = "127",
                date = "$year/$month/${randomDay()}",
                titles = listOf(descriptions.random()),
                desc = reminders.random()
            ),
            Deadline(
                id = "128",
                date = "$year/$month/${randomDay()}",
                titles = listOf(descriptions.random(), descriptions.random(), descriptions.random()),
                desc = reminders.random()
            )
        )
    )
}

// return a random day in 1..31
fun randomDay(): String {
    return (1..31).random().zeroPad()
}