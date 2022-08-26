package com.links.events.calendar.data.source

import com.links.events.calendar.model.DeadlineModel

/**
 * Created by Antonio Vitiello
 */
private val descriptions = listOf(
    "SCADENZA A",
    "SCADENZA B",
    "SCADENZA C",
    "SCADENZA D",
    "SCADENZA E",
    "SCADENZA F",
    "SCADENZA G",
    "SCADENZA H",
    "SCADENZA I",
    "SCADENZA L",
    "SCADENZA M",
    "SCADENZA N",
    "SCADENZA O",
    "SCADENZA P",
    "SCADENZA Q",
    "SCADENZA R",
    "SCADENZA S",
    "SCADENZA T",
    "SCADENZA U",
    "SCADENZA V",
    "SCADENZA Z"
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
            "$year/$month/${(1..31).random()}",
            listOf(descriptions.random(), descriptions.random(), descriptions.random()),
            reminders.random()
        ),
        DeadlineModel(
            "$year/$month/${(1..31).random()}",
            listOf(descriptions.random(), descriptions.random(), descriptions.random()),
            reminders.random()
        ),
        DeadlineModel(
            "$year/$month/${(1..31).random()}",
            listOf(descriptions.random(), descriptions.random(), descriptions.random()),
            reminders.random()
        ),
        DeadlineModel(
            "$year/$month/${(1..31).random()}",
            listOf(descriptions.random(), descriptions.random(), descriptions.random()),
            reminders.random()
        ),
        DeadlineModel(
            "$year/$month/${(1..31).random()}",
            listOf(descriptions.random(), descriptions.random(), descriptions.random()),
            reminders.random()
        )
    )
}

fun getMonthDeadlines(): List<DeadlineModel> {
    return listOf(
        DeadlineModel(
            "2022/08/7", listOf(descriptions.random(), descriptions.random(), descriptions.random()), reminders.random()
        ),
        DeadlineModel(
            "2022/08/8", listOf(descriptions.random(), descriptions.random(), descriptions.random()), reminders.random()
        ),
        DeadlineModel(
            "2022/08/16", listOf(descriptions.random(), descriptions.random(), descriptions.random()), reminders.random()
        ),
        DeadlineModel(
            "2022/08/26", listOf(descriptions.random(), descriptions.random(), descriptions.random()), reminders.random()
        ),
        DeadlineModel(
            "2022/08/28", listOf(descriptions.random(), descriptions.random(), descriptions.random()), reminders.random()
        )
    )
}

fun getDeadlineByDate(date: String): DeadlineModel {
    return DeadlineModel(
        date,
        listOf(
            descriptions.random(),
            descriptions.random(),
            descriptions.random()
        ),
        reminders.random()
    )
}
