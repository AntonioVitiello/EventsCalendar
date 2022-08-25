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

fun getMonthDeadlines(): List<DeadlineModel> {
    return listOf(
        DeadlineModel(
            "2022/08/7", listOf("1 ${descriptions.random()}", "2 ${descriptions.random()}", "3 ${descriptions.random()}")
        ),
        DeadlineModel(
            "2022/08/8", listOf("1 ${descriptions.random()}", "2 ${descriptions.random()}", "3 ${descriptions.random()}")
        ),
        DeadlineModel(
            "2022/08/16", listOf("1 ${descriptions.random()}", "2 ${descriptions.random()}", "3 ${descriptions.random()}")
        ),
        DeadlineModel(
            "2022/08/25", listOf("1 ${descriptions.random()}", "2 ${descriptions.random()}", "3 ${descriptions.random()}")
        ),
        DeadlineModel(
            "2022/08/28", listOf("1 ${descriptions.random()}", "2 ${descriptions.random()}", "3 ${descriptions.random()}")
        )
    )
}

fun getDeadlineByDate(date: String): DeadlineModel {
    return DeadlineModel(
        date, listOf(
            "1 ${descriptions.random()}",
            "2 ${descriptions.random()}",
            "3 ${descriptions.random()}"
        )
    )
}