package com.links.events.calendar.data.source

import com.links.events.calendar.model.DeadlineModel

/**
 * Created by Antonio Vitiello on 25/08/2022.
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

fun getDeadlinesByDate(data: String): List<DeadlineModel> {
    val split = data.split("/")
    val year = split[0]
    val month = split[1]
    return listOf(
        DeadlineModel(
            "$year/$month/${(1..24).random()}",
            listOf(descriptions.random(), descriptions.random(), descriptions.random())
        ),
        DeadlineModel(
            "$year/$month/${(1..24).random()}",
            listOf(descriptions.random(), descriptions.random(), descriptions.random())
        ),
        DeadlineModel(
            "$year/$month/${(1..24).random()}",
            listOf(descriptions.random(), descriptions.random(), descriptions.random())
        ),
        DeadlineModel(
            "$year/$month/${(1..24).random()}",
            listOf(descriptions.random(), descriptions.random(), descriptions.random())
        ),
        DeadlineModel(
            "$year/$month/${(1..24).random()}",
            listOf(descriptions.random(), descriptions.random(), descriptions.random())
        )
    )
}

fun getMonthDeadlines(): List<DeadlineModel> {
    return listOf(
        DeadlineModel(
            "2022/08/7", listOf(descriptions.random(), descriptions.random(), descriptions.random())
        ),
        DeadlineModel(
            "2022/08/8", listOf(descriptions.random(), descriptions.random(), descriptions.random())
        ),
        DeadlineModel(
            "2022/08/16", listOf(descriptions.random(), descriptions.random(), descriptions.random())
        ),
        DeadlineModel(
            "2022/08/26", listOf(descriptions.random(), descriptions.random(), descriptions.random())
        ),
        DeadlineModel(
            "2022/08/28", listOf(descriptions.random(), descriptions.random(), descriptions.random())
        )
    )
}

fun getDeadlineByDate(date: String): DeadlineModel {
    return DeadlineModel(
        date, listOf(
            "1 ${descriptions.random()}",
            descriptions.random(),
            descriptions.random()
        )
    )
}
