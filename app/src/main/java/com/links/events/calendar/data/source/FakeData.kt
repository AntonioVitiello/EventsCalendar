package com.links.events.calendar.data.source

import com.links.events.calendar.model.DeadlineModel
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

fun getDeadlinesByDate(data: String): List<DeadlineModel> {
    val split = data.split("/")
    val year = split[0]
    val month = split[1]
    return listOf(
        DeadlineModel(
            id = "123",
            date = "$year/$month/${randomDay()}",
            titles = listOf(descriptions.random(), descriptions.random(), descriptions.random()),
            desc = reminders.random()
        ),
        DeadlineModel(
            id = "124",
            date = "$year/$month/${randomDay()}",
            titles = listOf(descriptions.random(), descriptions.random(), descriptions.random()),
            desc = reminders.random()
        ),
        DeadlineModel(
            id = "125",
            date = "$year/$month/${randomDay()}",
            titles = listOf(descriptions.random(), descriptions.random(), descriptions.random()),
            desc = reminders.random()
        ),
        DeadlineModel(
            id = "126",
            date = "$year/$month/${randomDay()}",
            titles = listOf(descriptions.random(), descriptions.random(), descriptions.random()),
            desc = reminders.random()
        ),
        DeadlineModel(
            id = "127",
            date = "$year/$month/${randomDay()}",
            titles = listOf(descriptions.random(), descriptions.random(), descriptions.random()),
            desc = reminders.random()
        )
    )
}

// return a random day in 1..31
fun randomDay(): String {
    return (1..31).random().zeroPad()
}

fun getDeadlineByDate(date: String): DeadlineModel {
    return DeadlineModel(
        id = "1234",
        date = date,
        titles = listOf(descriptions.random(), descriptions.random(), descriptions.random()),
        desc = reminders.random()
    )
}

fun getMonthDeadlines(): List<DeadlineModel> {
    return listOf(
        DeadlineModel(
            id = "1000",
            date = "2022/08/07",
            titles = listOf(descriptions.random(), descriptions.random(), descriptions.random()),
            desc = reminders.random()
        ),
        DeadlineModel(
            id = "1001",
            date = "2022/08/08",
            titles = listOf(descriptions.random(), descriptions.random(), descriptions.random()),
            desc = reminders.random()
        ),
        DeadlineModel(
            id = "1002",
            date = "2022/08/16",
            titles = listOf(descriptions.random(), descriptions.random(), descriptions.random()),
            desc = reminders.random()
        ),
        DeadlineModel(
            id = "1003",
            date = "2022/08/26",
            titles = listOf(descriptions.random(), descriptions.random(), descriptions.random()),
            desc = reminders.random()
        ),
        DeadlineModel(
            id = "1004",
            date = "2022/08/28",
            titles = listOf(descriptions.random(), descriptions.random(), descriptions.random()),
            desc = reminders.random()
        )
    )
}
