package com.links.events.calendar.network.mapping

import com.links.events.calendar.model.DeadlineModel
import com.links.events.calendar.network.dto.DeadlineResponse
import com.links.events.calendar.tools.DateUtils
import com.links.events.calendar.tools.Utils

/**
 * Created by Antonio Vitiello
 */
fun mapDeadlineResponse(response: DeadlineResponse): List<DeadlineModel> {
    return mutableListOf<DeadlineModel>().apply {
        response.deadlines?.forEach { deadline ->
            val dayOfYear = DateUtils.parseDayOfYearOrNull(deadline.date)
            Utils.safeLet(deadline.id, dayOfYear) { id, date ->
                add(
                    DeadlineModel(
                        id = id,
                        date = DateUtils.formatDayOfYear(date),
                        titles = deadline.titles ?: listOf(),
                        desc = deadline.desc
                    )
                )
            }
        }
    }.apply {
        sortBy { it.date }
    }
}