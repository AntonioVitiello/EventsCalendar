package com.links.events.calendar.view.widget

import com.links.events.calendar.tools.DateUtils
import com.links.events.calendar.tools.capitalize
import org.junit.Test
import java.text.DateFormat
import java.text.DateFormat.FULL
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Antonio Vitiello on 23/08/2022.
 */
class EventCalendarWidgetTest {
    @Test
    fun getCurrentMonth() {
        val dateString = DateFormat.getDateInstance(FULL).format(Date())
        println("DateFormat: dateString = $dateString")
    }

    @Test
    fun getCurrentMonth1() {
        val simpleDate = SimpleDateFormat("MMMM yyyy", Locale.ITALY)
        var currentDate = simpleDate.format(Date())
        val initial = currentDate[0]
        currentDate = currentDate.replaceFirst(initial, initial.uppercaseChar())
        println("SimpleDateFormat-1: currentDate = $currentDate")
    }

    @Test
    fun getCurrentMonth2() {
        val simpleDate = SimpleDateFormat("MMMM yyyy", Locale.ITALY)
        var currentDate = simpleDate.format(Date())
        currentDate = currentDate.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ITALY) else it.toString() }

        println("SimpleDateFormat-2: currentDate = $currentDate")
    }

    @Test
    fun getCurrentMonth3() {
        val simpleDate = SimpleDateFormat("MMMM yyyy", Locale.ITALY)
        val currentDate = simpleDate.format(Date()).replaceFirstChar {
            if (it.isLowerCase()) {
                it.titlecase(Locale.ITALY)
            } else {
                it.toString()
            }
        }
        println("SimpleDateFormat-3: currentDate = $currentDate")
    }

    @Test
    fun getCurrentMonth4() {
        val currentDate = DateUtils.formatMonthYear().capitalize()
        println("SimpleDateFormat-4: currentDate = $currentDate")
    }

}